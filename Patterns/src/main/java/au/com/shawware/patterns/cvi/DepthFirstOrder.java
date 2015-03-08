/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns.cvi;

/**
 * Define the depth-first iteration orders for {@link DepthFirstIterator}.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
public enum DepthFirstOrder
{
    /** Parent first, then children from left to right. */
    PRE_ORDER,
    /** Child on left, then parent, then child on right. Doesn't really work for n-ary tree. */
    IN_ORDER,
    /** Children from left to right first, then parent. */
    POST_ORDER
}
