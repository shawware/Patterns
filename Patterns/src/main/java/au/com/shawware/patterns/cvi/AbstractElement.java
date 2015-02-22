/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.Map;
import java.util.TreeMap;

import au.com.shawware.util.SwAssert;

/**
 * The base <em>Component</em> class for the <code>Composite</code> pattern.
 * 
 * We specify that each Component is aware of whether it is a leaf
 * and/or root Component.
 *
 * One of the trade-offs is where do we define the attributes and operations
 * for managing children. These are on the Composite, although the children
 * are stored in the Component so that they are easily available to {@link #toString()}.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class AbstractElement
{
    /** The element's ID. */
    private final String mID;
    /** The element's description. */
    private final String mDesc;
    /** Whether this element is a leaf or composite. */
    private final boolean mIsLeaf;
    /** This element's parent. */
    protected AbstractElement mParent;

    /** The element's children. */
    protected final Map<String, AbstractElement> mChildren;

    // Output punctuation
    private   static final String OPEN_ELT   = "{";  //$NON-NLS-1$
    private   static final String CLOSE_ELT  = "}";  //$NON-NLS-1$
    private   static final String OPEN_ATTR  = "(";  //$NON-NLS-1$
    private   static final String CLOSE_ATTR = ")";  //$NON-NLS-1$
    private   static final String ATTR_SEP   = ": "; //$NON-NLS-1$
    protected static final String ITEM_SEP   = ", "; //$NON-NLS-1$
    // Attribute names
    private static final String ROOT       = "root";     //$NON-NLS-1$
    private static final String CHILDREN   = "children"; //$NON-NLS-1$
    private static final String PARENT     = "parent";   //$NON-NLS-1$
    private static final String LEAF       = "leaf";     //$NON-NLS-1$

    /**
     * Constructs a new element.
     * 
     * @param id the new element's ID
     * @param desc the new element's description
     * @param isLeaf whether the new element is a leaf
     */
    public AbstractElement(final String id, final String desc, final boolean isLeaf)
    {
        SwAssert.notEmpty(id);
        SwAssert.notEmpty(desc);

        mID = id;
        mDesc = desc;
        mIsLeaf = isLeaf;
        mParent = null;
        mChildren = isLeaf ? null : new TreeMap<String, AbstractElement>();
    }

    /**
     * @return This element's ID.
     */
    public final String getId()
    {
        return mID;
    }

    /**
     * @return This element's description.
     */
    public final String getDescription()
    {
        return mDesc;
    }

    /**
     * @return Whether this element is a leaf.
     */
    public final boolean isLeaf()
    {
        return mIsLeaf;
    }

    /**
     * @return Whether this element is at the root of a tree of elements.
     */
    public final boolean isRoot()
    {
        return (mParent == null);
    }

    /**
     * @return This element's parent. <code>null</code> if <code>isRoot()</code>.
     */
    public final AbstractElement getParent()
    {
        return mParent;
    }

    /**
     * Accept a visitor (ref: the Visitor pattern).
     * 
     * @param visitor the visitor to accept
     */
    public abstract void accept(final IVisitor visitor);

    @Override
    public String toString()
    {
        final StringBuilder s = new StringBuilder();
        s.append(elementHeader(this, true, false, false));
        if (!isRoot())
        {
            s.append(ITEM_SEP);
            appendAttribute(s, PARENT, elementHeader(mParent, true, false, true));
        }
        s.append(ITEM_SEP);
        appendAttribute(s, LEAF, String.valueOf(isLeaf()));
        if (!isLeaf())
        {
            s.append(ITEM_SEP);
            appendAttribute(s, CHILDREN, children());
        }
        s.append(CLOSE_ELT);
        return s.toString();
    }

    /**
     * Constructs a string representing the element's header.
     * 
     * @param elt the element to process
     * @param includeRoot whether to include the root attribute
     * @param includeLeaf whether to include the leaf attribute
     * @param close whether to close the element
     * 
     * @return The corresponding string
     */
    protected final String elementHeader(final AbstractElement elt, final boolean includeRoot, final boolean includeLeaf, final boolean close)
    {
        final StringBuilder s = new StringBuilder();
        s.append(OPEN_ELT)
         .append(elt.getId())
         .append(ITEM_SEP)
         .append(elt.getDescription());
        if (includeRoot)
        {
            s.append(ITEM_SEP);
            appendAttribute(s, ROOT, String.valueOf(elt.isRoot()));
        }
        if (includeLeaf)
        {
            s.append(ITEM_SEP);
            appendAttribute(s, LEAF, String.valueOf(elt.isLeaf()));
        }
        if (close)
        {
            s.append(CLOSE_ELT);
        }
        return s.toString();
    }

    /**
     * Converts the children of this component to a string.
     * Leaf attributes should not override the default.
     * 
     * @return A string representation of this component's children.
     */
    @SuppressWarnings("static-method")
    protected String children()
    {
        return null;
    }

    /**
     * Appends an attribute to the given string builder.
     * 
     * @param s the string builder to append to
     * @param name the attribute's name
     * @param value the attribute's value
     */
    @SuppressWarnings("static-method")
    private void appendAttribute(final StringBuilder s, final String name, final String value)
    {
        s.append(OPEN_ATTR).append(name).append(ATTR_SEP).append(value).append(CLOSE_ATTR);
    }
}
