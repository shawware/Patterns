/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.resistance;

import au.com.shawware.patterns.build.AbstractResonatorFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IResonator;

/**
 * Source of Resistance {@link IResonator}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class ResistanceResonatorFactory extends AbstractResonatorFactory
{
    /**
     * Constructs a new factory.
     */
    public ResistanceResonatorFactory()
    {
        super(Faction.RESISTANCE);
    }

    @Override
    protected IResonator manufactureResonator(final int level)
    {
        return new RResonator(level);
    }
}

