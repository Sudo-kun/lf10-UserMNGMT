package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.model.Contract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public abstract class AbstractOverview extends JPanel {

    private ButtonEditor buttonEditor;

    private JTable table;

    protected DefaultTableModel model;

    protected final JButton addButton = new JButton("Add");

    protected static JButton innerSearchButton = new JButton("Search");

    protected int actionButtonsColumn = 5;
    protected int idColumn = 0;


    protected JPanel createTable() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        model = createModel();

        //create table
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(35);

        // create a search button
        innerSearchButton.setPreferredSize(new Dimension(100, 30));
        innerSearchButton.setMaximumSize(new Dimension(100, 30));
        innerSearchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerSearchButton.setAlignmentY(Component.TOP_ALIGNMENT);

        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.setMaximumSize(new Dimension(100, 30));
        addButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addButton.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setSize((int) (screenSize.getWidth() * 0.95), 40);
        searchPanel.setPreferredSize(new Dimension((int) (screenSize.getWidth() * 0.95), 40));
        searchPanel.add(innerSearchButton);
        searchPanel.add(Box.createHorizontalGlue());
        searchPanel.add(addButton);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        // create search functionality for table
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        innerSearchButton.addActionListener(e -> {
            innerSearchButton.setEnabled(false);

            createSearchWindow();
        });

        //set editor for action buttons column
        setTableValues();

        //create and show table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
        Dimension scrollPaneSize = new Dimension((int) (screenSize.getWidth() * 0.95), (int) (screenSize.getHeight() * 0.90));
        scrollPane.setSize(scrollPaneSize);
        scrollPane.setPreferredSize(scrollPaneSize);

        // table should be inside a new panel with a FlowLayout
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.add(searchPanel);
        tablePanel.add(scrollPane);

        // handle window resize and resize the table accordingly while keeping the space around the table constant
        setResizeListener(tablePanel, scrollPane, searchPanel);

        return tablePanel;
    }

    public void drawOverview() {
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

    protected abstract void createSearchWindow();

    protected void setResizeListener(JPanel tablePanel, JScrollPane scrollPane, JPanel searchPanel) {
        tablePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Table resized");
                super.componentResized(e);
                Dimension windowSize = tablePanel.getSize();
                Dimension newScrollPaneSize = new Dimension((int) (windowSize.getWidth() * 0.95), (int) (windowSize.getHeight() * 0.90));
                scrollPane.setSize(newScrollPaneSize);
                scrollPane.setPreferredSize(newScrollPaneSize);
                searchPanel.setSize((int) newScrollPaneSize.getWidth(), 40);
                searchPanel.setPreferredSize(new Dimension((int) (windowSize.getWidth() * 0.95), 40));

                tablePanel.revalidate();
                tablePanel.repaint();
            }
        });
    }


    protected abstract DefaultTableModel createModel();

    public void setTableValues() {
        buttonEditor = new ButtonEditor(idColumn);

        table.getColumnModel().getColumn(actionButtonsColumn).setCellRenderer(new ButtonRenderer(idColumn));
        table.getColumnModel().getColumn(actionButtonsColumn).setCellEditor(buttonEditor);
    }

    public void setEditActionListener(ActionListener actionListener) {
        buttonEditor.setEditActionListener(actionListener);
    }

    public void setNewActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void setDeleteActionListener(ActionListener actionListener) {
        buttonEditor.setDeleteActionListener(actionListener);
    }

    public abstract int getIdByRow(int row);

    public void removeRow(int row) {
        model.removeRow(row);
    }
}
