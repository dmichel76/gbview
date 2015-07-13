package org.gbview.rendering;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import org.gbview.model.Model;
import org.gbview.model.RandomModel;
import org.gbview.model.SimpleModel;
import org.gbview.model.SingleParticleModel;
import org.gbview.rendering.scene.EmptyScene;
import org.gbview.rendering.scene.Scene;
import org.gbview.rendering.scene.SimpleScene;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class to setup and create the {@link GLCanvas}
 */
public class Viewer
{
    private JFrame frame;
    private Renderer renderer;
    private GLCanvas canvas;
    private Animator animator;

    private static final int CANVAS_WIDTH = 400;  // width of the drawable
    private static final int CANVAS_HEIGHT = 400; // height of the drawable

    private static final int FPS = 60; // animator's target frames per second

    public Viewer(JFrame frame)
    {
        // an empty scene is specified when no {@link Scene} is given
        this(frame, new EmptyScene());
        //this(frame, new SimpleScene(new RandomModel()));

        System.out.println(this.renderer.getScene().getModel().toString());
    }

    public Viewer(JFrame frame, Scene scene)
    {
        this.frame = frame;

        // set up the renderer from the given {@link Scene}
        this.renderer = new Renderer(scene);

        // set double buffering active
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setDoubleBuffered(true);

        // The canvas is the widget that's drawn in the JFrame
        canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(renderer);
        canvas.addMouseListener(renderer);
        canvas.addMouseMotionListener(renderer);
        canvas.addMouseWheelListener(renderer);
        canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        // adjust canvas to changing frame size
        this.frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int width = (int) e.getComponent().getSize().getWidth();
                int height = (int) e.getComponent().getSize().getHeight();
                canvas.setSize(new Dimension(width, height));
            }
        });

        // adjust canvas when frame is maximised, de-maximised frame size
        this.frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH || (e.getNewState() & Frame.NORMAL) == Frame.NORMAL){
                    int width = (int) e.getComponent().getSize().getWidth();
                    int height = (int) e.getComponent().getSize().getHeight();
                    canvas.setSize(new Dimension(width, height));
                }
            }
        });

        // using an {@link Animator} to force a loop on the
        // OpenGL <code>display()</code> routine
        animator = new FPSAnimator(canvas,FPS,true);
        animator.setRunAsFastAsPossible(true);
        animator.add(canvas);
    }

    public void setScene(Scene scene)
    {
        // remove old listeners
        canvas.removeGLEventListener(this.renderer);
        canvas.removeMouseListener(this.renderer);
        canvas.removeMouseMotionListener(this.renderer);
        canvas.removeMouseWheelListener(this.renderer);

        // set new renderer
        this.renderer = new Renderer(scene);

        // add new listeners
        canvas.addGLEventListener(this.renderer);
        canvas.addMouseListener(this.renderer);
        canvas.addMouseMotionListener(this.renderer);
        canvas.addMouseWheelListener(this.renderer);
    }

    public void start()
    {
        animator.start();
    }

    public void stop()
    {
        if (animator.isAnimating()) animator.stop();
    }

    public GLCanvas getCanvas()
    {
        return canvas;
    }

    public void setSize(Dimension dim) { canvas.setSize(dim); }

    public Dimension getSize(){ return canvas.getSize(); };

    public Animator getAnimator()
    {
        return animator;
    }
}
