/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import au.com.shawware.util.SwAssert;

/**
 * Abstracts the common elements of a game entity.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class Entity extends Element implements IEntity
{
    /** The entity's level. */
    private final int mLevel;

    /**
     * Constructs a new entity for the given faction.
     *
     * @param faction the entity's faction
     * @param level the entity's level
     */
    public Entity(final Faction faction, final int level)
    {
        super(faction);

        SwAssert.True((level > 0) && (level <= 8), "invalid level: " + level); //$NON-NLS-1$

        mLevel = level;
    }

    @Override
    public final int getLevel()
    {
        return mLevel;
    }

    @Override
    public String toString()
    {
        return Entity.class.getSimpleName() +
               '(' + getFaction() + ',' + ' ' + getLevel() + ')';
    }
}
