package com.example.dama321;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;


import static com.example.dama321.HelloApplication.tileSize;

public class Piece extends StackPane {

    private PieceType type;




    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        this.type = type;
        relocate(x * tileSize, y * tileSize);
        Circle piece = new Circle();
        piece.setRadius(tileSize / 2);
        piece.setStroke(Color.BLACK);
        piece.setCenterX(tileSize - tileSize / 2);
        piece.setCenterY(tileSize - tileSize / 2);
        getChildren().addAll(piece);
        piece.setFill(type == PieceType.RED ? Color.RED : Color.WHITE);

        Player redPlayer = new Player(PieceType.RED);
        Player whitePlayer = new Player(PieceType.WHITE);


        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            setLayoutY(e.getSceneY() - mouseY + oldY);
            setLayoutX(e.getSceneX() - mouseX + oldX);
        });


    }
        /*
        setOnMousePressed(e -> {

            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });

        setOnMouseDragged(e -> {
            setLayoutY(e.getSceneY() - mouseY + oldY);
            setLayoutX(e.getSceneX() - mouseX + oldX);
        });

         */


    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public void move(int x, int y) {
        oldX = x * tileSize;
        oldY = y * tileSize;
        setLayoutY(oldY);
        setLayoutX(oldX);
    }

    public void abortMove() {
        setLayoutX(oldX);
        setLayoutY(oldY);

    }
}
