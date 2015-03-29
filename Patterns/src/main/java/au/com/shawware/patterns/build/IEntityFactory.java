/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Defines the entity creation methods on a single factory interface.
 * Forms part of the <em>Abstract Factory</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IEntityFactory extends IElement
{
    /**
     * Creates a new mod in our game.
     * 
     * @param level the new mod's level
     * @param type the new mod's type
     * 
     * @return The new mod.
     */
    public IMod createMod(final int level, final ModType type);

    /**
     * Creates a new weapon in our game.
     * 
     * @param level the new weapon's level
     * @param type the new weapon's type
     * 
     * @return The new weapon.
     */
    public IWeapon createWeapon(final int level, final WeaponType type);
}
