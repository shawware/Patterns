/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts a <em>Component</em> from our <code>Composite</code> pattern to a
 * String using the <code>Visitor</code> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ConvertToString implements IVisitor
{
    /** The Code Generator to use for codes. */
    private final CodeGenerator mGenerator;
    /** Holds the Composite Components' strings as we visit them in turn. */
    private final List<String> mStrings;

    /**
     * Constructor.
     * 
     * @param maxDepth the maximum depth to use for code generation
     */
    public ConvertToString(final int maxDepth)
    {
        mGenerator = new CodeGenerator(maxDepth);
        mStrings = new ArrayList<String>();
    }

    /* (non-Javadoc)
     * @see au.com.shawware.patterns.cvi.IVisitor#visitAggregator(au.com.shawware.patterns.cvi.CodeAggregator)
     */
    @Override
    public void visitAggregator(final CodeAggregator aggregator)
    {
        // We don't output the code for the root of the Composite.
        if (!aggregator.isRoot())
        {
            mStrings.add(mGenerator.generateCode(aggregator));
        }
    }

    /* (non-Javadoc)
     * @see au.com.shawware.patterns.cvi.IVisitor#visitLineItem(au.com.shawware.patterns.cvi.LineItem)
     */
    @Override
    public void visitLineItem(final LineItem lineItem)
    {
        mStrings.add(mGenerator.generateCode(lineItem));
    }

    /**
     * @return The current result for this visitor.
     */
    public String[] getResult()
    {
        final String[] result = new String[mStrings.size()];
        mStrings.toArray(result);
        return result;
    }
}
