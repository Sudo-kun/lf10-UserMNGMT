package de.oszimt.lf10ContractMgmt.view.tableButton;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonRenderer implements TableCellRenderer {

    private JButton editButton;
    private JButton deleteButton;
    private int valueColumn;

    private JTable table;

    public ButtonRenderer(int valueColumn) {
        this.valueColumn = valueColumn;
        this.editButton = new JButton("Bearbeiten");
        this.deleteButton = new JButton("Löschen");
        // add action listeners to the buttons
        this.editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
            }
        });
        this.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        editButton.setActionCommand(String.valueOf(row));
        deleteButton.setActionCommand(String.valueOf(row));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        return buttonPanel;
    }
}
