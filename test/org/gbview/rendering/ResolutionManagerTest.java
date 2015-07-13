package org.gbview.rendering;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 *
 */
public class ResolutionManagerTest
{

    @Test
    public void testInterpolation()
    {
        ResolutionManager rm = ResolutionManager.getInstance();

        double[] keys = {-5.0,-4.0,-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0,4.0,5.0};
        int[] values = {-10,-8,-6,-4,-2,0,2,4,6,8,10};
        double target = -36.4;

        assertEquals(-6,rm.interpolate(-3.5,keys,values));
        assertEquals(-10,rm.interpolate(-5.0,keys,values));
        assertEquals(-10,rm.interpolate(-10.0,keys,values));
        assertEquals(+10,rm.interpolate(5.0,keys,values));
        assertEquals(+2,rm.interpolate(1.0,keys,values));

    }
}
