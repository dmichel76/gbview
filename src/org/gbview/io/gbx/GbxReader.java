package org.gbview.io.gbx;

import org.gbview.io.FileParser;
import org.gbview.io.TextFileReader;
import org.gbview.io.gbx.parser.GbxParser;
import org.gbview.model.Model;


import java.io.File;
import java.io.IOException;

/**
 * This class is for reading gbx files
 **/
public class GbxReader
{
    private FileParser parser;

    /**
     * Constructor
     * Creates a new parser.
     */
    public GbxReader()
    {
        this.parser = new GbxParser();
    }

    /**
     * Reads a gbx file.
     *
     * The file extension is checked according to the allowed
     * extensions defined n {@link GbxExtension}.
     *
     */
    public void read(File file) throws IOException
    {
        if(!checkExtension(file.getName()))
        {
            throw new IllegalArgumentException("file extension not recognised");
        }

        // Use a generic file reader using the specified parser
        TextFileReader reader = new TextFileReader(this.parser);
        reader.read(file);
    }

    /**
     * Checks the file extension
     * @param fileName {@link String} representing the file name
     * @return <code>true</code> if the extension is allowed, <code>false</code> otherwise
     */
    private boolean checkExtension(String fileName)
    {
        for(GbxExtension allowedExtension: GbxExtension.values())
        {
            if(fileName.endsWith(allowedExtension.getExtension()))
            {
                return true;
            }
        }
        return false;
    }

    public Model getModel()
    {
        return parser.getModel();
    }
}
