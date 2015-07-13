package org.gbview.io.gbx.definition;

import org.gbview.io.gbx.parser.GbxBoxParser;
import org.gbview.io.gbx.parser.GbxMoleculeParser;
import org.gbview.io.gbx.parser.GbxSphereParser;

import java.io.IOException;

/**
 * Defines the various types of definitions,
 * i.e. what can de specified in a gbx file.
 */
public enum GbDefinitionType
{

    MOL("MOL"),
    BOX("BOX"),
    BOND("BOND");

    private String name;

    private GbDefinitionType(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public static GbDefinitionType getType(String name)
    {
        GbDefinitionType definitionType = null;

        for(GbDefinitionType type : GbDefinitionType.values())
        {
            if(type.toString().equals(name))
            {
                definitionType = type;
                break;
            }
        }

        return definitionType;
    }

    public static boolean contains(String name)
    {
        for(GbDefinitionType type : GbDefinitionType.values())
        {
            if(type.toString().equals(name))
            {
                return true;
            }
        }

        return false;
    }

    public static GbDefinition createDefinition(GbDefinitionType gbDefinitionType, String[] tokens) throws IOException
    {

        GbDefinition gbDefinition = null;

        switch(gbDefinitionType)
        {
            case MOL:
                gbDefinition = GbxMoleculeParser.parse(tokens);
                break;
            case BOX:
                gbDefinition = GbxBoxParser.parse(tokens);
                break;
            case BOND:
                throw new UnsupportedOperationException();
        }

        return gbDefinition;
    }

}
