package org.gbview.model.particle;

import org.gbview.model.DisplayMode;
import org.gbview.model.properties.Properties;

import javax.media.opengl.GL;

/**
 * Object describing a generic particle
 */
public abstract class AbstractParticle implements Particle
{
    protected double x;
    protected double y;
    protected double z;

    protected DisplayMode displayMode = DisplayMode.getDefault();
    protected boolean visible = Properties.VISIBLE;

    public AbstractParticle(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setX(double x) { this.x = x; }

    @Override
    public double getX() { return this.x; }

    @Override
    public void setY(double y) { this.y = y; }

    @Override
    public double getY()
    {
        return this.y;
    }

    @Override
    public void setZ(double z) { this.z = z; }

    @Override
    public double getZ()
    {
        return this.z;
    }

    @Override
    public DisplayMode getDisplayMode()
    {
        return displayMode;
    }

    @Override
    public void setDisplayMode(DisplayMode displayMode)
    {
        this.displayMode = displayMode;
    }

    @Override
    public boolean isVisible()
    {
        return visible;
    }

    @Override
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    @Override
    public void draw(GL gl)
    {

        if(!this.visible)
            return;

        draw(gl, this.displayMode);
    }

    @Override
    public void draw(GL gl, DisplayMode mode)
    {
        if(!this.visible)
            return;

        switch(mode)
        {
            case FILL:
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
                drawFill(gl);
                break;

            case WIREFRAME:
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
                drawFill(gl);
                break;

            case DOT:
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_POINT);
                drawDot(gl);
                break;

            case LINE:
                gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
                drawLine(gl);
                break;

            default:
                throw new UnsupportedOperationException();
        }

    }

    @Override
    public abstract void drawFill(GL gl);

    @Override
    public abstract void drawDot(GL gl);

    @Override
    public abstract void drawLine(GL gl);

    @Override
    public abstract String toString();

}
