/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.enlightened;

import au.com.shawware.patterns.build.AbstractModFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IMod;
import au.com.shawware.patterns.build.ModType;

/**
 * Source of Enlightened {@link IMod}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class EnlightenedModFactory extends AbstractModFactory
{
    /**
     * Constructs a new factory.
     */
    public EnlightenedModFactory()
    {
        super(Faction.ENLIGHTENED);
    }

    @Override
    protected IMod manufactureMod(final int level, final ModType type)
    {
        return new EMod(level, type);
    }
}

