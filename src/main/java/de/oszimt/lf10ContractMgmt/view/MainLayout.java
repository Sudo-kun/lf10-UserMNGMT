package de.oszimt.lf10ContractMgmt.view;

import de.oszimt.lf10ContractMgmt.util.FontUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainLayout extends JPanel {
  private JLabel headlineLabel;

  private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  private JButton contractOverview;
  private JButton customerOverview;
  private JButton employeeOverview;
  private JPanel bodyPanel;
  private JScrollPane scrollPane;
  private JPanel headlinePanel;

  private JPanel body;


  public MainLayout() {
    windowInit();
    createHeadline();
    createBody();
  }

  private void windowInit() {
    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    // handle window resize
    setBorder(padding);
    setSize(1920, 1080);
    setPreferredSize(new Dimension(1920, 1080));

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        setSize(getSize());

        Dimension headlinePanelSize = new Dimension((int) (getWidth() * 0.95), (int) (getHeight() * 0.10));
        headlinePanel.setSize(headlinePanelSize);
        headlinePanel.setPreferredSize(headlinePanelSize);

        headlinePanel.revalidate();
        headlinePanel.repaint();

        Dimension scrollPaneSize = new Dimension(getWidth(), (int) (getHeight() * 0.90));
        scrollPane.setSize(scrollPaneSize);
        scrollPane.setPreferredSize(scrollPaneSize);

        body.setSize(scrollPaneSize);
        body.setPreferredSize(scrollPaneSize);

        Dimension bodyPanelSize = new Dimension((int) (getWidth() * 0.95), (int) (getHeight() * 0.90));
        bodyPanel.setSize(bodyPanelSize);
        bodyPanel.setPreferredSize(bodyPanelSize);

        body.revalidate();
        body.repaint();

        scrollPane.revalidate();
        scrollPane.repaint();

        bodyPanel.revalidate();
        bodyPanel.repaint();

        revalidate();
        repaint();
      }
    });

  }

  private void createHeadline() {
    headlinePanel = new JPanel();

    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    headlinePanel.setBorder(padding);

    headlineLabel = new JLabel();
    headlinePanel.setLayout(new BoxLayout(headlinePanel, BoxLayout.X_AXIS));

    Dimension headlinePanelSize = new Dimension((int) (screenSize.getWidth() * 0.95), (int) (screenSize.getHeight() * 0.10));
    headlinePanel.setSize(headlinePanelSize);
    headlinePanel.setPreferredSize(headlinePanelSize);

    headlineLabel.setFont(FontUtil.getBoldFont(25));
    headlinePanel.add(headlineLabel);

    contractOverview = new JButton("Aufträge");
    employeeOverview = new JButton("Mitarbeiter");
    customerOverview = new JButton("Kunden");

    headlinePanel.add(Box.createHorizontalGlue());

    headlinePanel.add(contractOverview);
    headlinePanel.add(Box.createHorizontalStrut(10));
    headlinePanel.add(employeeOverview);
    headlinePanel.add(Box.createHorizontalStrut(10));
    headlinePanel.add(customerOverview);

    headlinePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    headlinePanel.setAlignmentY(Component.TOP_ALIGNMENT);

    add(headlinePanel);
  }

  private void createBody() {
    bodyPanel = new JPanel();

    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    bodyPanel.setBorder(padding);

    scrollPane = new JScrollPane(bodyPanel);
    scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
    Dimension scrollPaneSize = new Dimension(getWidth(), (int) (getHeight() * 0.90));
    scrollPane.setSize(scrollPaneSize);
    scrollPane.setPreferredSize(scrollPaneSize);

    JPanel contentPanel = new JPanel();
    scrollPane.add(contentPanel);

    add(bodyPanel);
  }

  public void setHeadline(String headline) {
    headlineLabel.setText(headline);
  }

  public void setBody(JPanel body) {
    this.body = body;

    bodyPanel.removeAll();
    bodyPanel.add(body);
    bodyPanel.revalidate();
    bodyPanel.repaint();

    scrollPane.revalidate();
    scrollPane.repaint();

    revalidate();
    repaint();
  }

  public void setContractOverviewAction(ActionListener actionListener) {
    contractOverview.addActionListener(actionListener);
  }

  public void setCustomerOverviewAction(ActionListener actionListener) {
    customerOverview.addActionListener(actionListener);
  }

  public void setEmployeeOverviewAction(ActionListener actionListener) {
    employeeOverview.addActionListener(actionListener);
  }
}
