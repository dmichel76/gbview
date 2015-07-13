package org.gbview.rendering.GL;

import javax.media.opengl.GL;

public class GLCube extends abstractGLObject
{

	private final float[][] vertexes =
	{
		{-1, -1, -1}, {-1, -1,  1}, {-1,  1,  1}, {-1,  1, -1},
		{ 1, -1, -1}, { 1, -1,  1},	{ 1,  1,  1}, { 1,  1, -1},
		{-1, -1, -1}, {-1, -1,  1},	{ 1, -1,  1}, { 1, -1, -1},
		{-1,  1, -1}, {-1,  1,  1},	{ 1,  1,  1}, { 1,  1, -1},
		{-1, -1, -1}, {-1,  1, -1}, { 1,  1, -1}, { 1, -1, -1},
		{-1, -1,  1}, {-1,  1,  1},	{ 1,  1,  1}, { 1, -1,  1}
	};

    @Override
    public GLDrawable getDrawable()
    {
        return new GLDrawable()
        {
            @Override
            public void draw(GL gl)
            {
                gl.glTranslatef((float)coord_x,(float)coord_y,(float)coord_z);
                gl.glBegin(GL.GL_QUADS);
                {
                    for(int i=0 ; i<vertexes.length ; i++)
                    {
                        gl.glColor3f((float)colour.getRed()/ 256.0f, (float)colour.getGreen()/ 256.0f, (float)colour.getBlue()/ 256.0f);
                        gl.glVertex3f((float)scaleX*vertexes[i][0], (float)scaleY*vertexes[i][1], (float)scaleZ*vertexes[i][2]);
                    }
                }
                gl.glEnd();
            }
        };
    }

}
