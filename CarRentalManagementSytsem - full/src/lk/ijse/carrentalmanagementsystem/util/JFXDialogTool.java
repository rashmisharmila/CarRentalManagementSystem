package lk.ijse.carrentalmanagementsystem.util;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class JFXDialogTool {

    private final JFXDialog dialog;

    public JFXDialogTool(Region region, StackPane container) {
        dialog = new JFXDialog();
        dialog.setContent(region);
        dialog.setBackground(Background.EMPTY);
        dialog.setDialogContainer(container);
        dialog.getStyleClass().add("jfx-dialog-overlay-pane");
    }
    public void setOnDialogOpened(EventHandler<JFXDialogEvent> action) {
        dialog.setOnDialogOpened(action);
    }

    public void setOnDialogClosed(EventHandler<JFXDialogEvent> action) {
        dialog.setOnDialogClosed(action);
    }

    public void show() {
        dialog.show();
    }

    public void close() {
        dialog.close();
    }
}
