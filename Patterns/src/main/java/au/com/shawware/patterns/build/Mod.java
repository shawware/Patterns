/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import au.com.shawware.util.SwAssert;

/**
 * Abstracts the common elements of a game mod.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class Mod extends Entity implements IMod
{
    /** The mod's type. */
    private final ModType mType;

    /**
     * Construct a new mod.
     * 
     * @param faction the new mod's faction
     * @param level the new mod's level
     * @param type the new mod's type
     */
    public Mod(final Faction faction, final int level, final ModType type)
    {
        super(faction, level);

        SwAssert.notNull(type);

        mType = type;
    }

    @Override
    public ModType getType()
    {
        return mType;
    }

    @Override
    public String toString()
    {
        return Mod.class.getSimpleName() +
               '(' + getFaction() + ',' + ' ' +
               getLevel() + ','  + ' ' +
               getType() + ')';
    }
}
