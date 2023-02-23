package de.oszimt.lf10ContractMgmt.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class AbstractOverview extends JPanel {
    protected abstract JPanel createTable();

    public void drawOverview(String title) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
}
