package de.oszimt.lf10ContractMgmt.util;

import java.awt.*;

public class FontUtil {

    public static final String DEFAULT_FONT_NAME = "Serif";
    public static final Integer DEFAULT_FONT_SIZE = 15;

    public static Font getDefaultFont() {
        return getDefaultFont(DEFAULT_FONT_SIZE);
    }

    public static Font getBoldFont() {
        return getBoldFont(DEFAULT_FONT_SIZE);
    }

    public static Font getBoldFont(Integer textSize) {
        return new Font(DEFAULT_FONT_NAME, Font.BOLD, textSize);
    }

    public static Font getDefaultFont(Integer textSize) {
        return new Font(DEFAULT_FONT_NAME, Font.PLAIN, textSize);
    }
}
