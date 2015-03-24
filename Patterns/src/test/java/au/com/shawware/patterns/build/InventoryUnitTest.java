/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import au.com.shawware.patterns.build.enlightened.EnlightenedEntityFactory;
import au.com.shawware.patterns.build.enlightened.EnlightenedModFactory;
import au.com.shawware.patterns.build.enlightened.EnlightenedWeaponFactory;
import au.com.shawware.patterns.build.resistance.ResistanceEntityFactory;
import au.com.shawware.patterns.build.resistance.ResistanceModFactory;
import au.com.shawware.patterns.build.resistance.ResistanceWeaponFactory;

/**
 * Unit tests for exercising our factory pattern classes.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class InventoryUnitTest
{
    /**
     * Test the <em>Abstract Factory</em> pattern code.
     */
    @Test
    public void abstractFactory()
    {
        final IEntityFactory eFactory = EnlightenedEntityFactory.getFactory();
        basicAbstractFactoryTests(eFactory);

        final IEntityFactory rFactory = ResistanceEntityFactory.getFactory();
        basicAbstractFactoryTests(rFactory);
    }

    /**
     * Test the <em>Factory Method</em> pattern code.
     */
    @Test
    public void factoryMethod()
    {
        final IWeaponFactory ewFactory = new EnlightenedWeaponFactory();
        final IModFactory emFactory = new EnlightenedModFactory();
        basicFactoryMethodTests(ewFactory, emFactory);

        final IWeaponFactory rwFactory = new ResistanceWeaponFactory();
        final IModFactory rmFactory = new ResistanceModFactory();
        basicFactoryMethodTests(rwFactory, rmFactory);
    }

    /**
     * Conduct some simple tests for the given <em>Abstract Factory</em>.
     * 
     * @param factory the factory to test
     */
    private void basicAbstractFactoryTests(final IEntityFactory factory)
    {
        final Inventory inventory = new Inventory(factory.getFaction());
        checkInventory(inventory, 0, 0);

        final IWeapon weapon = factory.createWeapon(3, WeaponType.XMP_BURSTER);
        checkWeapon(weapon, factory.getFaction(), 3, WeaponType.XMP_BURSTER);
        System.out.println(weapon);

        inventory.addWeapon(weapon);
        checkInventory(inventory, 1, 0);

        final IMod mod = factory.createMod(4, ModType.MULTI_HACK);
        checkMod(mod, factory.getFaction(), 4, ModType.MULTI_HACK);
        System.out.println(mod);

        inventory.addMod(mod);
        checkInventory(inventory, 1, 1);
    }

    /**
     * COnduct some simple tests for the given <em>Factory Method</em> factories.
     * 
     * @param weaponFactory the weapon factory
     * @param modFactory the mod factory
     */
    private void basicFactoryMethodTests (final IWeaponFactory weaponFactory, final IModFactory modFactory)
    {
        final Inventory inventory = new Inventory(weaponFactory.getFaction());
        checkInventory(inventory, 0, 0);

        final IWeapon weapon = weaponFactory.createWeapon(3, WeaponType.XMP_BURSTER);
        checkWeapon(weapon, weaponFactory.getFaction(), 3, WeaponType.XMP_BURSTER);
        System.out.println(weapon);

        inventory.addWeapon(weapon);
        checkInventory(inventory, 1, 0);

        final IMod mod = modFactory.createMod(4, ModType.MULTI_HACK);
        checkMod(mod, modFactory.getFaction(), 4, ModType.MULTI_HACK);
        System.out.println(mod);

        inventory.addMod(mod);
        checkInventory(inventory, 1, 1);
    }

    /**
     * Verifies the given weapon has the correct faction, level and type.
     * 
     * @param weapon the weapon to test
     * @param faction the expected faction
     * @param level the expected level
     * @param type the expected type
     */
    private void checkWeapon(final IWeapon weapon, final Faction faction, final int level, final WeaponType type)
    {
        checkEntity(weapon, faction, level);
        assertThat(weapon.getType(), equalTo(type));
    }

    /**
     * Verifies the given mod has the correct faction, level and type.
     * 
     * @param mod the mod to test
     * @param faction the expected faction
     * @param level the expected level
     * @param type the expected type
     */
    private void checkMod(final IMod mod, final Faction faction, final int level, final ModType type)
    {
        checkEntity(mod, faction, level);
        assertThat(mod.getType(), equalTo(type));
    }

    /**
     * Verifies the given entity has the correct faction and level.
     * 
     * @param entity the entity to test
     * @param faction the expected faction
     * @param level the expected level
     */
    @SuppressWarnings({ "boxing", "static-method" })
    private void checkEntity(final IEntity entity, final Faction faction, final int level)
    {
        assertThat(entity, notNullValue());
        assertThat(entity.getFaction(), equalTo(faction));
        assertThat(entity.getLevel(), equalTo(level));
    }

    /**
     * Verifies the contents of the inventory are as expected.
     *
     * @param inventory the inventory to test
     * @param numWeapons the expected number of weapons
     * @param numMods the expected number of mods
     */
    @SuppressWarnings({ "boxing", "static-method" })
    private void checkInventory(final Inventory inventory, final int numWeapons, final int numMods)
    {
        assertThat(inventory.numberOfWeapons(), equalTo(numWeapons));
        assertThat(inventory.numberOfMods(), equalTo(numMods));
        assertThat(inventory.numberOfItems(), equalTo(numWeapons + numMods));
    }
}
