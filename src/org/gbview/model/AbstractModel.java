package org.gbview.model;

import org.gbview.model.particle.CubicBox;
import org.gbview.model.particle.Particle;
import org.gbview.model.particle.Sphere;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexRange;

import javax.media.opengl.GL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract class containing common methods used by implementation of {@link Model}.
 */
public abstract class AbstractModel implements Model
{
    CubicBox box;
    boolean boxFlag;
    Map<Index,Particle> particles;

    private double total_x = 0.0;
    private double total_y = 0.0;
    private double total_z = 0.0;

    public AbstractModel()
    {
        this.boxFlag = false;
        this.particles = new LinkedHashMap<Index,Particle>();
    }

    @Override
    public void draw(GL gl)
    {
        if (this.boxFlag) { this.box.draw(gl); }

        for(Map.Entry<Index,Particle> entry : this.particles.entrySet())
        {
            Particle p = entry.getValue();
            p.draw(gl);
        }
    }

    @Override
    public void setParticles(Map<Index,Particle> particles)
    {
        this.particles.clear();
        this.particles.putAll(particles);

        for (Map.Entry<Index, Particle> item : this.particles.entrySet())
        {
            Particle p = item.getValue();

            double x = p.getX();
            double y = p.getY();
            double z = p.getZ();

            this.total_x += x;
            this.total_y += y;
            this.total_z += z;
        }

    }

    @Override
    public Map<Index, Particle> getParticles()
    {
        return particles;
    }

    @Override
    public void addParticle(Index i, Particle p)
    {
        this.particles.put(i,p);

        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();

        this.total_x += x;
        this.total_y += y;
        this.total_z += z;
    }

    @Override
    public void setBox(CubicBox box)
    {
        this.box = box;
        this.boxFlag = true;
    }

    @Override
    public CubicBox getBox()
    {
        return box;
    }

    @Override
    public IndexRange getIndexRange()
    {
        return new IndexRange(new Index(0),new Index(particles.size()-1));
    }

    @Override
    public int getSize()
    {
        return this.particles.size();
    }

    @Override
    public String toString() {

        String result = "";

        for (Map.Entry<Index, Particle> item : this.particles.entrySet()) {
            Index index = item.getKey();
            Particle p = item.getValue();

            result += "index:" + index.getValue() + " - " + "particle: " + p.toString();
            result += "\n";
        }

        return result;
    }

    @Override
    public void normalise()
    {
        //re-center around the system's center of mass
        if ( this.boxFlag==false ) {
            center_around_com();
        }

        // find maximum value in system
        double max = -Double.MAX_VALUE;
        for (Map.Entry<Index, Particle> item : this.particles.entrySet())
        {
            Particle p = item.getValue();
            double x = p.getX(); if (x > max) max=x;
            double y = p.getY(); if (y > max) max=y;
            double z = p.getZ(); if (z > max) max=z;
        }
        if (this.boxFlag)
        {
            double lx = this.box.getLx(); if (lx > max) max=lx;
            double ly = this.box.getLy(); if (ly > max) max=ly;
            double lz = this.box.getLz(); if (lz > max) max=lz;
        }

        // normalise to that found maximum value
        for (Map.Entry<Index, Particle> item : this.particles.entrySet())
        {
            Particle p = item.getValue();
            p.normalise(max);
            item.setValue(p);
        }
        if (this.boxFlag) this.box.normalise(max);

    }

    public void center_around_com() {
        // center around center of mass
        for (Map.Entry<Index, Particle> item : this.particles.entrySet())
        {
            Particle p = item.getValue();
            p.setX(p.getX() - this.total_x / this.particles.size());
            p.setY(p.getY() - this.total_y / this.particles.size());
            p.setZ(p.getZ() - this.total_z / this.particles.size());
        }
    }
}
