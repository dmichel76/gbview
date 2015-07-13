package org.gbview.model.util;

/**
 *
 */
public class Index
{
    Integer i;

    public Index(int i)
    {
        this.i = new Integer(i);
    }

    public int getValue()
    {
        return i.intValue();
    }

    @Override
    public String toString() {
        return "Index{" +
                "i=" + i +
                '}';
    }
}
