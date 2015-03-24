/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.Element;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IEntityFactory;
import au.com.shawware.patterns.build.IMod;
import au.com.shawware.patterns.build.IWeapon;
import au.com.shawware.patterns.build.ModType;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Defines an entity factory for the Resistance faction.
 * Acts as an <em>Abstract Factory</em> accessed via a <em>Singleton</em>.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ResistanceEntityFactory extends Element implements IEntityFactory
{
    /** The singleton instance of this abstract factory. */
    private static ResistanceEntityFactory sFactory = null;

    /** Prevent external construction. */
    private ResistanceEntityFactory()
    {
        super(Faction.RESISTANCE);
    }

    /**
     * @return The singleton instance of this factory.
     */
    public static synchronized IEntityFactory getFactory()
    {
        if (sFactory == null)
        {
            sFactory = new ResistanceEntityFactory();
        }
        return sFactory;
    }

    @Override
    public IWeapon createWeapon(final int level, final WeaponType type)
    {
        return new RWeapon(level, type);
    }

    @Override
    public IMod createMod(int level, ModType type)
    {
        return new RMod(level, type);
    }
}
