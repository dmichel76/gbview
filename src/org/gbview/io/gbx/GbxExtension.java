package org.gbview.io.gbx;

/**
 * Various extension allowed for the gbx format
 */
public enum GbxExtension
{
    GBX("gbx"),
    GBXV("gbxv");

    private final String extension;

    GbxExtension(String extension)
    {
        this.extension = extension;
    }

    public String getExtension()
    {
        return extension;
    }
}
