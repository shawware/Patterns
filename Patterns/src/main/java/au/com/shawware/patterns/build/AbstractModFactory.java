/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Source of {@link IMod}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public abstract class AbstractModFactory extends Element implements IModFactory
{
    /**
     * Constructs a new factory for the given faction.
     * 
     * @param faction the new factory's faction.
     */
    protected AbstractModFactory(final Faction faction)
    {
        super(faction);
    }

    @Override
    public IMod createMod(final int level, final ModType type)
    {
        return manufactureMod(level, type);
    }

    /**
     * Manufactures a new mod with the given attributes.
     * The sub-classes can decide the class of mod.
     * 
     * @param level the new mod's level
     * @param type the new mod's type
     * 
     * @return The new mod.
     */
    protected abstract IMod manufactureMod(final int level, final ModType type);
}
