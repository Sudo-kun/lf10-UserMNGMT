package de.oszimt.lf10ContractMgmt.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class AbstractOverview extends JPanel {
    protected abstract JPanel createTable();

    public void drawOverview(String title) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel headlinePanel = createHeadline(title);
        add(Box.createVerticalGlue());
        add(headlinePanel);
        add(Box.createVerticalGlue());

        JPanel tablePanel = createTable();
        add(tablePanel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                setSize(getSize());
                revalidate();
                repaint();
            }
        });
    }
    
    protected static JPanel createHeadline(String headline) {
        JPanel headlinePanel = new JPanel();
        JLabel headlineLabel = new JLabel(headline);
        headlinePanel.add(headlineLabel);

        return headlinePanel;
    }
}
