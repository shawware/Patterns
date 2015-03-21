/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.Iterator;
import java.util.List;

import au.com.shawware.util.SwAssert;

/**
 * An <code>Iterator</code> over our <code>Composite</code> pattern.
 * Implements depth-first iterations (both pre-order and post-order).
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class DepthFirstIterator implements Iterator<AbstractElement>
{
    /** The ordered list of elements to be iterated over. */
    private final List<AbstractElement> mElements;
    /** The iteration order. */
    private final IElementOrderer mOrderer;
    /** The index of the next element in the list. */
    private int mNext;

    /**
     * Construct a new Iterator over the given aggregate Component.
     * 
     * @param aggregator the aggregate to iterate over
     * @param orderer the ordering strategy used to iterate through the elements
     */
    public DepthFirstIterator(final CodeAggregator aggregator, final IElementOrderer orderer)
    {
        SwAssert.notNull(aggregator);
        SwAssert.notNull(orderer);

        mElements = orderer.orderElements(aggregator);
        mOrderer = orderer;
        mNext = 0;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        return mNext < mElements.size();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    @Override
    public AbstractElement next()
    {
        return mElements.get(mNext++);
    }

    @Override
    public String toString()
    {
        return mOrderer.toString();
    }
}