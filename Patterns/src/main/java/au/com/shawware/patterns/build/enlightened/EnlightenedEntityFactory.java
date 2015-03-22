/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.enlightened;

import au.com.shawware.patterns.build.Element;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IEntityFactory;
import au.com.shawware.patterns.build.IMod;
import au.com.shawware.patterns.build.IWeapon;
import au.com.shawware.patterns.build.ModType;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Defines an entity factory for the Enlightened faction.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class EnlightenedEntityFactory extends Element implements IEntityFactory
{
    /**
     * Constructor.
     */
    public EnlightenedEntityFactory()
    {
        super(Faction.ENLIGHTENED);
    }

    @Override
    public IWeapon createWeapon(final int level, final WeaponType type)
    {
        return new EWeapon(level, type);
    }

    @Override
    public IMod createMod(int level, ModType type)
    {
        return new EMod(level, type);
    }
}
