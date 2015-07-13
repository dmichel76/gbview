package org.gbview.rendering;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.gbview.rendering.options.RendererOptions;
import org.gbview.rendering.options.RotationModeOption;
import org.gbview.rendering.scene.Scene;

public class Renderer implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private Scene scene;

	private boolean leftButtonPressed = false;
	
	private int dx = 0;
	private int dy = 0;
	private int mouse_x = 0;
	private int mouse_y = 0;
	
	private float xRotation = 0;
	private float yRotation = 0;
	private float zRotation = 0;
	
	private float xTranslation = 0;
	private float yTranslation = 0;
	private float zTranslation = 0;

    private RendererOptions options;
    private FPSCalculator fpsCalculator;

	public Renderer(Scene scene)
	{
		this.scene = scene;
        this.options = new RendererOptions();
        this.fpsCalculator = new FPSCalculator();
    }

    public Scene getScene()
    {
        return this.scene;
    }

	@Override
	public void display(GLAutoDrawable gLDrawable)
	{
        final GL gl = gLDrawable.getGL();
                
        // Clear the buffer, clear the matrix
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        // translate the scene
        float[] t = scene.getInitialTranslation();
        gl.glTranslatef(t[0] + xTranslation, t[1] + yTranslation, t[2] + zTranslation);
                
        //rotate the scene
        float[][] r = scene.getInitialRotation();
    	gl.glRotatef(r[0][0]+ xRotation, r[0][1], r[0][2], r[0][3]);
    	gl.glRotatef(r[1][0]+ yRotation, r[1][1], r[1][2], r[1][3]);
    	gl.glRotatef(r[2][0]+ zRotation, r[2][1], r[2][2], r[2][3]);	

    	
        // draw the scene according to scene definition
        scene.doDrawing(gl);

        gl.glFlush();

        // calculate FPS
        double fps = this.fpsCalculator.calculateFPS();

    }

	@Override
	public void init(GLAutoDrawable gLDrawable)
	{    	
        GL gl = gLDrawable.getGL();
        
        // setting the background
        Color background = scene.getBackgroundColour();
        gl.glClearColor(background.getRed(), background.getGreen(), background.getBlue(), background.getAlpha());
        
        // some graphics settings
        gl.glShadeModel(GL.GL_SMOOTH); // enable smooth shading, which blends colours nicely, and smoothes out lighting.
        gl.glClearDepth(1.0f); // clear z-buffer to the farthest
        gl.glEnable(GL.GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL.GL_LEQUAL); // the type of depth test to do
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST); // Do the best perspective correction

	}

	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
	{	   	
       
		final GL gl = gLDrawable.getGL();
 
        if (height <= 0) // avoid a divide by zero error!
        {
            height = 1;
        }
        final float aspect = (float) width / (float) height;
 
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        
        GLU glu = new GLU();  
        glu.gluPerspective(scene.getFlovy(), aspect, scene.getZNear(), scene.getZFar());
        
        gl.glViewport(0, 0, width, height);
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();		
	}

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b2) {
        // nothing
    }

    @Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if( e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL )
		{

            float f = Float.valueOf(this.options.getZoomingSpeedWheelOption().getValue().toString());
            updateZoom(e.getWheelRotation(), f);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{					
		// if button is not pressed, return now
		if(!leftButtonPressed) { return; }
		
		// calculate mouse movement
		dx = e.getX() - mouse_x;
		dy = e.getY() - mouse_y;	
		
		// if no displacement, return now
		if( dx==0 && dy==0) { return; }

        // if control is pressed, we do zooming
        if(e.isControlDown())
        {

            float f = Float.valueOf(this.options.getZoomingSpeedMouseOption().getValue().toString());
            updateZoom(dy, f);
        }
        // if shift if pressed, we do translation
        else if(e.isShiftDown())
        {
            float f = Float.valueOf(this.options.getTranslationSpeedOption().getValue().toString());

            updateTranslationX(dx, f);
            updateTranslationY(dy, f);
        }
        // otherwise, nor control nor shift are pressed, we do rotation
        else
        {
            // if alt key is pressed, we rotate along z axes only
            RotationModeOption.RotationModes rotationMode = (RotationModeOption.RotationModes) this.options.getRotationModeOption().getValue();

            float f = Float.valueOf(this.options.getRotationSpeedOption().getValue().toString());

            switch(rotationMode)
            {
                case XMODE:
                    updateRotationX(dx, f);
                    break;
                case YMODE:
                    updateRotationY(dx, f);
                    break;
                case ZMODE:
                    updateRotationZ(dx, f);
                    break;
                case XYMODE:
                    // the up/down motion (dy) corresponds to the rotation along the x axes
                    // whereas the left/right (dx) corresponds to the rotation along the y axes
                    updateRotationX(dy, f);
                    updateRotationY(dx, f);
                    break;

            }
        }

		mouse_x = e.getX();
		mouse_y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{	
		mouse_x = e.getX();
		mouse_y = e.getY();
		
		if(SwingUtilities.isLeftMouseButton(e))
		{
			leftButtonPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		leftButtonPressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

    private void updateZoom(float value, float speed)
    {
        zTranslation += (value * speed);

        ResolutionManager resolutionManager = ResolutionManager.getInstance();
        resolutionManager.setBaseFromZoom(zTranslation);
    }

    private void updateTranslationX(float value, float speed)
    {
        xTranslation += (value * speed);
    }

    private void updateTranslationY(float value, float speed)
    {
        // we use -value as OpenGL is left handed
        yTranslation += (-value * speed);
    }

    private void updateRotationX(float value, float speed)
    {
        xRotation += (value * speed);
    }

    private void updateRotationY(float value, float speed)
    {
        yRotation += (value * speed);
    }

    private void updateRotationZ(float value, float speed)
    {
        zRotation += (value * speed);
    }
}
