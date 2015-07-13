package org.gbview.model;

import javax.media.opengl.GL;

/**
 *
 */
public interface Drawable
{
    void draw(GL gl);

    void draw(GL gl, DisplayMode mode);

    void drawFill(GL gl);

    void drawDot(GL gl);

    void drawLine(GL gl);

}
