/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import java.util.ArrayList;
import java.util.List;

import au.com.shawware.util.SwAssert;

/**
 * Holds a player's inventory in our game.
 * Each item in the inventory must match the player's faction.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class Inventory extends Element
{
    /** Holds the mods. */
    private final List<IMod> mMods;
    /** Holds the weapons. */
    private final List<IWeapon> mWeapons;

    /**
     * Construct a new inventory.
     * 
     * @param faction the player's faction
     */
    public Inventory(final Faction faction)
    {
        super(faction);

        mMods = new ArrayList<IMod>();
        mWeapons = new ArrayList<IWeapon>();
    }

    /**
     * Adds a mod to this inventory.
     * 
     * @param mod the mod to add
     */
    public void addMod(final IMod mod)
    {
        SwAssert.notNull(mod);
        checkFaction(mod);

        mMods.add(mod);
    }

    /**
     * Adds a weapon to this inventory.
     * 
     * @param weapon the weapon to add
     */
    public void addWeapon(final IWeapon weapon)
    {
        SwAssert.notNull(weapon);
        checkFaction(weapon);

        mWeapons.add(weapon);
    }

    /**
     * Asserts that the given entity has the correct faction for this inventory.
     * 
     * @param entity the entity to test
     */
    private void checkFaction(final IEntity entity)
    {
        SwAssert.True(entity.getFaction().equals(getFaction()), "wrong faction"); //$NON-NLS-1$
    }

    /**
     * @return The total number of mods in this inventory.
     */
    public int numberOfMods()
    {
        return mMods.size();
    }

    /**
     * @return The number of weapons in this inventory.
     */
    public int numberOfWeapons()
    {
        return mWeapons.size();
    }

    /**
     * @return The number of items in this inventory.
     */
    public int numberOfItems()
    {
        return mMods.size() + mWeapons.size();
    }

    /**
     * Counts the number of mods in this inventory with the given attributes.
     *
     * @param level the level of mod to count
     * @param type the type of mod to count
     *
     * @return The mod count.
     */
    public int numberOfMods(final int level, final ModType type)
    {
        int count = 0;
        for (IMod mod : mMods)
        {
            if ((mod.getLevel() == level) && mod.getType().equals(type))
            {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of weapons in this inventory with the given attributes.
     *
     * @param level the level of weapon to count
     * @param type the type of weapon to count
     *
     * @return The weapon count.
     */
    public int numberOfWeapons(final int level, final WeaponType type)
    {
        int count = 0;
        for (IWeapon weapon : mWeapons)
        {
            if ((weapon.getLevel() == level) && weapon.getType().equals(type))
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString()
    {
        return Inventory.class.getSimpleName() + '(' + getFaction() + ')';
    }
}
