package org.gbview.model;

import org.gbview.model.particle.CubicBox;
import org.gbview.model.particle.Particle;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexRange;

import javax.media.opengl.GL;
import java.util.Map;

/**
 *
 */
public interface Model
{
    void draw(GL gl);

    void addParticle(Index i, Particle p);

    void setParticles(Map<Index,Particle> particles);

    Map<Index, Particle> getParticles();

    CubicBox getBox();

    void setBox(CubicBox box);

    IndexRange getIndexRange();

    int getSize();

    String toString();

    void normalise();
}
