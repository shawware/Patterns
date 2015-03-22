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
    /** Holds the weapons. */
    private final List<IWeapon> mWeapons;
    /** Holds the mods. */
    private final List<IMod> mMods;

    /**
     * Construct a new inventory.
     * 
     * @param faction the player's faction
     */
    public Inventory(final Faction faction)
    {
        super(faction);

        mWeapons = new ArrayList<IWeapon>();
        mMods = new ArrayList<IMod>();
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
     * Asserts that the given entity has the correct faction for this inventory.
     * 
     * @param entity the entity to test
     */
    private void checkFaction(final IEntity entity)
    {
        SwAssert.True(entity.getFaction().equals(getFaction()), "wrong faction"); //$NON-NLS-1$
    }

    /**
     * @return The number of items in this inventory.
     */
    public int numberOfItems()
    {
        return mWeapons.size() + mMods.size();
    }

    /**
     * @return The number of weapons in this inventory.
     */
    public int numberOfWeapons()
    {
        return mWeapons.size();
    }

    /**
     * @return The number of mods in this inventory.
     */
    public int numberOfMods()
    {
        return mMods.size();
    }

    @Override
    public String toString()
    {
        return Inventory.class.getSimpleName() + '(' + getFaction() + ')';
    }
}
