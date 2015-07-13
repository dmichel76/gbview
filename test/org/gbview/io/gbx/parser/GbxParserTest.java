package org.gbview.io.gbx.parser;

import org.gbview.io.gbx.definition.GbBoxDefinition;
import org.gbview.io.gbx.definition.GbMoleculeDefinition;
import org.gbview.io.gbx.definition.GbSphereDefinition;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


/**
 * Test class for {@link org.gbview.io.gbx.parser.GbxParser}
 */
public class GbxParserTest
{
    @Test
    public void comments_are_ignored()
    {
        GbxParser parser = new GbxParser();

        assertTrue(parser.isComment("# this is a comment"));
        assertTrue(parser.isComment("// this is a comment"));
        assertTrue(parser.isComment("' this is a comment"));
        assertTrue(parser.isComment("* this is a comment"));

        assertFalse(parser.isComment("this is NOT a comment"));
        assertFalse(parser.isComment("/ this is NOT a comment"));
        assertFalse(parser.isComment("% this is NOT a comment"));
        assertFalse(parser.isComment("! this is NOT a comment"));
        assertFalse(parser.isComment("<!-- this is NOT a comment"));

    }

    @Test
    public void can_parse_SPHERE_particle_definition() throws IOException
    {
        GbxSphereParser parser = new GbxSphereParser();

        String[] validArgs = {
            "SPHERE -n lennard -d 1.0 -c 1 2 3",
            "SPHERE --name lennard --colour 1 2 3 --diameter 1.0",
            "SPHERE --name lennard --diameter 1.0 -c 1 2 3"
        };

        for(String s : validArgs)
        {
            GbSphereDefinition sd = parser.parse(s.split(GbxParser.DELIMITERS));
            assertEquals("lennard", sd.getName());
            assertEquals(1.0, sd.getDiameter(),0.0);
            assertEquals(1,sd.getColour().getRed());
            assertEquals(2,sd.getColour().getGreen());
            assertEquals(3,sd.getColour().getBlue());
        }
    }

    @Test
    public void can_parse_MOL_particle_definition() throws IOException
    {
        GbxMoleculeParser parser = new GbxMoleculeParser();

        String[] validArgs = {
            "MOL -n molecule -s 2 -c 1 0 1",
            "MOL --name molecule --size 2 --colour 1 0 1",
            "MOL --name molecule --colour 1 0 1 --size 2 "
        };

        for(String s : validArgs)
        {
            GbMoleculeDefinition md = parser.parse(s.split(GbxParser.DELIMITERS));
            assertEquals("molecule", md.getName());
            assertEquals(2, md.getSize(),0.0);
            assertEquals(1,md.getColour().getRed());
            assertEquals(0, md.getColour().getGreen());
            assertEquals(1,md.getColour().getBlue());
        }
    }

    @Test
    public void can_parse_BOX_definition() throws IOException
    {
        GbxBoxParser parser = new GbxBoxParser();

        String[] validArgs = {
                "BOX --cubic 1.0 2.0 3.0",
                "BOX -c 1.0 2.0 3.0"
        };

        for(String s : validArgs)
        {
            GbBoxDefinition bd = parser.parse(s.split(GbxParser.DELIMITERS));
            assertEquals(1.0, bd.getLx(),0.0);
            assertEquals(2.0, bd.getLy(),0.0);
            assertEquals(3.0, bd.getLz(),0.0);
        }
    }
}
