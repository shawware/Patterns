/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import au.com.shawware.util.SwAssert;

/**
 * Abstracts the common elements of a game weapon.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class Weapon extends Entity implements IWeapon
{
    /** The weapon's type. */
    private final WeaponType mType;

    /**
     * Construct a new weapon.
     * 
     * @param faction the new weapon's faction
     * @param level the new weapon's level
     * @param type the new weapon's type
     */
    public Weapon(final Faction faction, final int level, final WeaponType type)
    {
        super(faction, level);

        SwAssert.notNull(type);

        mType = type;
    }

    @Override
    public final WeaponType getType()
    {
        return mType;
    }

    @Override
    public String toString()
    {
        return Weapon.class.getSimpleName() +
               '(' + getFaction() + ',' + ' ' +
               getLevel() + ','  + ' ' +
               getType() + ')';
    }
}
