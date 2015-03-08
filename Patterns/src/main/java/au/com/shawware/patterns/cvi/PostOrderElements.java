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
 * Implements a post-order, depth first ordering.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class PostOrderElements implements IElementOrderer
{

    /* (non-Javadoc)
     * @see au.com.shawware.patterns.cvi.IElementOrderer#orderElements(au.com.shawware.patterns.cvi.CodeAggregator)
     */
    @Override
    public List<AbstractElement> orderElements(CodeAggregator aggregator)
    {
        final List<AbstractElement> orderedElements = new ArrayList<AbstractElement>();
        postOrder(orderedElements, aggregator);
        return orderedElements;
    }

    /**
     * Recursively initialise the list of elements to iterate over in post-order.
     * 
     * @param orderedElements the list to add the elements to
     * @param elt the current element in the recursive iteration
     */
    private void postOrder(final List<AbstractElement> orderedElements, final AbstractElement elt)
    {
        if (!elt.isLeaf())
        {
            final CodeAggregator aggregator = (CodeAggregator)elt;
            final Iterator<String> ids = aggregator.getChildrenIDs();
            while (ids.hasNext())
            {
                postOrder(orderedElements, aggregator.getChild(ids.next()));
            }
        }
        orderedElements.add(elt);
    }
}
