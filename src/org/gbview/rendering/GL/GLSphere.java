package org.gbview.rendering.GL;

import javax.media.opengl.GL;
import javax.media.opengl.glu.*;

public class GLSphere extends abstractGLObject
{

    @Override
    public GLDrawable getDrawable()
    {
        return new GLDrawable()
        {
            @Override
            public void draw(GL gl)
            {

                gl.glTranslatef((float)coord_x,(float)coord_y,(float)coord_z);
                gl.glColor3f((float)colour.getRed()/256.0f,(float)colour.getGreen()/256.0f,(float)colour.getBlue()/256.0f);
                GLU glu = new GLU();
                GLUquadric quad = glu.gluNewQuadric();
                glu.gluQuadricDrawStyle(quad, GLU.GLU_FILL);
                glu.gluQuadricNormals(quad, GLU.GLU_FLAT);
                glu.gluQuadricOrientation(quad, GLU.GLU_OUTSIDE);
                glu.gluSphere(quad, (float) scale, resolution, resolution);
                glu.gluDeleteQuadric(quad);
            }
        };
    }
}
