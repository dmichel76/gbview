package org.gbview.model.util;

import java.util.*;

/**
 * Class holding indexes, that a list of 'single' indexes as well as ranges.
 *
 * This allows one object to contain a flexible amount of indexes.
 * For instance, the class can de defined to hold indexes 1-5,7,9,10-20
 * i.e. 1,2,3,4,5,7,9,10,11,12,13,14,15,16,17,18,19,20
 *
 */
public class IndexSet
{
    Set<Index> indexes = new HashSet<Index>();

    /**
     * Default construction of empty set.
     * Indexes can be added using the <code>add</code> method
     */
    public IndexSet(){};

    public IndexSet(Set<Index> indexes, Set<IndexRange> ranges)
    {
        if(indexes!=null)
        {
            addIndexes(indexes);
        }

        if(ranges!=null)
        {
            addIndexRanges(ranges);
        }

    }

    public void addIndexRanges(Set<IndexRange> ranges)
    {
        for(IndexRange ir : ranges)
        {
            this.indexes.addAll(ir.getIndexSet());
        }
    }

    public void addIndexes(Set<Index> indexes)
    {
        this.indexes.addAll(indexes);
    }

    public boolean add(Index index)
    {
        return this.indexes.add(index);
    }

    public Set<Index> getIndexes()
    {
        return indexes;
    }

    @Override
    public String toString()
    {
        // just convert the set into a list to sort it
        List<Index> list = new ArrayList<Index>(indexes);
        java.util.Collections.sort(list,new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                int i1 = o1.getValue();
                int i2 = o2.getValue();
                return (i1 > i2 ? -1 : (i1 == i2 ? 0 : 1));
            }
        });

        return "IndexSet{" +
                "indexes=" + list +
                '}';
    }
}
