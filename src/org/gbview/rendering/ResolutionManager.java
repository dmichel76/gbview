package org.gbview.rendering;

/**
 * Singleton to manage the resolution of OpenGL objects depending on their relative size.
 * The overall resolution can be increased or decreased by changing the <code>base</code>.
 * The dependence of the objects size can be changes by modifying the <code>amplitude</code>.
 */
public class ResolutionManager
{

    private int base_shift;
    private int base = 8;
    private int amplitude = 5;

    private double[] size_arr = {0.01,0.02,0.05,0.10,0.20,0.30,0.50};
    private int[] resolution_arr = {-amplitude,-amplitude/2,-amplitude/4,0,+amplitude/2,+amplitude/4,+amplitude};

    private double[] zoom_arr = {-5.0,-4.0,-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0,4.0,5.0,7.0,10.0};
    private int[] base_arr = {-3,-2,-2,-1,0,0,1,2,4,6,8,10,14};

    private static final ResolutionManager instance = new ResolutionManager();

    public static ResolutionManager getInstance()
    {
        return instance;
    }

    private ResolutionManager()
    {
        // Singleton: private constructor to prevent instantiation
    }

    public int getResolutionFromSize(double size)
    {
        int tmp = base + base_shift + interpolate(size,this.size_arr,this.resolution_arr);
        int res =  tmp>0 ? tmp : base + base_shift;

        return res;
    }

    public void setBaseFromZoom(double zoom)
    {
        base_shift = interpolate(zoom,this.zoom_arr,this.base_arr);
    }

    int interpolate(double target, double[] keys, int[] values)
    {
        // sanity check
        if(keys.length != values.length)
        {
            throw new IllegalStateException("Internal error");
        }

        int shift = values[0];
        for(int i=0;i<keys.length;i++)
        {
            if(target>keys[i])
            {
                shift = values[i];
            }

            if(target<=keys[i])
            {
                shift = values[i];
                break;
            }

        }

        return shift;
    }

    public int getBase()
    {
        return base + base_shift;
    }

    public int getAmplitude()
    {
        return amplitude;
    }
}
