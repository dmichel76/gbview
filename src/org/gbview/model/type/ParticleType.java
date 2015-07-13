package org.gbview.model.type;

import org.gbview.io.gbx.definition.GbParticleDefinition;
import org.gbview.io.gbx.parser.GbxSphereParser;

import java.io.IOException;

/**
 * Defines the type of {@link org.gbview.model.particle.AbstractParticle}
 */
public enum ParticleType
{
    SPHERE("SPHERE");

    String name;

    private ParticleType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Parse a <code>name</code> to a {@link org.gbview.model.type.ParticleType}
     */
    public static ParticleType parse(String name)
    {
        ParticleType particleType = null;

        for(ParticleType type : ParticleType.values())
        {
            if(type.getName().equals(name))
            {
                particleType = type;
                break;
            }
        }

        return particleType;
    }

    /**
     * Check if a <code>name</code> has an associated {@link org.gbview.model.type.ParticleType}
     */
    public static boolean contains(String name)
    {
        for(ParticleType type : ParticleType.values())
        {
            if(type.toString().equals(name))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates a {@link org.gbview.io.gbx.definition.GbParticleDefinition} from a known {@link org.gbview.model.type.ParticleType}
     */
    public static GbParticleDefinition createParticleDefinition(ParticleType particleType, String[] tokens) throws IOException
    {
        GbParticleDefinition particleDefinition = null;

        switch(particleType)
        {
            case SPHERE:
                particleDefinition = GbxSphereParser.parse(tokens);
                break;
        }

        return particleDefinition;
    }

}
