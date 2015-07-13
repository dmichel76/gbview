package org.gbview.ui.options;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Dialog for all the preferences
 */
public class PreferencesDialog extends JDialog
{
    private ResourceBundle R = ResourceBundle.getBundle("strings");

    public PreferencesDialog(JFrame parent)
    {
        this.setTitle(R.getString("org.gbview.ui.options.PreferencesDialog.title"));

        this.setModal(false);
        this.setResizable(false);

        this.setLayout(new BorderLayout());
        this.add(createTabbedPane(),BorderLayout.CENTER);
        this.pack();

    }

    private JTabbedPane createTabbedPane()
    {
        // main tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // rendering Options tab
        RenderingOptionsUi renderingOptionsUi = new RenderingOptionsUi();
        tabbedPane.addTab(renderingOptionsUi.getTitle(),null,renderingOptionsUi);

        return tabbedPane;
    }

    public static void main(String[] args)
    {
        PreferencesDialog preferencesDialog = new PreferencesDialog(null);
        preferencesDialog.setVisible(true);

    }
}
