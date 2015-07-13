package org.gbview.ui;

import org.gbview.Gbview;
import org.gbview.io.gbx.GbxFormat;
import org.gbview.ui.options.PreferencesDialog;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * Class for creating the menu bar.
 */
public class MenuBar
{
    private Gbview gbview;

    private JMenuBar menuBar;
    private ResourceBundle R = ResourceBundle.getBundle("strings");

    public MenuBar(Gbview gbview)
    {
        this.gbview = gbview;

        this.menuBar = new JMenuBar();
        this.menuBar.add(createFileMenu(this.gbview));
        this.menuBar.add(createOptionsMenu(this.gbview));
        this.menuBar.add(createHelpMenu(this.gbview));
    }

    public JMenuBar getMenuBar()
    {
        return this.menuBar;
    }

    private JMenu createFileMenu(final JFrame parent)
    {
        final Preferences prefs = Preferences.userNodeForPackage(this.getClass());

        JMenuItem fileOpenItem = new JMenuItem(R.getString("org.gbview.ui.MenuBar.File.Open"));
        fileOpenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.setFileFilter(GbxFormat.getFilefilter());

                // set directory to the last one used
                String dir = prefs.get("directory",System.getProperty("user.home"));
                fileChooser.setCurrentDirectory(new File(dir));

                if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    gbview.loadFile(file);

                    // save filepath for the "recently open" file list
                    RecentlyOpenedFileList rofl = RecentlyOpenedFileList.getInstance();
                    rofl.addFile(file);

                    // save directory for next time
                    dir = fileChooser.getCurrentDirectory().getAbsolutePath();
                    prefs.put("directory",dir);
                }
            }
        });

        final JMenu fileReopenMenu = createRecentlyOpenedFilesMenu();

        final JMenuItem fileCloseItem = new JMenuItem(R.getString("org.gbview.ui.MenuBar.File.Close"));
        fileCloseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gbview.closeFile();
            }
        });

        JMenuItem fileExitItem = new JMenuItem(R.getString("org.gbview.ui.MenuBar.File.Exit"));
        fileExitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gbview.exit();
            }
        });

        JMenu fileMenu = new JMenu(R.getString("org.gbview.ui.MenuBar.File"));
        fileMenu.add(fileOpenItem);
        fileMenu.add(fileReopenMenu);
        fileMenu.add(fileCloseItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileExitItem);

        fileMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                RecentlyOpenedFileList rofl = RecentlyOpenedFileList.getInstance();
                fileReopenMenu.setEnabled(!rofl.getFiles().isEmpty());
                fileCloseItem.setEnabled(gbview.isFileLoaded());
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        return fileMenu;
    }

    private void populateRecentlyOpenedFilesMenu(JMenu menu)
    {
        final RecentlyOpenedFileList rofl = RecentlyOpenedFileList.getInstance();
        for(final File f : rofl.getFiles())
        {
            JMenuItem i = new JMenuItem(f.getName());
            i.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    gbview.loadFile(f);
                }
            });

            menu.add(i);
        }

        menu.add(new JSeparator());

        JMenuItem clearListItem = new JMenuItem(R.getString("org.gbview.ui.MenuBar.File.Reopen.ClearList"));
        clearListItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String title = R.getString("org.gbview.ui.MenuBar.File.Reopen.ClearList.confirm.title");
                String msg = R.getString("org.gbview.ui.MenuBar.File.Reopen.ClearList.confirm.message");

                int confirmed = JOptionPane.showConfirmDialog(gbview,msg,title,JOptionPane.YES_NO_OPTION);

                if(confirmed == 1)
                {
                    // user clicked "no"
                    return;
                }

                rofl.clear();
            }
        });
        menu.add(clearListItem);

    }

    private JMenu createRecentlyOpenedFilesMenu()
    {
        final RecentlyOpenedFileList rofl = RecentlyOpenedFileList.getInstance();

        JMenu recentlyOpenedFilesMenu = new JMenu(R.getString("org.gbview.ui.MenuBar.File.Reopen"));

        populateRecentlyOpenedFilesMenu(recentlyOpenedFilesMenu);

        recentlyOpenedFilesMenu.addMenuListener(new MenuListener()
        {
            @Override
            public void menuSelected(MenuEvent e)
            {
                JMenu menu = (JMenu) e.getSource();
                menu.removeAll();
                populateRecentlyOpenedFilesMenu(menu);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}

        });

        return recentlyOpenedFilesMenu;

    }

    private JMenu createOptionsMenu(final JFrame parent)
    {
        JMenuItem preferencesItem = new JMenuItem(R.getString("org.gbview.ui.MenuBar.Options.Preferences"));
        preferencesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreferencesDialog preferencesDialog = new PreferencesDialog(parent);
                preferencesDialog.setLocationRelativeTo(parent);
                preferencesDialog.setVisible(true);
            }
        });

        JMenu optionMenu = new JMenu(R.getString("org.gbview.ui.MenuBar.Options"));
        optionMenu.add(preferencesItem);

        return optionMenu;
    }

    private JMenu createHelpMenu(final JFrame parent)
    {
        JMenuItem aboutItem = new JMenuItem(R.getString("org.gbview.ui.MenuBar.Help"));
        aboutItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AboutBox aboutBox = new AboutBox();
                aboutBox.setLocationRelativeTo(parent);
                aboutBox.setVisible(true);
            }
        });

        JMenu helpMenu = new JMenu(R.getString("org.gbview.ui.MenuBar.Help.About"));
        helpMenu.add(aboutItem);

        return helpMenu;
    }
}
