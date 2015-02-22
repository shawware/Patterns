/*
 * Copyright (C) 2015 shawware.com.au
 *
 * License: GNU General Public License V3 (or later)
 * http://www.gnu.org/copyleft/gpl.html
 */

package au.com.shawware.patterns;

import au.com.shawware.patterns.cvi.CodeAggregator;
import au.com.shawware.patterns.cvi.LineItem;

/**
 * A collection of code snippets for assisting with development and testing.
 *
 * @author <a href="mailto:david.shaw@shawware.com.au">David Shaw</a>
 */
@SuppressWarnings({ "static-method", "nls" })
public class Snippets
{
    /**
     * Entry point.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        final Snippets me = new Snippets();
        me.run();
    }

    /**
     * Does the actual work.
     */
    private void run()
    {
        veryBasicStuff();
    }

    private void veryBasicStuff()
    {
        System.out.println("Basic construction");
        final LineItem lla = new LineItem("10", "LL-A");
        System.out.println(lla);
        final LineItem llb = new LineItem("20", "LL-B");
        System.out.println(llb);
        final CodeAggregator ss = new CodeAggregator("20", "SS-B");
        System.out.println(ss);
        final CodeAggregator aa = new CodeAggregator("10", "AA-A");
        System.out.println(aa);
        aa.addChild(ss);
        ss.addChild(lla);
        ss.addChild(llb);
        System.out.println("Child / Parent established");
        System.out.println(lla);
        System.out.println(llb);
        System.out.println(ss);
        System.out.println(aa);
    }
}
