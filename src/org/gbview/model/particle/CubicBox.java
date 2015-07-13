package org.gbview.model.particle;

import org.gbview.model.*;
import org.gbview.model.DisplayMode;
import org.gbview.model.particle.AbstractParticle;
import org.gbview.model.util.ColourDefaults;
import org.gbview.rendering.GL.GLCube;
import org.gbview.rendering.GL.GLDot;

import javax.media.opengl.GL;
import java.awt.*;

/**
 *
 */
public class CubicBox extends AbstractParticle
{
    private double lx;
    private double ly;
    private double lz;

    private Color colour;

    public CubicBox(double x, double y, double z, double lx, double ly, double lz)
    {
        super(x, y, z);

        this.setLx(lx);
        this.setLy(ly);
        this.setLz(lz);

        this.setVisible(true);
        this.setDisplayMode(DisplayMode.WIREFRAME);
        this.setColour(ColourDefaults.WHITE.getColour());
    }

    public CubicBox(double x, double y, double z)
    {
        this(x, y, z, 1.0, 1.0, 1.0);
    }

    public CubicBox()
    {
        this(0.0, 0.0, 0.0);
    }

    public void setColour(Color colour)
    {
        this.colour = colour;
    }

    public double getLx() {return this.lx;}
    public void setLx(double lx)
    {
        this.lx = lx;
    }

    public double getLy() {return this.ly;}
    public void setLy(double ly)
    {
        this.ly = ly;
    }

    public double getLz() {return this.lz;}
    public void setLz(double lz)
    {
        this.lz = lz;
    }

    @Override
    public void normalise(double norm) {
        this.lx /= norm;
        this.ly /= norm;
        this.lz /= norm;
    }

    @Override
    public String toString()
    {
        return "[lx=" + lx + ", ly=" + ly + ", lz=" + lz +
                ", colour=" + colour + ", displayMode=" + displayMode;
    }

    @Override
    public void drawFill(GL gl)
    {
        GLCube aCube = new GLCube();
        aCube.setCoordinates(x,y,z);
        aCube.setScale(lx,ly,lz);
        aCube.setColour(colour);
        aCube.drawMe(gl);
    }

    @Override
    public void drawDot(GL gl)
    {
        GLDot aDot = new GLDot();
        aDot.setCoordinates(x,y,z);
        aDot.setScale(lx);
        aDot.setColour(colour);
        aDot.drawMe(gl);
    }

    @Override
    public void drawLine(GL gl)
    {
        throw new UnsupportedOperationException();
    }

}
