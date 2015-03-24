/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Source of {@link IWeapon}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class AbstractWeaponFactory extends Element implements IWeaponFactory
{
    /**
     * Constructs a new factory for the given faction.
     * 
     * @param faction the new factory's faction.
     */
    protected AbstractWeaponFactory(final Faction faction)
    {
        super(faction);
    }

    @Override
    public IWeapon createWeapon(final int level, final WeaponType type)
    {
        return manufactureWeapon(level, type);
    }

    /**
     * Manufactures a new weapon with the given attributes.
     * The sub-classes can decide the class of weapon.
     * 
     * @param level the new weapon's level
     * @param type the new weapon's type
     * 
     * @return The new weapon.
     */
    protected abstract IWeapon manufactureWeapon(final int level, final WeaponType type);
}
