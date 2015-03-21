/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.List;

/**
 * Implement a trivial <code>Strategy</code> pattern for ordering
 * the elements in our <code>Composite</code>.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IElementOrderer
{
    /**
     * Orders the elements in the given aggregator.
     * 
     * @param aggregator the composite aggregator to process
     * 
     * @return An ordered list of the aggregator's elements.
     */
    public List<AbstractElement> orderElements(final CodeAggregator aggregator);
}
