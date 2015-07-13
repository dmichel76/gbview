package org.gbview.ui;

import org.swingplus.JHyperlink;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

/**
 * About box window
 */
public class AboutBox extends JDialog
{
    private final int MARGIN = 25;

    private ResourceBundle R = ResourceBundle.getBundle("strings");

    public AboutBox()
    {

        this.setTitle("About " + R.getString("org.gbview.Name"));

        this.setModal(true);
        this.setResizable(false);

        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
        this.add(main);
        
        JPanel top = createTopPanel();
        main.add(top,BorderLayout.NORTH);

        JPanel bottom = createBottomPanel();
        main.add(bottom,BorderLayout.CENTER);

        this.pack();

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                we.getWindow().getParent().requestFocusInWindow();
            }
        });

    }

    private JPanel createBottomPanel()
    {
        JPanel panel = new JPanel();
        BoxLayout box = new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(box);

        JLabel version = new JLabel("version: " + R.getString("org.gbview.Version"));
        version.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel license = new JLabel("License: " + R.getString("org.gbview.License"));
        license.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel year = new JLabel(R.getString("org.gbview.Year"));
        year.setAlignmentX(Component.CENTER_ALIGNMENT);

        JHyperlink websiteLink = new JHyperlink(R.getString("org.gbview.Website"),R.getString("org.gbview.Website"));
        websiteLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        JHyperlink emailLink = new JHyperlink(R.getString("org.gbview.Email"),"mailto:"+R.getString("org.gbview.Email"));
        emailLink.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(new JSeparator());
        panel.add(new JLabel(" "));
        panel.add(version);
        panel.add(license);
        panel.add(year);
        panel.add(new JLabel(" "));
        panel.add(emailLink);
        panel.add(websiteLink);

        return panel;

    }

    private JPanel createTopPanel()
    {
        JPanel panel = new JPanel();
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("resources/icon.png"));
        panel.add(new JLabel(icon));

        JLabel title = new JLabel(R.getString("org.gbview.Name"));
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20));
        panel.add(title);

        return panel;
    }

    public static void main(String[] args)
    {
        AboutBox aboutBox = new AboutBox();
        aboutBox.setVisible(true);
    }
}
