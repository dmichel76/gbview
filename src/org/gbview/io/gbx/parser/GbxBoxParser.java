package org.gbview.io.gbx.parser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.gbview.io.gbx.definition.GbBoxDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for a box definition:
 *
 * e.g. 'BOX --cubic 5.0 5.0 5.0'
 */
public class GbxBoxParser
{
    @Parameter(description = "")
    private List<String> list = new ArrayList<String>();

    @Parameter(names = {"c","-c","cubic","--cubic"}, arity = 3, description = "dimensions for a cubic simulation box")
    public List<Double> dimensions;

    public static GbBoxDefinition parse(String[] tokens) throws IOException
    {
        GbxBoxParser parser = new GbxBoxParser();
        new JCommander(parser,tokens);

        double lx = parser.dimensions.get(0);
        double ly = parser.dimensions.get(1);
        double lz = parser.dimensions.get(2);

        return new GbBoxDefinition(lx,ly,lz);
    }
}
