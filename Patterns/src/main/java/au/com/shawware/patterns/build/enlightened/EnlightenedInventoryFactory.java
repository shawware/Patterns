/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.enlightened;

import au.com.shawware.patterns.build.AbstractInventoryFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IInventoryFactory;
import au.com.shawware.patterns.build.IMod;
import au.com.shawware.patterns.build.IResonator;
import au.com.shawware.patterns.build.IWeapon;
import au.com.shawware.patterns.build.ModType;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Implements an enlightened inventory factory as a <em>Factory Method</em>.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class EnlightenedInventoryFactory extends AbstractInventoryFactory
{
    /** The singleton instance of this abstract factory. */
    private static EnlightenedInventoryFactory sFactory = null;

    /** Prevent external construction. */
    private EnlightenedInventoryFactory()
    {
        super(Faction.ENLIGHTENED);
    }

    /**
     * @return The singleton instance of this factory.
     */
    public static synchronized IInventoryFactory getFactory()
    {
        if (sFactory == null)
        {
            sFactory = new EnlightenedInventoryFactory();
        }
        return sFactory;
    }

    @Override
    protected IMod createMod(final int level, final ModType type)
    {
        return new EMod(level, type);
    }

    @Override
    protected IWeapon createWeapon(final int level, final WeaponType type)
    {
        return new EWeapon(level, type);
    }

    @Override
    protected IResonator createResonator(final int level)
    {
        return new EResonator(level);
    }
}
