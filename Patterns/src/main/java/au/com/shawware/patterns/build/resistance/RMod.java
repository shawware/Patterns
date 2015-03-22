/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.Mod;
import au.com.shawware.patterns.build.ModType;

/**
 * Defines a Resistance mod.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class RMod extends Mod
{
    /**
     * Constructs a new mod for the resistance faction
     * with the given level and type.
     * 
     * @param level the new mod's level
     * @param type the new mod's type
     */
    public RMod(final int level, final ModType type)
    {
        super(Faction.RESISTANCE, level, type);
    }
}