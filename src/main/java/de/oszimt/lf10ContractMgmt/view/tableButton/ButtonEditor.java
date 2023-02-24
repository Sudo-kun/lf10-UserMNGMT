package de.oszimt.lf10ContractMgmt.view.tableButton;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton editButton;
    private JButton deleteButton;
    private int valueColumn;
    private JTable table;

    public ButtonEditor(int valueColumn) {
        this.valueColumn = valueColumn;
        this.editButton = new JButton("Edit");
        this.deleteButton = new JButton("Delete");
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        editButton.setActionCommand(String.valueOf(row));
        deleteButton.setActionCommand(String.valueOf(row));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        return buttonPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    public void setEditActionListener(ActionListener listener) {
        this.editButton.addActionListener(listener);
    }

    public void setDeleteActionListener(ActionListener listener) {
        this.deleteButton.addActionListener(listener);
    }
}
