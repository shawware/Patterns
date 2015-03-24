/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Defines the weapon creation method on a single factory interface.
 * Forms part of the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IWeaponFactory extends IElement
{
    /**
     * Creates a new weapon in our game.
     * 
     * Duplicates {@link IEntityFactory#createWeapon(int, WeaponType)}.
     * 
     * @param level the new weapon's level
     * @param type the new weapon's type
     * 
     * @return The new weapon.
     */
    public IWeapon createWeapon(final int level, final WeaponType type);
}
