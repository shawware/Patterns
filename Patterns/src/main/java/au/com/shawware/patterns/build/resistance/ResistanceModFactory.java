/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.AbstractModFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IMod;
import au.com.shawware.patterns.build.ModType;

/**
 * Source of Resistance {@link IMod}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ResistanceModFactory extends AbstractModFactory
{
    /**
     * Constructs a new factory.
     */
    public ResistanceModFactory()
    {
        super(Faction.RESISTANCE);
    }

    @Override
    protected IMod manufactureMod(final int level, final ModType type)
    {
        return new RMod(level, type);
    }
}
