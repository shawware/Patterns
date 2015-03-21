/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

import java.util.Stack;

/**
 * Generates codes for <em>Component</em>s within our <code>Composite</code> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class CodeGenerator
{
    /** Specifies an empty element, ie. for those at the end of the code. */
    private static final String EMPTY_ELT = "00"; //$NON-NLS-1$
    /** Used to separate element levels in the generated code. */
    private static final String ELT_SEPARATOR = "-"; //$NON-NLS-1$
    /** Used to indent the description (after the code). */
    private static final String INDENT = " "; //$NON-NLS-1$

    /** The depth to generates codes to. */
    private final int mMaxDepth;

    /**
     * Constructs a new generator with the given maximum depth.
     * 
     * @param maxDepth the maximum depth to use for this generator
     */
    public CodeGenerator(final int maxDepth)
    {
        mMaxDepth = maxDepth;
    }

    /**
     * Generate a code for the given Composite Component.
     * 
     * @param elt the component to generate the code for
     * 
     * @return The generated code.
     */
    public String generateCode(final AbstractElement elt)
    {
        final Stack<String> parents = new Stack<String>();
        if (!elt.isRoot())
        {
            AbstractElement parent = elt.getParent();
            while (!parent.isRoot())
            {
                parents.push(parent.getId());
                parent = parent.getParent();
            }
        }
        final int depth = parents.size() + 1;
        final int append = mMaxDepth - parents.size() - 1;
        String code = ""; //$NON-NLS-1$ - Empty string
        while (!parents.isEmpty())
        {
            code += parents.pop();
            code += ELT_SEPARATOR;
        }
        code += elt.getId();
        for (int i=0; i<append; i++)
        {
            code += ELT_SEPARATOR + EMPTY_ELT;
        }
        for (int i=0; i<depth; i++)
        {
            code += INDENT;
        }
        code += elt.getDescription();
        return code;
    }

    @Override
    public String toString()
    {
        return "CodeGenerator" + //$NON-NLS-1$
                "(" + //$NON-NLS-1$
                mMaxDepth +
                ")"; //$NON-NLS-1$
    }
}
