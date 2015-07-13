package org.gbview.model.util;

import java.awt.*;

/**
 *  Default colours used for colouring particle
 *  when no colour argument is specified in the input data file
 */
public enum ColourDefaults
{
    RED(Color.red),
    WHITE(Color.white),
    GREEN(Color.green),
    ORANGE(Color.orange),
    BLUE(Color.blue),
    YELLOW(Color.yellow),
    PINK(Color.pink);

    private Color colour;
    static private int count=0 ;

    ColourDefaults(Color colour)
    {
        this.colour = colour;
    }

    public Color getColour()
    {
        return this.colour;
    }

    public String getName()
    {
        return this.toString();
    }

    public static ColourDefaults getDefault()
    {
        return ColourDefaults.RED;
    }

    public static synchronized ColourDefaults getNext()
    {
        ColourDefaults[] values = ColourDefaults.values();

        if(count >=0 && count <= values.length)
        {
            ColourDefaults c = values[count];

            count++;
            if(count >= values.length)
            {
                count = 0;
            }

            return c;
        }

        return null;
    }

}
