package org.gbview.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Singleton holding a list recently opened files.
 */
public class RecentlyOpenedFileList
{
    private static RecentlyOpenedFileList instance = null;

    private Preferences prefs;
    private String filePathListPref = "filePathListPref";
    private static String FILE_PATH_LIST_PREF_SEPARATOR = ";";

    private static int MAX_FILES = 5;
    private List<File> files = new ArrayList<File>();

    protected RecentlyOpenedFileList()
    {
        this.prefs = Preferences.userNodeForPackage(this.getClass());
        loadFromPreferences();
    }
    
    private void loadFromPreferences()
    {        
        String paths = prefs.get(filePathListPref,"");
        String[] pathTokens = paths.split(FILE_PATH_LIST_PREF_SEPARATOR);
        for(String pathToken : pathTokens)
        {
            if(!pathToken.isEmpty())
            {
                addFile(new File(pathToken));
            }
        }
    }
    
    private void saveToPreferences()
    {
        String paths = "";
        for(File file : getFiles())
        {
            paths += file.getAbsolutePath();
            paths += FILE_PATH_LIST_PREF_SEPARATOR;
        }

        this.prefs.put(filePathListPref,paths);
    }
    
    public static RecentlyOpenedFileList getInstance()
    {
        if(instance == null)
        {
            instance = new RecentlyOpenedFileList();
        }
        return instance;
    }

    public void addFile(File file)
    {
        if(files.contains(file))
        {
            files.remove(file);
        }
        files.add(0, file);

        if(files.size() > MAX_FILES)
        {
            files.remove(files.size()-1);
        }

        saveToPreferences();
    }

    public Collection<File> getFiles()
    {
        return files;
    }

    public File getFileFromName(String name)
    {
        for(File f : files)
        {
            if(f.getAbsolutePath().endsWith(name))
            {
                return f;
            }
        }
        
        return null;
    }

    public void clear()
    {
        files.clear();
    }
}
