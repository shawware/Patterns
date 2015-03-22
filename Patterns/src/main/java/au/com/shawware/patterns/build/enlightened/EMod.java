/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.enlightened;

import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.Mod;
import au.com.shawware.patterns.build.ModType;

/**
 * Defines an Enlightened mod.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class EMod extends Mod
{
    /**
     * Constructs a new mod for the enlightened faction
     * with the given level and type.
     * 
     * @param level the new mod's level
     * @param type the new mod's type
     */
    public EMod(final int level, final ModType type)
    {
        super(Faction.ENLIGHTENED, level, type);
    }
}
