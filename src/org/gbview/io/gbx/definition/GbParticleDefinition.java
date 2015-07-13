package org.gbview.io.gbx.definition;

import org.gbview.model.particle.Particle;
import org.gbview.model.type.ParticleType;
import org.gbview.model.util.Index;
import org.gbview.model.util.IndexSet;

/**
 *  Interface of all particle definitions
 */
public interface GbParticleDefinition extends GbDefinition
{
    public void setIndexSet(IndexSet indexes);

    public void addIndex(Index index);

    public IndexSet getIndexSet();

    public String getName();

    public ParticleType getType();

    public Particle createParticle(double ... data);

    public String toString();
}

