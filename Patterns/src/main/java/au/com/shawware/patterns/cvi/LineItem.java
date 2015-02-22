/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

/**
 * Defines a line item within the chart of accounts.
 * Forms the <em>Leaf</em> of the <code>Composite</code> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class LineItem extends AbstractElement
{
    /**
     * Constructs a new line item (<code>Leaf</code>).
     * 
     * @param id the new component's id
     * @param desc the new component's description
     */
    public LineItem(final String id, final String desc)
    {
        super(id, desc, true);
    }

    @Override
    public void accept(final IVisitor visitor)
    {
        visitor.visitLineItem(this);
    }
}
