/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

/**
 * Defines the interface for visiting our <code>Composite</code> tree.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IVisitor
{
    /**
     * Visit an aggregator (Composite) within the tree.
     * 
     * @param aggregator the aggregator to visit
     */
    public void visitAggregator(final CodeAggregator aggregator);

    /**
     * Visit a Line Item (Leaf) with the tree.
     * 
     * @param lineItem the line item to visit
     */
    public void visitLineItem(final LineItem lineItem);
}
