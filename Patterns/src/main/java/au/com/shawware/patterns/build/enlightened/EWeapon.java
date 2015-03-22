/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.enlightened;

import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.Weapon;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Defines an Enlightened weapon.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class EWeapon extends Weapon
{
    /**
     * Constructs a new weapon for the enlightened faction
     * with the given level and type.
     * 
     * @param level the new weapon's level
     * @param type the new weapon's type
     */
    public EWeapon(final int level, final WeaponType type)
    {
        super(Faction.ENLIGHTENED, level, type);
    }
}
