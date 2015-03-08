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
 * An iterator over our <code>Composite</code> pattern.
 * Implements depth-first iterations (boht pre-order and post-order).
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class DepthFirstIterator implements Iterator<AbstractElement>
{
    /** The ordered list of elements to be iterated over. */
    private final List<AbstractElement> mElements;
    /** The iteration order. */
    private final DepthFirstOrder mOrder;
    /** The index of the next element in the list. */
    private int mNext;

    /**
     * Construct a new Iterator over the given aggregate Component.
     * 
     * @param aggregator the aggregate to iterate over
     * @param order the order in which to iterate through the elements - we support
     * {@link DepthFirstOrder#PRE_ORDER} and {@link DepthFirstOrder#POST_ORDER}
     */
    public DepthFirstIterator(final CodeAggregator aggregator, final DepthFirstOrder order)
    {
        SwAssert.notNull(aggregator);
        SwAssert.notNull(order);

        final IElementOrderer orderer;
        switch (order)
        {
            case IN_ORDER:
                throw new IllegalArgumentException("unsupported order"); //$NON-NLS-1$
            case POST_ORDER:
                orderer = new PostOrderElements();
                break;
            case PRE_ORDER:
            default:
                orderer = new PreOrderElements();
                break;
        }
        mElements = orderer.orderElements(aggregator);
        mOrder = order;
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
        return "DepthFirstIterator" + //$NON-NLS-1$
                "(" + //$NON-NLS-1$
                mOrder +
                ")"; //$NON-NLS-1$
    }
}