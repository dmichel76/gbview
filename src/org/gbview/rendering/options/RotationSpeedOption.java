package org.gbview.rendering.options;

import java.util.prefs.Preferences;

/**
 * Persisted option value for the zooming speed when controller from the mouse wheel
 */
public class RotationSpeedOption implements Options
{

    private static String OPTION_NAME = "rendering.rotation.speed";
    private static float DEFAULT = 0.5f;

    private Preferences preferences;

    public RotationSpeedOption(Preferences preferences)
    {
        this.preferences = preferences;
    }

    public Object getValue()
    {
        return preferences.getFloat(OPTION_NAME,DEFAULT);
    }

    public void setValue(Object value)
    {
        float f = Float.valueOf(value.toString());
        preferences.putFloat(OPTION_NAME, f);
    }
}
