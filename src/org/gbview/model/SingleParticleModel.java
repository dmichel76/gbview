package org.gbview.model;

import org.gbview.model.particle.CubicBox;
import org.gbview.model.particle.Sphere;
import org.gbview.model.properties.Properties;
import org.gbview.model.properties.SphereProperties;
import org.gbview.model.util.ColourDefaults;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexRange;

import java.awt.*;

/**
 * Sample model used for development
 */
public class SingleParticleModel extends AbstractModel implements Model
{
    public SingleParticleModel()
    {
        super();

        // create just one particle and put in in the centre
        Sphere sphere = new Sphere(0,0,0);
        sphere.setDiameter(0.4);
        this.particles.put(new Index(0),sphere);

        // make a simulation box
        this.box = new CubicBox(0.0, 0.0, 0.0);
        this.box.setColour(Color.WHITE);
        this.box.setDisplayMode(DisplayMode.WIREFRAME);
        this.box.setLx(1.0);
        this.box.setLy(1.0);
        this.box.setLz(1.0);
    }
}
