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

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import au.com.shawware.patterns.build.enlightened.EnlightenedEntityFactory;
import au.com.shawware.patterns.build.enlightened.EnlightenedInventoryFactory;
import au.com.shawware.patterns.build.enlightened.EnlightenedModFactory;
import au.com.shawware.patterns.build.enlightened.EnlightenedWeaponFactory;
import au.com.shawware.patterns.build.resistance.ResistanceEntityFactory;
import au.com.shawware.patterns.build.resistance.ResistanceInventoryFactory;
import au.com.shawware.patterns.build.resistance.ResistanceModFactory;
import au.com.shawware.patterns.build.resistance.ResistanceWeaponFactory;
import au.com.shawware.util.StringUtil;
import au.com.shawware.util.SwAssert;
import au.com.shawware.util.ValueCounter;

/**
 * Unit tests for exercising our factory pattern classes.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class InventoryUnitTest
{
    /**
     * Primary test fixture - the items to add to the inventory.
     * Must include at least one entity of each type.
     */
    private static final Instruction[] sData = new Instruction[]
    {
        new Instruction(EntityType.MOD, 4, ModType.MULTI_HACK),
        new Instruction(EntityType.WEAPON, 3, WeaponType.XMP_BURSTER),
        new Instruction(EntityType.WEAPON, 4, WeaponType.XMP_BURSTER),
        new Instruction(EntityType.MOD, 7, ModType.HEAT_SINK),
        new Instruction(EntityType.WEAPON, 3, WeaponType.ULTRA_STRIKE),
        new Instruction(EntityType.WEAPON, 3, WeaponType.ULTRA_STRIKE),
        new Instruction(EntityType.WEAPON, 3, WeaponType.ULTRA_STRIKE),
        new Instruction(EntityType.WEAPON, 3, WeaponType.XMP_BURSTER),
        new Instruction(EntityType.WEAPON, 4, WeaponType.XMP_BURSTER),
        new Instruction(EntityType.WEAPON, 5, WeaponType.XMP_BURSTER),
        new Instruction(EntityType.WEAPON, 3, WeaponType.XMP_BURSTER),
        new Instruction(EntityType.MOD, 7, ModType.HEAT_SINK),
        new Instruction(EntityType.MOD, 7, ModType.SHIELD),
        new Instruction(EntityType.MOD, 7, ModType.MULTI_HACK),
        new Instruction(EntityType.MOD, 7, ModType.HEAT_SINK),
        new Instruction(EntityType.WEAPON, 2, WeaponType.PORTAL_REVERSER),
    };

    /**
     * Short list of entities not in the primary test fixture.
     */
    private static final Instruction[] sAbsent = new Instruction[]
    {
        new Instruction(EntityType.MOD, 1, ModType.MULTI_HACK),
        new Instruction(EntityType.WEAPON, 2, WeaponType.XMP_BURSTER),
    };

    // Data about the primary test fixture.
    /** The number of mods in the primary test fixture. */
    private static int sNumMods = 0;
    /** The number of weapons in the primary test fixture. */
    private static int sNumWeapons = 0;
    /** The test fixture as a list. */
    private static List<Instruction> sInstructions;
    /** Count the entity data. */
    private static ValueCounter<String> sCounter;

    /**
     * Calculates the derived test fixture data.
     */
    @BeforeClass
    public static void commonTestData()
    {
        sNumMods = 0;
        sNumWeapons = 0;
        sInstructions = new ArrayList<Instruction>();
        sCounter = new ValueCounter<String>();
        for (int i=0; i<sData.length; i++)
        {
            if (sData[i].getEntityType().equals(EntityType.MOD))
            {
                sNumMods++;
            }
            else
            {
                sNumWeapons++;
            }
            sInstructions.add(sData[i]);
            sCounter.countValue(generateKey(sData[i]));
            for (int j=0; j<sAbsent.length; j++)
            {
                SwAssert.False(sData[i].toString().equals(sAbsent[j].toString()), "oops - absent item in data"); //$NON-NLS-1$
            }
        }
    }

    /**
     * Generates a key based on the given instruction.
     * 
     * @param instruction the instruction to create a key for
     *
     * @return The instruction's key.
     */
    private static String generateKey(final Instruction instruction)
    {
        final String key;
        if (instruction.getEntityType().equals(EntityType.MOD))
        {
            key = StringUtil.generateKey(EntityType.MOD.toString(),
                    Integer.toString(instruction.getLevel()), instruction.getModType().toString());
        }
        else
        {
            key = StringUtil.generateKey(EntityType.WEAPON.toString(),
                    Integer.toString(instruction.getLevel()), instruction.getWeaponType().toString());
        }
        return key;
    }

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

        final IInventoryFactory eFactory = EnlightenedInventoryFactory.getFactory();
        inventoryFactoryMethodTest(eFactory);

        final IInventoryFactory rFactory = ResistanceInventoryFactory.getFactory();
        inventoryFactoryMethodTest(rFactory);
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

        inventory.addWeapon(weapon);
        checkInventory(inventory, 0, 1);

        final IMod mod = factory.createMod(4, ModType.MULTI_HACK);
        checkMod(mod, factory.getFaction(), 4, ModType.MULTI_HACK);

        inventory.addMod(mod);
        checkInventory(inventory, 1, 1);
    }

    /**
     * Conduct some simple tests for the given <em>Factory Method</em> factories.
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

        inventory.addWeapon(weapon);
        checkInventory(inventory, 0, 1);

        final IMod mod = modFactory.createMod(4, ModType.MULTI_HACK);
        checkMod(mod, modFactory.getFaction(), 4, ModType.MULTI_HACK);

        inventory.addMod(mod);
        checkInventory(inventory, 1, 1);
    }

    /**
     * Thoroughly tests the inventory <em>Factory Method</em> factory.
     * 
     * @param factory the factory to test
     */
    @SuppressWarnings("boxing")
    private void inventoryFactoryMethodTest(final IInventoryFactory factory)
    {
        final Inventory inventory = factory.createInventory(sInstructions);
        checkInventory(inventory, sNumMods, sNumWeapons);

        for (int i=0; i<sData.length; i++)
        {
            final int count = count(inventory, sData[i]);
            final String key = generateKey(sData[i]);
            assertThat(count, equalTo(sCounter.count(key)));
        }

        for (int i=0; i<sAbsent.length; i++)
        {
            final int count = count(inventory, sAbsent[i]);
            final String key = generateKey(sAbsent[i]);
            assertThat(count, equalTo(0));
            assertThat(count, equalTo(sCounter.count(key)));
        }
    }

    /**
     * Counts the number of entities in the given inventory created
     * from the given instruction. The instruction may have been issued
     * more than once.
     * 
     * @param inventory the inventory to look in
     * @param instruction the instruction results to check for
     * 
     * @return The number of matching entities.
     */
    @SuppressWarnings("static-method")
    private int count(final Inventory inventory, final Instruction instruction)
    {
        final int count;
        if (instruction.getEntityType().equals(EntityType.MOD))
        {
            count = inventory.numberOfMods(instruction.getLevel(), instruction.getModType());
        }
        else
        {
            count = inventory.numberOfWeapons(instruction.getLevel(), instruction.getWeaponType());
        }
        return count;
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
     * @param numMods the expected number of mods
     * @param numWeapons the expected number of weapons
     */
    @SuppressWarnings({ "boxing", "static-method" })
    private void checkInventory(final Inventory inventory, final int numMods, final int numWeapons)
    {
        assertThat(inventory.numberOfMods(), equalTo(numMods));
        assertThat(inventory.numberOfWeapons(), equalTo(numWeapons));
        assertThat(inventory.numberOfItems(), equalTo(numMods + numWeapons));
    }
}
