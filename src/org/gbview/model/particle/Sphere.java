package org.gbview.model.particle;

import org.gbview.model.util.ColourDefaults;
import org.gbview.rendering.GL.GLDot;
import org.gbview.rendering.GL.GLObject;
import org.gbview.rendering.GL.GLSphere;
import org.gbview.rendering.ResolutionManager;

import javax.media.opengl.GL;
import java.awt.*;

/**
 * A Sphere is a simple {@link AbstractParticle} that has a
 * <code>color</code> field and a <code>diameter</</code> field.
 */
public class Sphere extends AbstractParticle
{
    protected Color colour = ColourDefaults.getDefault().getColour();
    protected double diameter;

    public Sphere(double x, double y, double z)
    {
        super(x, y, z);
    }

    public void setColour(Color colour)
    {
        this.colour = colour;
    }

    public Color getColour()
    {
        return colour;
    }

    public double getDiameter()
    {
        return diameter;
    }

    public void setDiameter(double diameter)
    {
        this.diameter = diameter;
    }

    @Override
    public void normalise(double norm)
    {
        this.x /= norm;
        this.y /= norm;
        this.z /= norm;
        this.diameter /= norm;
    }

    @Override
    public String toString()
    {
        return "[x=" + x + ", y=" + y + ", z=" + z +
                ", colour=" + colour + ", diameter=" + diameter +
                ", displayMode=" + displayMode;
    }

    @Override
    public void drawFill(GL gl)
    {
        GLObject aSphere = new GLSphere();
        aSphere.setCoordinates(x,y,z);
        aSphere.setScale(diameter);
        aSphere.setColour(colour);

        // TODO perhaps this can move up to the abstract class?
        ResolutionManager resolutionManager = ResolutionManager.getInstance();
        int res = resolutionManager.getResolutionFromSize(diameter);
        aSphere.setResolution(res);

        aSphere.drawMe(gl);
    }

    @Override
    public void drawDot(GL gl)
    {
        GLObject aDot = new GLDot();
        aDot.setCoordinates(x,y,z);
        aDot.setColour(colour);
        aDot.setScale(diameter);
        aDot.drawMe(gl);
    }

    @Override
    public void drawLine(GL gl)
    {
        throw new UnsupportedOperationException();
    }

}
