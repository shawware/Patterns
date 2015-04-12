/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Abstracts the common elements of a game resonator.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class Resonator extends Entity implements IResonator
{
    /**
     * Construct a new resonator.
     * 
     * @param faction the new resonator's faction
     * @param level the new resonator's level
     */
    public Resonator(final Faction faction, final int level)
    {
        super(faction, level);
    }

    @Override
    public String toString()
    {
        return Resonator.class.getSimpleName() +
               '(' + getFaction() + ',' + ' ' +
               getLevel() + ')';
    }
}

