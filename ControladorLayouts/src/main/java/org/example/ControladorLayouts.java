package org.example;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.List;

public class ControladorLayouts {

    @FXML private ComboBox<String> comboLayouts;
    @FXML private Pane panelContenedor;

    private List<Node> componentesOriginales;

    @FXML
    public void initialize() {
        if (comboLayouts != null) {
            comboLayouts.getItems().addAll(
                    "BorderLayout (BorderPane)",
                    "FlowLayout (HBox)",
                    "BoxLayout (VBox)",
                    "GridLayout (GridPane)",
                    "TileLayout (TilePane)",
                    "StackLayout (StackPane)",
                    "AbsoluteLayout (AnchorPane)"
            );
        }
    }

    @FXML
    void cambiarLayout() {
        if (componentesOriginales == null) {
            componentesOriginales = new ArrayList<>(panelContenedor.getChildren());
        }

        String seleccion = comboLayouts.getValue();
        if (seleccion == null) return;

        panelContenedor.getChildren().clear();
        Pane nuevoLayout;

        switch (seleccion) {
            case "BorderLayout (BorderPane)":
                BorderPane bp = new BorderPane();
                bp.setPadding(new Insets(10));
                if (componentesOriginales.size() > 0) bp.setCenter(componentesOriginales.get(0));
                if (componentesOriginales.size() > 1) bp.setTop(componentesOriginales.get(1));
                if (componentesOriginales.size() > 2) bp.setLeft(componentesOriginales.get(2));
                if (componentesOriginales.size() > 3) bp.setBottom(componentesOriginales.get(3));
                nuevoLayout = bp;
                break;

            case "GridLayout (GridPane)":
                GridPane gp = new GridPane();
                gp.setHgap(15); gp.setVgap(15);
                gp.setAlignment(Pos.CENTER);
                for (int i = 0; i < componentesOriginales.size(); i++) {
                    gp.add(componentesOriginales.get(i), i % 2, i / 2);
                }
                nuevoLayout = gp;
                break;

            case "TileLayout (TilePane)":
                TilePane tp = new TilePane();
                tp.setHgap(10); tp.setVgap(10);
                tp.setPrefColumns(2);
                tp.setAlignment(Pos.CENTER);
                tp.getChildren().addAll(componentesOriginales);
                nuevoLayout = tp;
                break;

            case "AbsoluteLayout (AnchorPane)":
                AnchorPane ap = new AnchorPane();
                if (componentesOriginales.size() > 0) {
                    Node n = componentesOriginales.get(0);
                    AnchorPane.setTopAnchor(n, 20.0);
                    AnchorPane.setLeftAnchor(n, 20.0);
                }
                ap.getChildren().addAll(componentesOriginales);
                nuevoLayout = ap;
                break;

            case "FlowLayout (HBox)":
                HBox hb = new HBox(15);
                hb.setAlignment(Pos.CENTER);
                hb.getChildren().addAll(componentesOriginales);
                nuevoLayout = hb;
                break;

            case "BoxLayout (VBox)":
                VBox vb = new VBox(15);
                vb.setAlignment(Pos.CENTER);
                vb.getChildren().addAll(componentesOriginales);
                nuevoLayout = vb;
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
    }
}