/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Source of {@link IResonator}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class AbstractResonatorFactory extends Element implements IResonatorFactory
{
    /**
     * Constructs a new factory for the given faction.
     * 
     * @param faction the new factory's faction.
     */
    protected AbstractResonatorFactory(final Faction faction)
    {
        super(faction);
    }

    @Override
    public final IResonator createResonator(final int level)
    {
        return manufactureResonator(level);
    }

    /**
     * Manufactures a new resonator with the given attributes.
     * The sub-classes can decide the class of resonator.
     * 
     * @param level the new resonator's level
     * 
     * @return The new resonator.
     */
    protected abstract IResonator manufactureResonator(final int level);
}
