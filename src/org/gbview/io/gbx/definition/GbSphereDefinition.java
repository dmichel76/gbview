package org.gbview.io.gbx.definition;

import org.gbview.model.*;
import org.gbview.model.DisplayMode;
import org.gbview.model.particle.Particle;
import org.gbview.model.type.ParticleType;
import org.gbview.model.particle.Sphere;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexSet;

import java.awt.*;

/**
 * Defines a SPHERE particle
 */
public class GbSphereDefinition implements GbParticleDefinition
{
    String name;
    double diameter;
    Color colour;

    IndexSet indexSet;

    public GbSphereDefinition(String name, double diameter, Color colour)
    {
        this.diameter = diameter;
        this.colour = colour;
        this.name = name;

        this.indexSet = new IndexSet();
    }

    @Override
    public ParticleType getType()
    {
        return ParticleType.SPHERE;
    }

    @Override
    public void setIndexSet(IndexSet indexSet)
    {
        this.indexSet = indexSet;
    }

    @Override
    public void addIndex(Index index)
    {
        this.indexSet.add(index);
    }

    @Override
    public IndexSet getIndexSet()
    {
        return indexSet;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public double getDiameter()
    {
        return diameter;
    }

    public Color getColour()
    {
        return colour;
    }

    @Override
    public Particle createParticle(double ... data)
    {
        double x = data[0];
        double y = data[1];
        double z = data[2];

        Sphere particle = new Sphere(x,y,z);

        particle.setColour(colour);
        particle.setDiameter(diameter);

        return particle;
    }

    @Override
    public String toString()
    {
        return "GbSphereDefinition{" +
                "name='" + name + '\'' +
                ", diameter=" + diameter +
                ", colour=" + colour +
                ", indexSet=" + indexSet +
                '}';
    }
}
