/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import java.util.List;

import au.com.shawware.util.SwAssert;

/**
 * Abstracts the common elements of building an inventory.
 * Uses the <em>Factory Method</em> and <em>Template Method</em> patterns.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class AbstractInventoryFactory extends Element implements IInventoryFactory
{
    /**
     * Constructs a new inventory factory.
     * 
     * @param faction the faction the inventory and its entities will belong to
     */
    public AbstractInventoryFactory(final Faction faction)
    {
        super(faction);
    }

    // A Template Method.
    @Override
    public Inventory createInventory(final List<Instruction> instructions)
    {
        SwAssert.notNull(instructions);

        final Inventory inventory = new Inventory(getFaction());
        for (Instruction i : instructions)
        {
            SwAssert.notNull(i);

            if (i.getEntityType().equals(EntityType.MOD))
            {
                inventory.addMod(createMod(i.getLevel(), i.getModType()));
            }
            else
            {
                inventory.addWeapon(createWeapon(i.getLevel(), i.getWeaponType()));
            }
        }
        return inventory;
    }

    /**
     * Creates a new mod in our game.
     * 
     * Duplicates {@link IEntityFactory#createMod(int, ModType)}.
     * 
     * @param level the new mod's level
     * @param type the new mod's type
     * 
     * @return The new mod.
     */
    protected abstract IMod createMod(final int level, final ModType type);

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
    protected abstract IWeapon createWeapon(final int level, final WeaponType type);
}
