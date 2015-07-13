package org.gbview.rendering.options;

import java.util.prefs.Preferences;

/**
 * Persisted option value for the zooming speed when controller from the mouse wheel
 */
public class TranslationSpeedOption implements Options
{

    private static String OPTION_NAME = "rendering.translation.speed";
    private static float DEFAULT = 0.01f;

    private Preferences preferences;

    public TranslationSpeedOption(Preferences preferences)
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
