/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import au.com.shawware.util.issues.LineIssueHolder;

/**
 * Unit tests for {@link CodeLoader}
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
@SuppressWarnings({"nls", "static-method"})
public class CodeLoaderUnitTest
{
    /** The maximum depth of our codes, ie. number of levels in tree. */
    private static final int sMaxDepth = 4;

    /** The definition of a simple set of codes. */
    private static final String[] sBasicCodeDefinition =
    {
        "# Basic test",
        "def: DD, 10, Food",
        "  def: AA, 10, Fruit",
        "    def: SS, 10, Apples",
        "      def: LL, 10, Pink Lady",
        "      def: LL, 20, Granny Smith",
        "      def: LL, 30, Fuji",
        "    def: SS, 20, Oranges",
        "      def: LL, 10, Mandarin",
        "      def: LL, 20, Navel",
    };
    /** The corresponding account codes (to {@link #sBasicCodeDefinition}. */
    private static final String[] sBasicCodes =
    {
        "10-00-00-00 Food",
        "10-10-00-00  Fruit",
        "10-10-10-00   Apples",
        "10-10-10-10    Pink Lady",
        "10-10-10-20    Granny Smith",
        "10-10-10-30    Fuji",
        "10-10-20-00   Oranges",
        "10-10-20-10    Mandarin",
        "10-10-20-20    Navel",
    };

    /**
     * Test the basic load and compare operation.
     */
    @Test
    public void basicTest()
    {
        final CodeAggregator chart = loadChart("Basic", "Basic Test", sBasicCodeDefinition);
        final String[] actualCodes = aggregateToArray(chart);
        Assert.assertArrayEquals(sBasicCodes, actualCodes);
    }

    /**
     * Tests the Visitor pattern.
     */
    @Test
    public void visitorTest()
    {
        final CodeAggregator chart = loadChart("Visitor", "Visitor Test", sBasicCodeDefinition);
        final ConvertToString visitor = new ConvertToString(sMaxDepth);
        chart.accept(visitor);
        final String[] actualCodes = visitor.getResult();
        Assert.assertArrayEquals(sBasicCodes, actualCodes);
    }

    /**
     * Creates a new chart with the given ID and description and
     * loads the codes in the given definition into it.
     * 
     * @param id the new chart's ID
     * @param desc the new chart's description
     * @param def the chart definition
     * 
     * @return The new chart.
     */
    private CodeAggregator loadChart(final String id, final String desc, final String[] def)
    {
        final CodeLoader ldr = CodeLoader.getInstance();
        final LineIssueHolder issues = new LineIssueHolder();
        final Scanner src = new Scanner(String.join("\n", def));
        final CodeAggregator chart = ldr.loadCodes(id, desc, src, issues);
        src.close();
        assertThat(chart, notNullValue());
        Assert.assertTrue("issues found in chart definition", issues.isOkay());
        assertThat(chart.getId(), equalTo(id));
        assertThat(chart.getDescription(), equalTo(desc));
        return chart;
    }

    /**
     * Converts the given aggregate to an array.
     * 
     * @param aggregate the aggregate to convert
     * 
     * @return The converted aggregate.
     */
    private String[] aggregateToArray(final CodeAggregator aggregate)
    {
        final List<String> codes = new ArrayList<String>();
        final CodeGenerator generator = new CodeGenerator(sMaxDepth);
        codesToArray(aggregate, codes, generator);
        return codes.toArray(new String[codes.size()]);
    }

    /**
     * Converts the given aggregate to a list of strings (using a recursive algorithm).
     * The root of the tree is not converted.
     * 
     * @param aggregate the aggregate to convert
     * @param codes the list of strings to append to
     * @param generator the generator to use to create codes
     */
    private void codesToArray(final CodeAggregator aggregate, final List<String> codes, final CodeGenerator generator)
    {
        if (!aggregate.isRoot())
        {
            codes.add(generator.generateCode(aggregate));
        }
        final Iterator<String> i = aggregate.getChildrenIDs();
        while (i.hasNext())
        {
            final AbstractElement child = aggregate.getChild(i.next());
            if (child.isLeaf())
            {
                codes.add(generator.generateCode(child));
            }
            else
            {
                codesToArray((CodeAggregator)child, codes, generator);
            }
        }
    }
}
