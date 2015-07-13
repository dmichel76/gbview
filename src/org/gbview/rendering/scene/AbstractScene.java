package org.gbview.rendering.scene;

import org.gbview.model.Model;

import javax.media.opengl.GL;
import java.awt.*;

public abstract class AbstractScene implements Scene
{

	protected Model model;

	public AbstractScene(Model model)
	{
		this.model = model;
	}

	public Model getModel()
	{
		return this.model;
	}

	@Override
    public float[] getInitialTranslation()
	{
		float[] vector = {0.0f,0.2f,-15.0f};
		return vector;
	}
	
	@Override
    public float[][] getInitialRotation()
	{
		float[][] matrix = 
		{
			{30.0f,1.0f,0.0f,0.0f},
			{30.0f,0.0f,1.0f,0.0f},
			{0.0f,0.0f,0.0f,1.0f}			
		};
		
		return matrix;
	}
	
	@Override
    public Color getBackgroundColour()
	{
		return Color.BLACK;
	}
	
	@Override
    public float getFlovy()
	{
		return 20.0f;
	}
	
	@Override
    public float getZNear()
	{
		return 0.5f;
	}
	
	@Override
    public float getZFar()
	{
		return 1000.0f;
	}
	
	@Override
    public void doDrawing(GL gl)
	{
		this.model.draw(gl);
	}

}
