package org.gbview.io.gbx.definition;

import org.gbview.model.*;
import org.gbview.model.DisplayMode;
import org.gbview.model.particle.CubicBox;

import java.awt.*;

/**
 * Defines a simulation box
 */
public class GbBoxDefinition implements GbDefinition
{

    private double lx, ly, lz;
    private Color defaultColor = Color.WHITE;

    public GbBoxDefinition(double lx, double ly, double lz)
    {
        this.lx = lx;
        this.ly = ly;
        this.lz = lz;
    }

    public double getLx()
    {
        return lx;
    }

    public void setLx(double lx)
    {
        this.lx = lx;
    }

    public double getLy()
    {
        return ly;
    }

    public void setLy(double ly)
    {
        this.ly = ly;
    }

    public double getLz()
    {
        return lz;
    }

    public void setLz(double lz)
    {
        this.lz = lz;
    }

    @Override
    public String toString()
    {
        return "GbBoxDefinition{" +
                "lx=" + lx +
                ", ly=" + ly +
                ", lz=" + lz +
                '}';
    }

    public CubicBox createBox()
    {
        CubicBox box = new CubicBox(0,0,0);
        box.setLx(this.lx);
        box.setLy(this.ly);
        box.setLz(this.lz);
        box.setColour(defaultColor);
        box.setDisplayMode(DisplayMode.WIREFRAME);
        return box;
    }

}
