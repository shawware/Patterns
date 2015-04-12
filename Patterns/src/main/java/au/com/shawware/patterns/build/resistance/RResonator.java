/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.Resonator;

/**
 * Defines an Resistance resonator.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class RResonator extends Resonator
{
   /**
    * Constructs a new resonator for the resistance faction
    * with the given level.
    * 
    * @param level the new resonator's level
    */
   public RResonator(final int level)
   {
       super(Faction.RESISTANCE, level);
   }
}
