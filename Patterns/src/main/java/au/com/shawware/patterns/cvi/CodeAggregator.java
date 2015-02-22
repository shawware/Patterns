/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.Iterator;

import au.com.shawware.util.SwAssert;

/**
 * Defines an element for aggregating codes within a chart of accounts.
 * 
 * Forms the <em>Composite</em> of the <code>Composite</code> pattern.
 * 
 * We have chosen to put the child management into the <em>Composite</em>
 * rather than the <em>Component</em>.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class CodeAggregator extends AbstractElement
{
    // Output punctuation
    private static final String OPEN_LIST  = "[";  //$NON-NLS-1$
    private static final String CLOSE_LIST = "]";  //$NON-NLS-1$

    /**
     * Constructs a new aggregate (<code>Composite</code>).
     * 
     * @param id the new component's id
     * @param desc the new component's description
     */
    public CodeAggregator(final String id, final String desc)
    {
        super(id, desc, false);
    }

    /**
     * Adds the given element to this element's children.
     * If the element has the same ID as an existing child, the existing child is replaced.
     * 
     * Has the side-effect of setting the child's parent to this element.
     * 
     * @param child the child element
     */
    public void addChild(final AbstractElement child)
    {
        assert SwAssert.isNotNull(child);

        mChildren.put(child.getId(), child);
        child.mParent = this;
    }

    /**
     * Reports whether this aggregate has a child with the given ID.
     * 
     * @param id the identifier of the child to look for
     * 
     * @return Whether the given child is present.
     */
    public boolean containsChild(final String id)
    {
        assert SwAssert.isNotEmpty(id);

        return mChildren.containsKey(id);
    }

    /**
     * Returns the child from this aggregate with the given ID.
     * 
     * @param id the identifier of the child to look for
     * 
     * @return The corresponding child.
     */
    public AbstractElement getChild(final String id)
    {
        assert containsChild(id);

        return mChildren.get(id);
    }

    /**
     * @return The IDs of this aggregate's children.
     */
    public Iterator<String> getChildrenIDs()
    {
        return mChildren.keySet().iterator();
    }

    @Override
    public void accept(final IVisitor visitor)
    {
        visitor.visitAggregator(this);
        if (mChildren.size() > 0)
        {
            final Iterator<String> children = getChildrenIDs();
            while (children.hasNext())
            {
                final AbstractElement child = getChild(children.next());
                child.accept(visitor);
            }
        }
    }

    @Override
    protected final String children()
    {
        final StringBuilder children = new StringBuilder();
        children.append(OPEN_LIST);
        final Iterator<String> i = mChildren.keySet().iterator();
        while (i.hasNext())
        {
            children.append(elementHeader(mChildren.get(i.next()), false, true, true));
            if (i.hasNext())
            {
                children.append(ITEM_SEP);
            }
        }
        children.append(CLOSE_LIST);
        return children.toString();
    }
}
