package org.gbview.rendering.GL;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class GLDot extends abstractGLObject
{

    @Override
    public GLDrawable getDrawable()
    {
        return new GLDrawable()
        {
            @Override
            public void draw(GL gl)
            {
                gl.glColor3f((float)colour.getRed()/256.0f,(float)colour.getGreen()/256.0f,(float)colour.getBlue()/256.0f);
                gl.glPointSize((float)scale);

                gl.glBegin(GL.GL_POINTS);
                {
                    gl.glVertex3f((float)coord_x,(float)coord_y,(float)coord_z);
                }
                gl.glEnd();
            }
        };
    }
}
