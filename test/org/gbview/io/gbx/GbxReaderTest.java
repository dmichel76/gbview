package org.gbview.io.gbx;

import java.io.File;
import java.io.IOException;

import org.gbview.model.Model;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.fail;

/**
 * Test class for {@link org.gbview.io.gbx.GbxReader}
 */
public class GbxReaderTest
{
    @Test
    public void should_be_able_to_read_atomic_gbx_file()
    {
        String testFile = "./data/chol.gbx";

        GbxReader reader = new GbxReader();
        try
        {
            reader.read(new File(testFile));
            Model model = reader.getModel();
        }
        catch (IOException e)
        {
            fail("could not read file: " + testFile);
        }
    }

    @Test
    public void should_be_able_to_read_molecular_gbx_file()
    {
        fail("unimplemented");
    }

}
