package org.gbview.rendering.scene;

import org.gbview.model.Model;
import org.gbview.model.SimpleModel;

import javax.media.opengl.GL;

/**
 *
 */
public class EmptyScene extends AbstractScene
{
    public EmptyScene() {
        super(new SimpleModel());
    }

    // Draws nothing...
    @Override
    public void doDrawing(GL gl){
        //  draws nothing
    }
}
