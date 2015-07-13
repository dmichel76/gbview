package org.gbview.rendering.GL;

import javax.media.opengl.GL;
import java.awt.*;

/**
 *
 */
public abstract class abstractGLObject implements GLObject
{
    Color colour;
    int resolution;
    double coord_x, coord_y, coord_z;
    double scale, scaleX, scaleY, scaleZ;

    /**
     * Constructor. Sets the default values for diameter, colour and displaying mode.
     */
    public abstractGLObject()
    {
        // set the default parameters
        colour = Color.BLUE;
        resolution = 10;
        scale = scaleX = scaleY = scaleZ = 1.0;
        coord_x = coord_y = coord_z = 0.0;
    }

    @Override
    public void setCoordinates(double x, double y, double z)
    {
        this.coord_x = x;
        this.coord_y = y;
        this.coord_z = z;
    }

    @Override
    public void setScale(double scale)
    {
        this.scale = scale;
    }

    @Override
    public void setScale(double scaleX, double scaleY, double scaleZ)
    {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    @Override
    public void setColour(Color rgb)
    {
        colour = new Color(rgb.getRed(),rgb.getGreen(),rgb.getBlue());
    }

    @Override
    public void setResolution(int resolution)
    {
        this.resolution = resolution;
    }

    /**
     * This draws the object defined by a GLDrawable objet in all the specifed display mode.
     * @param gl GL context object
     */
    @Override
    public void drawMe(GL gl)
    {
        // get the drawable object which, in turns, draws itself.
        gl.glPushMatrix();
        getDrawable().draw(gl);
        gl.glPopMatrix();
    }

    /**
     * Method to be implemented in subclass for the particular object to be drawn.
     *
     * @return a GLDrawable object.
     */
    @Override
    abstract public GLDrawable getDrawable();

}
