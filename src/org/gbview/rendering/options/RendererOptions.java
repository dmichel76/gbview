package org.gbview.rendering.options;

import java.util.prefs.Preferences;

/**
 * Options used by the {@link org.gbview.rendering.Renderer} class. These have default values hardcoded
 */
public class RendererOptions
{
    private Preferences prefs;



    public RendererOptions()
    {
        this.prefs = Preferences.userNodeForPackage(this.getClass());
    }

    public ZoomingSpeedWheelOption getZoomingSpeedWheelOption()
    {
        return new ZoomingSpeedWheelOption(prefs);
    }

    public ZoomingSpeedMouseOption getZoomingSpeedMouseOption()
    {
        return new ZoomingSpeedMouseOption(prefs);
    }

    public RotationSpeedOption getRotationSpeedOption()
    {
        return new RotationSpeedOption(prefs);
    }

    public TranslationSpeedOption getTranslationSpeedOption()
    {
        return new TranslationSpeedOption(prefs);
    }

    public RotationModeOption getRotationModeOption()
    {
        return new RotationModeOption(prefs);
    }

}
