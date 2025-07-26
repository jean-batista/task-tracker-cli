package main.ui.screens.utils;

public class ScreenUtils {
    
    public static String dash(int quantity) {
        String dash = "";
        for(int i = 0; i < quantity; i++) {
            dash += "-";
        }
        return dash;
    }

    public static String blank(int quantity) {
        String blank = "";
        for(int i = 0; i < quantity; i++) {
            blank += " ";
        }
        return blank;
    }

}
