package org.gbview.model.properties;

import org.gbview.model.*;
import org.gbview.model.util.IndexRange;

/**
 * Interface for particle properties such as {@link SphereProperties}
 */
public interface Properties
{

    public static boolean VISIBLE = true;
    public static boolean HIDDEN = false;

    public void setVisible(boolean visible);
    public void setDisplayMode(org.gbview.model.DisplayMode displayMode);

    public void apply(Model model, IndexRange indexRange);
}
