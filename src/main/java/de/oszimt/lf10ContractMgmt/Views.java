package de.oszimt.lf10ContractMgmt;

import de.oszimt.lf10ContractMgmt.view.CustomerView;
import de.oszimt.lf10ContractMgmt.view.LoginPanel;

public enum Views {
    LOGIN("Login", LoginPanel.class),
    CONTACT("Edit/Create Contact", CustomerView.class);

    private final String title;
    private final Class<?> viewClass;

    Views(String title, Class<?> viewClass) {
        this.title = title;
        this.viewClass = viewClass;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getViewClass() {
        return viewClass;
    }
}
