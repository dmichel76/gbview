package org.gbview.model.type;

/**
 * Defines the various types of box available
 */
public enum BoxType
{
    CUBIC("cubic");

    String name;

    private BoxType(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }

    public static BoxType parse(String name)
    {
        BoxType boxType = null;

        for(BoxType type : BoxType.values())
        {
            if(type.toString().equals(name))
            {
                boxType = type;
                break;
            }
        }

        return boxType;
    }
}
