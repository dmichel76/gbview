package org.gbview;

import javax.swing.*;

import org.gbview.io.gbx.GbxReader;
import org.gbview.model.*;
import org.gbview.model.DisplayMode;
import org.gbview.model.particle.CubicBox;
import org.gbview.model.particle.Particle;
import org.gbview.model.particle.Sphere;
import org.gbview.model.util.Index;
import org.gbview.rendering.scene.EmptyScene;
import org.gbview.rendering.scene.Scene;
import org.gbview.rendering.scene.SimpleScene;
import org.gbview.ui.ExceptionDialog;
import org.gbview.ui.FramePersistenceManager;
import org.gbview.ui.Ui;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

public class Gbview extends JFrame
{
    private Ui ui;
    private boolean isFileLoaded=false;

    private ResourceBundle R = ResourceBundle.getBundle("strings");
    private static Dimension DEFAULT_DIMENSION = new Dimension(500,500);

    public Gbview()
    {
        // Create the UI
        this.ui = new Ui(this);

        // Make sure the OpenGL routine is stopped before closing the {@link JFrame}
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread() {
                    @Override
                    public void run() {
                        ui.getViewer().stop(); // stop the animator loop
                        System.exit(0);
                    }
                }.start();
            }
        });

        String title = R.getString("org.gbview.Name") + " " + R.getString("org.gbview.Version");
        this.setTitle(title);

        this.setIconImage(new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources/icon.png")).getImage());

        // add UI to frame
        this.getContentPane().add(ui.getComponent());

        // Using a persistence manager to store and restore the location/size
        FramePersistenceManager persistenceManager = new FramePersistenceManager(this,DEFAULT_DIMENSION);
        persistenceManager.load();
    }

    public void loadFile(File file)
    {
        // Sanity check
        if(!file.isFile() || !file.exists())
        {
            IOException e = new IOException("File specified does not exists");
            ExceptionDialog exceptionDialog = new ExceptionDialog(this, "I/O error", e.getMessage(), e);
            exceptionDialog.setVisible(true);
        }

        try {
            // Create scene from file
            GbxReader reader = new GbxReader();
            reader.read(file);
            Model model = reader.getModel();
            model.normalise();
            Scene scene = new SimpleScene(model);

            // Load the new scene
            this.ui.getViewer().setScene(scene);

            this.isFileLoaded = true;
        }
        catch (Throwable ioe)
        {
            String title = "Error while loading file";
            String message = file.getAbsolutePath();
            ExceptionDialog exceptionDialog = new ExceptionDialog(this, title, message, ioe);
            exceptionDialog.setVisible(true);
        }

    }

    public boolean isFileLoaded()
    {
        return this.isFileLoaded;
    }

    public void closeFile()
    {
        this.ui.getViewer().setScene(new EmptyScene());
        this.isFileLoaded = false;
    }

    public void display()
    {
        // make frame visible & start the animation loop
        this.setVisible(true);
        this.ui.getViewer().start();
    }

    @Override
    protected void processWindowEvent(WindowEvent e)
    {
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
        {
            exit();
        }
        else
        {
            // If you do not want listeners processing the WINDOW_CLOSING
            // events, you could this next call in an else block for the:
            //     if (e.getID() ...)
            // statement. That way, only the other types of Window events
            // (iconification, activation, etc.) would be sent out.

            super.processWindowEvent(e);
        }
    }

    public void exit()
    {
        int exit = JOptionPane.showConfirmDialog(this,
                R.getString("org.gbview.ui.exit.confirm.question"),
                R.getString("org.gbview.ui.exit.confirm.title"),
                JOptionPane.YES_NO_OPTION);

        if (exit == JOptionPane.YES_OPTION)
        {
            FramePersistenceManager persistenceManager = new FramePersistenceManager(this,DEFAULT_DIMENSION);
            persistenceManager.save();

            System.exit(0);
        }
    }

    public static void main(final String[] args)
    {
        // Run the GUI codes in the event-dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            Gbview gbview = new Gbview();

            if(args.length != 0)
            {
                File file = new File(args[0]);
                gbview.loadFile(file);
            }

            gbview.display();

            }
        });
    }

}
