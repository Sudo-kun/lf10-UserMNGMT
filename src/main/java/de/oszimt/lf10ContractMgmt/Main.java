package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.mock_data.MockDataCreator;
import de.oszimt.lf10ContractMgmt.view.ActivityOverview;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(1920, 1080);
        window.setVisible(true);

        MockDataCreator mockDataCreator = new MockDataCreator();

        ActivityOverview activityOverview = new ActivityOverview(window);

        activityOverview.drawActivityOverview(mockDataCreator.createContractMockData((int) (Math.random() * 20) + 1));
    }
}
