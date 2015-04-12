/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Defines the resonator creation method on a single factory interface.
 * Forms part of the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IResonatorFactory extends IElement
{
    /**
     * Creates a new resonator in our game.
     * 
     * Duplicates {@link IEntityFactory#createResonator(int)}.
     * 
     * @param level the new resonator's level
     * 
     * @return The new resonator.
     */
    public IResonator createResonator(final int level);
}
