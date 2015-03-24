/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.AbstractWeaponFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IWeapon;
import au.com.shawware.patterns.build.WeaponType;

/**
 * Source of Resistance {@link IWeapon}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ResistanceWeaponFactory extends AbstractWeaponFactory
{
    /**
     * Constructs a new factory.
     */
    public ResistanceWeaponFactory()
    {
        super(Faction.RESISTANCE);
    }

    @Override
    protected IWeapon manufactureWeapon(final int level, final WeaponType type)
    {
        return new RWeapon(level, type);
    }
}

