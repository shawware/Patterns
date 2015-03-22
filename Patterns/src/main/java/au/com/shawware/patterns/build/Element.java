/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import au.com.shawware.util.SwAssert;

/**
 * Abstracts the common elements of a game element.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class Element implements IElement
{
    /** The element's faction. */
    private final Faction mFaction;

    /**
     * Constructs a new element for the given faction.
     *
     * @param faction the entity's faction
     */
    public Element(final Faction faction)
    {
        SwAssert.notNull(faction);

        mFaction = faction;
    }

    @Override
    public final Faction getFaction()
    {
        return mFaction;
    }

    @Override
    public String toString()
    {
        return Element.class.getSimpleName() + '(' + getFaction() + ')';
    }
}
