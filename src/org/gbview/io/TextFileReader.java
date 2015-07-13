package org.gbview.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import org.gbview.io.FileParser;

/**
 * This is a generic file reader, regardless of the actually file format.
 * It reads a ASCII text file line by line.
 * A specific parser (which one is used depends on the format) is used to parse each line.
 */
public class TextFileReader
{

    private FileParser parser;

    /**
     * Constructor
     * @param parser {@link FileParser} which actually parses each line read.
     */
    public TextFileReader(FileParser parser)
    {
        this.parser = parser;
    }

    /**
     * Method for reading a ASCII text file line by line.
     *
     * @param file {@link java.io.File} to read
     */
    public void read(File file) throws IOException
    {
        int lineNumber = 0;
        BufferedReader br = null;

        try
        {
            String line = "";
            br = new BufferedReader(new java.io.FileReader(file.getAbsoluteFile()));

            while ((line = br.readLine()) != null)
            {
                lineNumber++;
                this.parser.parse(line);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            try
            {
                if (br != null)  br.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
