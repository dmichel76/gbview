package org.gbview.model;

import org.gbview.model.particle.CubicBox;
import org.gbview.model.particle.Sphere;
import org.gbview.model.properties.Properties;
import org.gbview.model.properties.SphereProperties;
import org.gbview.model.util.ColourDefaults;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexRange;

import java.awt.Color;

/**
 * Sample model used for development
 */
public class RandomModel extends AbstractModel implements Model
{
    public RandomModel()
    {
        super();

        // create a bunch of particle
        // with random coordinates
        // and put in the <code>particles</code> map with associated indexes
        for(int i=0; i<40; i++)
        {
            double x =  2 * Math.random() - 1;
            double y =  2 * Math.random() - 1;
            double z =  2 * Math.random() - 1;

            Sphere particle = new Sphere(x,y,z);
            particle.setDiameter(0.1);

            ColourDefaults c = ColourDefaults.getNext();
            particle.setColour(c.getColour());

            this.particles.put(new Index(i),particle);
        }

        // apply a bunch of properties to a given index range
        SphereProperties prop = new SphereProperties();
        prop.setDiameter(0.3);
        prop.setColour(Color.BLUE);
        prop.setDisplayMode(DisplayMode.WIREFRAME);
        prop.setVisible(Properties.VISIBLE);
        prop.apply(this, new IndexRange(new Index(5),new Index(9)));

        SphereProperties prop2 = new SphereProperties();
        prop2.setDiameter(0.4);
        prop2.setVisible(Properties.VISIBLE);
        prop2.apply(this, new IndexRange(new Index(3),new Index(4)));

        // make a simulation box
        this.box = new CubicBox(0.0, 0.0, 0.0);
        this.box.setColour(Color.WHITE);
        this.box.setDisplayMode(DisplayMode.WIREFRAME);
        this.box.setLx(1.0);
        this.box.setLy(1.0);
        this.box.setLz(1.0);
    }
}
