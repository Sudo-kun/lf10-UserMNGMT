package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.view.ActivityOverview;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(1920, 1080);
        window.setVisible(true);

        ActivityOverview activityOverview = new ActivityOverview(window);

        activityOverview.drawActivityOverview();
    }
}
