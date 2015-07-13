package org.gbview.model.particle;

import org.gbview.model.DisplayMode;
import org.gbview.model.Drawable;

public interface Particle extends Drawable
{
    void setX(double x);
    double getX();

    void setY(double y);
    double getY();

    void setZ(double z);
    double getZ();

    DisplayMode getDisplayMode();
    void setDisplayMode(DisplayMode displayMode);

    boolean isVisible();
    void setVisible(boolean flag);

    void normalise(double norm);

    String toString();


}
