/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Defines the mod creation method on a single factory interface.
 * Forms part of the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public interface IModFactory extends IElement
{
    /**
     * Creates a new mod in our game.
     * 
     * Duplicates {@link IEntityFactory#createMod(int, ModType)}.
     * 
     * @param level the new mod's level
     * @param type the new mod's type
     * 
     * @return The new mod.
     */
    public IMod createMod(final int level, final ModType type);
}