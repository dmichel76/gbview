package org.gbview.io.gbx.definition;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Definition of a molecule
 */
public class GbMoleculeDefinition implements GbDefinition
{

    String name;
    int size;
    Color colour;

    public GbMoleculeDefinition(String name, int size, Color colour)
    {
        this.name = name;
        this.size = size;
        this.colour = colour;
    }

    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    public Color getColour()
    {
        return colour;
    }

    @Override
    public String toString()
    {
        return name + " " + size + " " + colour;
    }

}
