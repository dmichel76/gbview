package org.gbview.model.properties;

import org.gbview.model.*;
import org.gbview.model.particle.Particle;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexRange;

import java.util.Map;

/**
 * Implementation of {@link Properties} to be applied to {@link org.gbview.model.particle.Particle}.
 *
 * This stores a list of various properties (e.g. <code>visibility</code>, <code>displayMode</code>) and applied
 * it to a {@link org.gbview.model.Model} by the <code>apply</code> method to a {@link org.gbview.model.util.IndexRange}
 * of {@link org.gbview.model.particle.Particle}.
 *
 * This class is abstract as the method <code>applyImp</code> usesd in <code>apply</code> must be implemented in subclasses.
 * For instance, the subclass {@link SphereProperties}, used specifically for a {@link org.gbview.model.particle.Sphere}, applies
 * properties to a {@link org.gbview.model.particle.Sphere} in <code>applyImpl</code>. It also includes other properties that are specific to a
 * {@link org.gbview.model.particle.Sphere}.
 *
 */
public abstract class AbstractProperties implements Properties
{
    protected boolean visible;
    protected org.gbview.model.DisplayMode displayMode;

    protected boolean visibleFlag = false;
    protected boolean displayModeFlag = false;

    @Override
    public void setVisible(boolean visible)
    {
        this.visible = visible;
        this.visibleFlag = true;
    }

    @Override
    public void setDisplayMode(org.gbview.model.DisplayMode displayMode)
    {
        this.displayMode = displayMode;
        this.displayModeFlag = true;
    }

    @Override
    public void apply(Model model, IndexRange indexRange)
    {
        // sanity check
        if(!model.getIndexRange().contains(indexRange))
            return;

        for(Map.Entry<Index,Particle> entry : model.getParticles().entrySet())
        {
            Index index = entry.getKey();

            if(indexRange.contains(index))
            {
                Particle particle = entry.getValue();

                if(this.displayModeFlag) particle.setDisplayMode(this.displayMode);
                if(this.visibleFlag) particle.setVisible(this.visible);

                // each subclass has its own implementation, depending on the particle type
                applyImpl(particle);
            }
        }
    }

    /**
     * Apply properties to a given {@link org.gbview.model.particle.Particle}
     * Abstract method used in <code>apply</code> and to be implemented in subclasses.
     * */
    protected abstract void applyImpl(Particle particle);

}
