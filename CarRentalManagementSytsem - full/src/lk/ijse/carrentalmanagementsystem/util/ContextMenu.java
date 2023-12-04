package lk.ijse.carrentalmanagementsystem.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class ContextMenu {


    private final JFXPopup popup;

    private final Node node;

    private JFXButton edit;

    private JFXButton delete;

    private JFXButton details;

    private JFXButton refresh;

    public ContextMenu(Node node) {
        this.node = node;

        popup = new JFXPopup();
        popup.setPopupContent(getContent());
    }

    public void setActionEdit(EventHandler action) {
        edit.setOnAction(action);
    }

    public void setActionDelete(EventHandler action) {
        delete.setOnAction(action);
    }

    public void setActionDetails(EventHandler action) {
        details.setOnAction(action);
    }

    public void setActionRefresh(EventHandler action) {
        refresh.setOnAction(action);
    }

    public void show() {
        node.setOnMouseClicked(ev -> {
            if (ev.getButton().equals(MouseButton.SECONDARY)) {
                popup.show(node);
                popup.setAnchorX(ev.getScreenX());
                popup.setAnchorY(ev.getScreenY());
            }
        });
    }

    public void hide() {
        popup.hide();
    }

    public JFXButton getEditButton() {
        return edit;
    }

    public JFXButton getDeleteButton() {
        return delete;
    }

    private VBox getContent() {
        edit = new JFXButton("Edit");

        edit.setAlignment(Pos.BASELINE_LEFT);
        edit.setContentDisplay(ContentDisplay. CENTER);
        style(edit);

        delete = new JFXButton("Delete");

        delete.setAlignment(Pos.BASELINE_LEFT);
        delete.setContentDisplay(ContentDisplay. CENTER);
        style(delete);

        details = new JFXButton("Details");

        details.setAlignment(Pos.BASELINE_LEFT);
        details.setContentDisplay(ContentDisplay. CENTER);
        style(details);

        refresh = new JFXButton("Refresh");

        refresh.setAlignment(Pos.BASELINE_LEFT);
        refresh.setContentDisplay(ContentDisplay. CENTER);
        style(refresh);

        VBox contextMenu = new VBox();
        contextMenu.setPrefSize(100, 100);
        contextMenu.getChildren().addAll(edit, delete, details, refresh);
        return contextMenu;
    }

    private void style(JFXButton button) {
        button.getStyleClass().add("button-context-menu");
    }
}
