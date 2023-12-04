package lk.ijse.carrentalmanagementsystem.util;

public class Alerts {
    public static void success(String title, String content){
        Dialog.createAlert(Dialog.Type.SUCCESS, title, content);
    }

    public static void error(String title, String content){
        Dialog.createAlert(Dialog.Type.ERROR, title, content);
    }
}
