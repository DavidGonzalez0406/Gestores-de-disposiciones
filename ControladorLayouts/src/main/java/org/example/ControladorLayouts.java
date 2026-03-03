package org.example;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class ControladorLayouts {

    @FXML private ComboBox<String> comboLayouts;
    @FXML private Pane panelContenedor;

    private List<Node> componentesOriginales;

    @FXML
    public void initialize() {
        if (comboLayouts != null) {
            // Llenamos el combo con nombres claros para el usuario
            comboLayouts.getItems().addAll(
                    "BorderLayout (BorderPane)",
                    "FlowLayout (HBox)",
                    "BoxLayout (VBox)",
                    "GridLayout (GridPane)",
                    "TileLayout (TilePane)",
                    "StackLayout (StackPane)"
            );
        }
    }

    @FXML
    void cambiarLayout() {
        if (componentesOriginales == null && panelContenedor != null) {
            componentesOriginales = new ArrayList<>(panelContenedor.getChildren());
        }

        String seleccion = comboLayouts.getValue();
        if (seleccion == null || panelContenedor == null) return;

        panelContenedor.getChildren().clear();
        Pane nuevoLayout;

        switch (seleccion) {
            case "BorderLayout (BorderPane)":
                BorderPane bp = new BorderPane();
                bp.setPadding(new Insets(20));
                if (componentesOriginales.size() > 0) bp.setCenter(componentesOriginales.get(0));
                if (componentesOriginales.size() > 1) bp.setTop(componentesOriginales.get(1));
                if (componentesOriginales.size() > 2) bp.setLeft(componentesOriginales.get(2));
                if (componentesOriginales.size() > 3) bp.setBottom(componentesOriginales.get(3));
                nuevoLayout = bp;
                break;

            case "GridLayout (GridPane)":
                GridPane gp = new GridPane();
                gp.setHgap(20); gp.setVgap(20);
                gp.setAlignment(Pos.CENTER);
                for (int i = 0; i < componentesOriginales.size(); i++) {
                    gp.add(componentesOriginales.get(i), i % 2, i / 2);
                }
                nuevoLayout = gp;
                break;

            case "BoxLayout (VBox)":
                VBox vb = new VBox(20);
                vb.setAlignment(Pos.CENTER);
                vb.getChildren().addAll(componentesOriginales);
                nuevoLayout = vb;
                break;

            case "FlowLayout (HBox)":
                HBox hb = new HBox(20);
                hb.setAlignment(Pos.CENTER);
                hb.getChildren().addAll(componentesOriginales);
                nuevoLayout = hb;
                break;

            case "TileLayout (TilePane)":
                TilePane tp = new TilePane();
                tp.setHgap(15); tp.setVgap(15);
                tp.setAlignment(Pos.CENTER);
                tp.getChildren().addAll(componentesOriginales);
                nuevoLayout = tp;
                break;

            case "StackLayout (StackPane)":
                StackPane sp = new StackPane();
                sp.getChildren().addAll(componentesOriginales);
                nuevoLayout = sp;
                break;

            default:
                nuevoLayout = new Pane();
                nuevoLayout.getChildren().addAll(componentesOriginales);
        }

        nuevoLayout.prefWidthProperty().bind(panelContenedor.widthProperty());
        nuevoLayout.prefHeightProperty().bind(panelContenedor.heightProperty());

        panelContenedor.getChildren().add(nuevoLayout);

        FadeTransition ft = new FadeTransition(Duration.millis(350), nuevoLayout);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
}