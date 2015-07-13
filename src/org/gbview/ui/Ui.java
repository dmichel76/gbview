package org.gbview.ui;

import org.gbview.Gbview;
import org.gbview.rendering.Viewer;
import javax.media.opengl.GLCanvas;
import javax.swing.*;

/**
 * Main UI class.
 */
public class Ui
{
    private Gbview gbview;
    private JComponent component;
    private Viewer viewer;
    private GLCanvas canvas;

    public Ui(Gbview gbview)
    {
        this.gbview = gbview;

        // Create a canvas
        viewer = new Viewer(this.gbview);
        canvas = viewer.getCanvas();

        // Create component
        component = new JPanel();
        component.add(canvas);

        // create menu bar
        createMenuBar(gbview);

    }

    public Viewer getViewer()
    {
        return viewer;
    }

    public JComponent getComponent()
    {
        return component;
    }

    private void createMenuBar(Gbview gbview)
    {
        MenuBar menuBar = new MenuBar(gbview);
        this.gbview.getRootPane().setJMenuBar(menuBar.getMenuBar());
    }

}
