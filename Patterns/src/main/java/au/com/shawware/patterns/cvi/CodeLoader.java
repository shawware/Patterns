/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.Scanner;

import au.com.shawware.util.StringUtil;
import au.com.shawware.util.SwAssert;
import au.com.shawware.util.issues.LineIssueHolder;

/**
 * Loads a set of codes from a given source and produces a tree of codes.
 * 
 * The focus of this project is exploring patterns. Therefore the loader
 * is very simple (ie. very limited error handling) and enforces a simple
 * format (ie. limited options, no forward definitions, etc.).
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
@SuppressWarnings("nls")
public class CodeLoader
{
    /** The singleton instance of the loader. */
    private static final CodeLoader sInstance = new CodeLoader();
    /** Private constructor to prevent direct instantiation. */
    private CodeLoader() {}

    /**
     * @return The singleton instance of the loader.
     */
    public static CodeLoader getInstance()
    {
        return sInstance;
    }

    /** The chart of accounts being loaded. */
    private CodeAggregator mRawChart;
    /** The current department. */
    private CodeAggregator mCurrentDept;
    /** The current area. */
    private CodeAggregator mCurrentArea;
    /** The current sub area. */
    private CodeAggregator mCurrentSubArea;
 
    /** Whether we're currently within the definition of a department. */
    private boolean mInDept;
    /** Whether we're currently within the definition of an area. */
    private boolean mInArea;
    /** Whether we're currently within the definition of a sub area. */
    private boolean mInSubArea;
    /** The set of encountered issues. */
    private LineIssueHolder mIssues;
    /** The current line number. */
    private int mLineNumber;
 
    /**
     * Loads the codes from the given source and returns them as an aggregate.
     * 
     * @param id the id of the new aggregate
     * @param desc the description of the new aggregate
     * @param src the source of the code definition
     * @param issues a holder for any issues encountered processing the code definition
     * 
     * @return The processed codes as an aggregate.
     */
    public CodeAggregator loadCodes(final String id, final String desc, final Scanner src, final LineIssueHolder issues)
    {
        assert SwAssert.isNotEmpty(id);
        assert SwAssert.isNotEmpty(desc);
        assert SwAssert.isNotNull(src);
        assert SwAssert.isNotNull(issues);

        mRawChart = new CodeAggregator(id, desc);

        mInDept = false;
        mInArea = false;
        mInSubArea = false;
 
        mCurrentDept = null;
        mCurrentArea = null;
        mCurrentSubArea = null;

        mIssues = issues;
        mLineNumber = 0;
        while (src.hasNextLine())
        {
            mLineNumber++;

            final String line = src.nextLine().trim();

            // Skip empty lines and comments.
            if ((line.length() == 0) || (line.charAt(0) == '#'))
            {
                continue;
            }

            processLine(line);
        }

        if (issues.isOkay())
        {
            // TODO: convert raw to processed.
            final CodeAggregator result = new CodeAggregator(mRawChart.getId(), mRawChart.getDescription());
            return mRawChart;
        }
        return null;
    }

    /**
     * Processes a single line of the code definition.
     * 
     * @param line the line to process
     */
    private void processLine(final String line)
    {
        // Identify major elements of a line.
        // All lines have format: <cmd>: <attrs>
        if (!line.contains(": "))
        {
            mIssues.addError(mLineNumber, "missing colon (and space)");
            return;
        }
        final String[] tokens = line.split(": ");
        final String cmd = tokens[0];
        if ((tokens.length == 1) || StringUtil.isEmpty(tokens[1]))
        {
            mIssues.addError(mLineNumber, "nothing specified after element: \"" + cmd + "\"");
            return;
        }
        if (!tokens[1].contains(", "))
        {
            mIssues.addError(mLineNumber, "malformed attributes: \"" + tokens[1] + "\"");
            return;
        }
        final String[] attrs = tokens[1].split(", ");
        if (attrs.length != 3)
        {
            mIssues.addError(mLineNumber, "incorrect number of attributes: \"" + tokens[1] + "\"");
            return;
        }
        final String elt = attrs[0];
        final String id = attrs[1];
        final String desc = attrs[2];

        if (elt.equals("DD"))
        {
            mCurrentDept = processAggregate(id, desc, true, mRawChart, "department", "chart");
            mInDept = true;
            mInArea = false;
            mInSubArea = false;
        }
        else if (elt.equals("AA"))
        {
            mCurrentArea = processAggregate(id, desc, mInDept, mCurrentDept, "area", "department");
            mInArea = true;
            mInSubArea = false;
        }
        else if (elt.equals("SS"))
        {
            mCurrentSubArea = processAggregate(id, desc, mInArea, mCurrentArea, "sub area", "area");
            mInSubArea = true;
        }
        else if (elt.equals("LL"))
        {
            if (!mInSubArea)
            {
                mIssues.addError(mLineNumber, "line item definition outside of sub area: \"" + id + "\"");
                return;
            }
            if (mCurrentSubArea.containsChild(id))
            {
                mIssues.addError(mLineNumber, "duplicate line item: \"" + id + "\"");
                return;
            }
            LineItem lineItem = new LineItem(id, desc);
            mCurrentSubArea.addChild(lineItem);
        }
        else if (elt.equals("Common-SS"))
        {
            
        }
        else
        {
            mIssues.addError(mLineNumber, "unknown element type: \"" + cmd + "\"");
            return;
        }
    }

    /**
     * Processes the definition of an aggregate.
     * 
     * @param id the ID of the new aggregate
     * @param desc the description of the new aggregate
     * @param inParentDefn whether we're within the definition of the appropriate parent
     * @param parent the parent aggregator
     * @param def the aggregate we're defining
     * @param parentDef the parent aggregate we're within
     * 
     * @return the new code aggregate
     */
    private CodeAggregator processAggregate(final String id, final String desc,
        final boolean inParentDefn, final CodeAggregator parent,
        final String def, final String parentDef)
    {
        if (!inParentDefn)
        {
            mIssues.addError(mLineNumber, def + " definition outside of " + parentDef + ": \"" + id + "\" (" + desc + ")");
            return null;
        }
        if (parent.containsChild(id))
        {
            mIssues.addError(mLineNumber, "duplicate " + parentDef + ": \"" + id + "\" (" + desc + ")");
            return null;
        }
        final CodeAggregator child = new CodeAggregator(id, desc);
        parent.addChild(child);
        return child;
    }
}
