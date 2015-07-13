package org.gbview.model;

public enum DisplayMode
{

    FILL("fill"),
	WIREFRAME("wireframe"),
    LINE("line"),
    DOT("dot");

    private String name;

    private DisplayMode(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public static DisplayMode getDefault()
    {
        return DisplayMode.FILL;
    }

}
