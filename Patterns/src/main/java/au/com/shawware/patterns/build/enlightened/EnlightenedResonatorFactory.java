/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build.enlightened;

import au.com.shawware.patterns.build.AbstractResonatorFactory;
import au.com.shawware.patterns.build.Faction;
import au.com.shawware.patterns.build.IResonator;

/**
 * Source of Enlightened {@link IResonator}s using the <em>Factory Method</em> pattern.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public class EnlightenedResonatorFactory extends AbstractResonatorFactory
{
    /**
     * Constructs a new factory.
     */
    public EnlightenedResonatorFactory()
    {
        super(Faction.ENLIGHTENED);
    }

    @Override
    protected IResonator manufactureResonator(final int level)
    {
        return new EResonator(level);
    }
}