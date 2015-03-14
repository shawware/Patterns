/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implements a pre-order, depth first ordering.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class PreOrderElements implements IElementOrderer
{
    /* (non-Javadoc)
     * @see au.com.shawware.patterns.cvi.IOrderElements#orderElements(au.com.shawware.patterns.cvi.CodeAggregator)
     */
    @Override
    public List<AbstractElement> orderElements(final CodeAggregator aggregator)
    {
        final List<AbstractElement> orderedElements = new ArrayList<AbstractElement>();
        preOrder(orderedElements, aggregator);
        return orderedElements;
    }

    /**
     * Recursively initialise the list of elements to iterate over in pre-order.
     * 
     * @param orderedElements the list to add the elements to
     * @param elt the current element in the recursive iteration
     */
    private void preOrder(final List<AbstractElement> orderedElements, final AbstractElement elt)
    {
        orderedElements.add(elt);
        if (!elt.isLeaf())
        {
            final CodeAggregator aggregator = (CodeAggregator)elt;
            final Iterator<String> ids = aggregator.getChildrenIDs();
            while (ids.hasNext())
            {
                preOrder(orderedElements, aggregator.getChild(ids.next()));
            }
        }
    }

    @Override
    public String toString()
    {
        return "Depth First :: Pre Order"; //$NON-NLS-1$
    }
}
