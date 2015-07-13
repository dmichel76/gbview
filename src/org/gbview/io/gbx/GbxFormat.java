package org.gbview.io.gbx;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

/**
 * Groups together some useful information about the GBX format.
 */
public class GbxFormat
{
    public static List<String> getAllowedCommentsKeyword()
    {
        return Arrays.asList("#","//","'","*");
    }
    public static FileFilter getFilefilter() { return new FileNameExtensionFilter("Gbview (.gbx)", "gbx"); }
}
