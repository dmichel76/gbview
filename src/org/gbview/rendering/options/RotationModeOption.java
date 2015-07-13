package org.gbview.rendering.options;

import java.util.prefs.Preferences;

/**
 * Persisted option value for the zooming speed when controller from the mouse wheel
 */
public class RotationModeOption implements Options
{

    public enum RotationModes
    {
        XYMODE, // "normal" rotation mode around the x and y axis
        ZMODE,  // alternative mode where the rotation occur around the z axis only
        XMODE,  // alternative mode where the rotation occur around the x axis only
        YMODE   // alternative mode where the rotation occur around the y axis only
    }
    RotationModes rotationMode = RotationModes.XYMODE;

    private static String OPTION_NAME = "rendering.rotation.mode";

    private Preferences preferences;

    public RotationModeOption(Preferences preferences)
    {
        this.preferences = preferences;
    }

    public Object getValue()
    {
        String rotationMode = this.preferences.get(OPTION_NAME,this.rotationMode.name());
        return RotationModes.valueOf(rotationMode);
    }

    public void setValue(Object value)
    {
        String rotationMode = RotationModes.valueOf(value.toString()).name();
        this.preferences.put(OPTION_NAME,rotationMode);
    }
}
