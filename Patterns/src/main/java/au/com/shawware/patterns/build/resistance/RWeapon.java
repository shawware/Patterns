/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.Weapon;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Defines a Resistance weapon.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class RWeapon extends Weapon
{
    /**
     * Constructs a new weapon for the resistance faction
     * with the given level and type.
     * 
     * @param level the new weapon's level
     * @param type the new weapon's type
     */
    public RWeapon(final int level, final WeaponType type)
    {
        super(Faction.RESISTANCE, level, type);
    }
}
