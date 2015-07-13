package org.gbview.model.util;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class IndexRange
{
    private Index i;
    private Index j;

    public IndexRange(Index i, Index j) {

        if(i.getValue() > j.getValue())
        {
            throw new IllegalArgumentException("Invalid index range definition");
        }

        this.i = i;
        this.j = j;
    }

    public Set<Index> getIndexSet()
    {
        Set<Index> indexes = new HashSet<Index>();

        for(int i=0;i<this.getRangeLength();i++)
        {
            int j = getIndexStart().getValue() + 1;
            indexes.add(new Index(j));
        }

        return indexes;
    }

    public Index getIndexStart() {
        return i;
    }

    public Index getIndexEnd() {
        return j;
    }

    public int getRangeLength()
    {
        return j.getValue() - i.getValue();
    }

    public boolean contains(Index index)
    {
        int ind = index.getValue();

        if( ind >= i.getValue() && ind <= j.getValue())
        {
            return true;
        }
        return false;
    }

    public boolean contains(IndexRange range)
    {
        Index start = range.getIndexStart();
        Index end = range.getIndexEnd();

        if((start.getValue() >= i.getValue()) && (end.getValue() <= j.getValue()))
        {
            return true;
        }
        return false;

    }

}
