package org.gbview.io.gbx.parser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.gbview.io.gbx.definition.GbSphereDefinition;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser of a SPHERE particle definition:
 *
 * e.g. 'SPHERE --name atom --diameter 1.0 --colour 0 0 1'
 */
public class GbxSphereParser
{
    @Parameter(description = "")
    private List<String> list = new ArrayList<String>();

    @Parameter(names = {"-n","--name"}, description = "name of particle type")
    public String name;

    @Parameter(names = { "-d","--diameter" }, description = "diameter of particle")
    public Double diameter;

    @Parameter(names = { "-c","--colour" }, arity = 3, description = "rgb colour of particle")
    public List<Integer> colour;

    /**
     * Parsing routine.
     *
     * @param tokens
     * @return {@link org.gbview.io.gbx.definition.GbSphereDefinition}
     */
    public static GbSphereDefinition parse(String[] tokens) throws IOException
    {

        // Create a new parser for a SPHERE particle definition
        GbxSphereParser parser = new GbxSphereParser();
        new JCommander(parser,tokens);

        double d = parser.diameter;
        String name = parser.name;
        Color colour = new Color(parser.colour.get(0),parser.colour.get(1),parser.colour.get(2));

        return new GbSphereDefinition(name,d,colour);
    }
}