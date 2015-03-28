/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.AbstractInventoryFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IInventoryFactory;
import au.com.shawware.patterns.build.IMod;
import au.com.shawware.patterns.build.IWeapon;
import au.com.shawware.patterns.build.ModType;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Implements a resistance inventory factory as a <em>Factory Method</em>.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ResistanceInventoryFactory extends AbstractInventoryFactory
{
    /** The singleton instance of this abstract factory. */
    private static ResistanceInventoryFactory sFactory = null;

    /** Prevent external construction. */
    private ResistanceInventoryFactory()
    {
        super(Faction.RESISTANCE);
    }

    /**
     * @return The singleton instance of this factory.
     */
    public static synchronized IInventoryFactory getFactory()
    {
        if (sFactory == null)
        {
            sFactory = new ResistanceInventoryFactory();
        }
        return sFactory;
    }

    @Override
    protected IMod createMod(final int level, final ModType type)
    {
        return new RMod(level, type);
    }

    @Override
    protected IWeapon createWeapon(final int level, final WeaponType type)
    {
        return new RWeapon(level, type);
    }
}
