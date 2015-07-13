package org.gbview.ui.options;

import net.miginfocom.swing.MigLayout;
import org.gbview.rendering.options.Options;
import org.gbview.rendering.options.RendererOptions;
import org.gbview.rendering.options.RotationModeOption;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * {@link JPanel} for the rendering Options
 */
public class RenderingOptionsUi extends JPanel
{
    public String title;
    private RendererOptions rendererOptions = new RendererOptions();

    private ResourceBundle R = ResourceBundle.getBundle("strings");

    public RenderingOptionsUi()
    {

        this.title = R.getString("org.gbview.ui.options.RenderingOptionsUi.title");

        this.setLayout(new MigLayout("wrap 2"));

        this.add(new JLabel(R.getString("org.gbview.ui.options.RenderingOptionsUi.Zooming")), "split 2, span");
        this.add(new JSeparator(), "growx, wrap");
        addSpinnerFloatOption(R.getString("org.gbview.ui.options.RenderingOptionsUi.ZoomingSpeedWheel"), 0.02f, rendererOptions.getZoomingSpeedWheelOption());
        addSpinnerFloatOption(R.getString("org.gbview.ui.options.RenderingOptionsUi.ZoomingSpeedMouse"), 0.02f, rendererOptions.getZoomingSpeedMouseOption());

        this.add(new JLabel(R.getString("org.gbview.ui.options.RenderingOptionsUi.Translation")), "split 2, span");
        this.add(new JSeparator(), "growx, wrap");
        addSpinnerFloatOption(R.getString("org.gbview.ui.options.RenderingOptionsUi.TranslationSpeed"), 0.02f, rendererOptions.getTranslationSpeedOption());

        this.add(new JLabel(R.getString("org.gbview.ui.options.RenderingOptionsUi.Rotation")), "split 2, span");
        this.add(new JSeparator(), "growx, wrap");
        addSpinnerFloatOption(R.getString("org.gbview.ui.options.RenderingOptionsUi.RotationSpeed"), 0.02f, rendererOptions.getRotationSpeedOption());

        JLabel label = new JLabel(R.getString("org.gbview.ui.options.RenderingOptionsUi.RotationMode"));
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        this.add(label);
        final JComboBox rotationModeBox = new JComboBox(RotationModeOption.RotationModes.values());
        rotationModeBox.setSelectedItem(rendererOptions.getRotationModeOption().getValue());
        rotationModeBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                rendererOptions.getRotationModeOption().setValue(rotationModeBox.getSelectedItem().toString());
            }
        });
        this.add(rotationModeBox);



    }

    private void addSpinnerFloatOption(String name, float step, final Options option)
    {

        JLabel label = new JLabel(name);
        label.setFont(label.getFont().deriveFont(Font.PLAIN));
        this.add(label);

        float value = Float.valueOf(option.getValue().toString());
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(value, null, null, step);
        final JSpinner spinner = new JSpinner(spinnerNumberModel);

        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinner.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(2);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        Dimension d = spinner.getPreferredSize();
        d.width = 50;
        spinner.setPreferredSize(d);

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float f = Float.valueOf(spinner.getValue().toString());
                option.setValue(f);
            }
        });
        this.add(spinner);
    }

    public String getTitle()
    {
        return title;
    }
}
