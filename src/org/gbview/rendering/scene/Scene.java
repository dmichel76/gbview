package org.gbview.rendering.scene;

import org.gbview.model.Model;

import javax.media.opengl.GL;
import java.awt.*;

/**
 * Interface for a scene
 */
public interface Scene
{

    float[] getInitialTranslation();

    float[][] getInitialRotation();

    Color getBackgroundColour();

    float getFlovy();

    float getZNear();

    float getZFar();

    void doDrawing(GL gl);

    Model getModel();
}
