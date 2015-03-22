/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.build;

/**
 * Specify the types of mods in our game.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public enum ModType
{
    /** Increases a portal's defenses. */
    SHIELD,
    /** Reduces a portal's cool down time. */
    HEAT_SINK,
    /** Increases a portal's hackability. */
    MULTI_HACK
}
