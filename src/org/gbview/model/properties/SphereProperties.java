package org.gbview.model.properties;

import org.gbview.model.particle.Particle;
import org.gbview.model.particle.Sphere;

import java.awt.Color;

/**
 * Collection of properties for a SPHERE defined in {@link org.gbview.model.type.ParticleType}.
 */
public class SphereProperties extends AbstractProperties
{
    private double diameter;
    private Color colour;

    private boolean diameterFlag = false;
    private boolean colourFlag = false;

    public void setDiameter(double diameter)
    {
        this.diameter = diameter;
        this.diameterFlag = true;
    }

    public void setColour(Color colour)
    {
        this.colour = colour;
        this.colourFlag = true;
    }

    @Override
    protected void applyImpl(Particle particle)
    {
        Sphere sphere = (Sphere) particle;

        // if a colour is set in this property, override the default colour of particle
        if(this.colourFlag) sphere.setColour(this.colour);

        // if a diameter is set in this property, override the default diameter  of particle
        if(this.diameterFlag) sphere.setDiameter(this.diameter);
    }


}
