package org.gbview.io;

import org.gbview.model.Model;

import java.io.IOException;

/**
 * Interface for parsers.
 */
public interface FileParser
{
    void parse(String line) throws IOException;
    Model getModel();
}
