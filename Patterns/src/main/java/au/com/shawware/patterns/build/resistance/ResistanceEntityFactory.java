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
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ResistanceEntityFactory extends Element implements IEntityFactory
{
    /**
     * Constructor.
     */
    public ResistanceEntityFactory()
    {
        super(Faction.RESISTANCE);
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
