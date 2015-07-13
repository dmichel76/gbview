package org.gbview.ui;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

/**
 * This manages the persistence of a {@link JFrame} size and location.
 *
 * A default {@link Dimension} must be provided, which will be used the first time
 * the application is launched.
 * 
 * An optional default location can be provided via a {@link Point} object which
 * will also be used the first time is application is launched. If not provided,
 * then the default location given by the host platform is used instead.
 *
 * In order to persist both location and size, the <code>install</code> method must
 * be called. This will simply install and hook on the closing action of the
 * <code>frame</code> to store the latest dimension and size.
 *
 */
public class FramePersistenceManager
{

    private Preferences prefs;
    
    private String frameSizeMaximisedPref = "frameMaximisedPref";
    private String frameSizeWidthPref = "frameSizeWidthPref";
    private String frameSizeHeightPref = "frameSizeHeightPref";
    private String frameLocationXPref = "frameLocationXPref";
    private String frameLocationYPref = "frameLocationYPref";

    private static int WAITING_TIME = 100;

    private JFrame frame;
    private Dimension defaultDimension;
    private boolean defaultPlatformLocation;
    private Point defaultLocation;

    private static final Logger logger = Logger.getLogger(FramePersistenceManager.class.getName());
    
    public FramePersistenceManager(JFrame frame, Dimension defaultDimension)
    {
        this(frame,defaultDimension,null);
        this.defaultPlatformLocation = true;
    }

    public FramePersistenceManager(JFrame frame, Dimension defaultDimension, Point defaultLocation)
    {
        this.frame = frame;
        this.defaultDimension = defaultDimension;

        this.prefs = Preferences.userNodeForPackage(frame.getClass());

        this.defaultPlatformLocation = false;
        this.defaultLocation = defaultLocation;
    }

    public void install()
    {
        storePrefsWhenClosing();
    }

    private void setDimension()
    {
        int w = prefs.getInt(frameSizeWidthPref, (int) this.defaultDimension.getWidth());
        int h = prefs.getInt(frameSizeHeightPref,(int) this.defaultDimension.getHeight());
        this.frame.setSize(w, h);
        this.frame.setExtendedState(prefs.getInt(frameSizeMaximisedPref, Frame.NORMAL));
    }
    
    private void setLocation()
    {

        int x, xDefault;
        int y, yDefault;

        // use host platform default location
        if(this.defaultPlatformLocation)
        {
            this.frame.setLocationByPlatform(true);
            xDefault = this.frame.getLocation().x;
            yDefault = this.frame.getLocation().y;
        }
        // use specified default location
        else
        {
            xDefault = (int) this.defaultLocation.getX();
            yDefault = (int) this.defaultLocation.getY();
        }

        // set location from preferences, using default if not found
        x = prefs.getInt(frameLocationXPref, xDefault);
        y = prefs.getInt(frameLocationYPref, yDefault);
        this.frame.setLocation(x, y);
    }

    public void load()
    {
    
        logger.trace("Loading main frame dimension and location preferences");
        
        setDimension();
        setLocation();
    }

    public void save()
    {

        logger.trace("Saving main frame dimension and location preferences");

        // remember if the frame was maximised or not
        prefs.putInt(frameSizeMaximisedPref, frame.getExtendedState());

        // exit from full screen to remember the size/location before it was maximised
        frame.setExtendedState(Frame.NORMAL);

        // give the system half a chance to resize the window
        try
        {
            Thread.sleep(WAITING_TIME);
        }
        catch (InterruptedException e)
        {
            logger.warn(e.getMessage());
        }

        // remember the dimension and location when it's not maximised
        Rectangle r = frame.getBounds();
        prefs.putInt(frameLocationXPref, (int) r.getX());
        prefs.putInt(frameLocationYPref, (int) r.getY());
        prefs.putInt(frameSizeWidthPref, (int) r.getWidth());
        prefs.putInt(frameSizeHeightPref, (int) r.getHeight());        
    }

    private void storePrefsWhenClosing()
    {
        //this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                save();
                System.exit(0);
            }
        });

    }

}
