package org.gbview.rendering.GL;

import java.awt.Color;
import javax.media.opengl.GL;

public interface GLObject
{
	public void drawMe(GL gl);

    public void setCoordinates(double x, double y, double z);

    public void setColour(Color rgb);

    public void setScale(double scale);

    public void setScale(double scaleX, double scaleY, double scaleZ);

    public void setResolution(int resolution);

    public GLDrawable getDrawable();

}
