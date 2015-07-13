package org.gbview.io.gbx.parser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.gbview.io.gbx.definition.GbMoleculeDefinition;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser of a molecule definition:
 *
 * e.g. 'MOL --name molecule --size 2 --colour 1 0 1'
 */
public class GbxMoleculeParser
{

    @Parameter(description = "")
    private List<String> list = new ArrayList<String>();

    @Parameter(names = { "-n","--name"}, description = "name of particle type")
    public String name;

    @Parameter(names = { "-s","--size"}, description = "size, i.e. the number of particle for that molecule")
    public int size;

    @Parameter(names = { "-c","--colour" }, arity = 3, description = "rgb colour of particle")
    public List<Integer> colour;

    /**
     * Parsing routine.
     *
     * @param tokens
     * @return {@link GbMoleculeDefinition}
     * @throws IOException
     */
    public static GbMoleculeDefinition parse(String[] tokens) throws IOException
    {
        GbxMoleculeParser parser = new GbxMoleculeParser();
        new JCommander(parser,tokens);

        String name = parser.name;
        int size = parser.size;
        Color colour = new Color(parser.colour.get(0),parser.colour.get(1),parser.colour.get(2));

        return new GbMoleculeDefinition(name, size, colour);

    }
}
