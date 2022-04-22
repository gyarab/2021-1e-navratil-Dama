package com.example.dama321;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import static com.example.dama321.HelloApplication.tileSize;

public class Piece extends StackPane {

    private PieceType type;

    public PieceType getType(){
        return type;
    }

    public Piece(PieceType type, int x, int y){
        this.type = type;
        relocate(x*tileSize,y*tileSize);
        Circle piece = new Circle();
        piece.setRadius(tileSize/5);
        piece.setStroke(Color.BLACK);
        piece.setCenterX(tileSize - tileSize/2);
        piece.setCenterY(tileSize - tileSize/2);
        getChildren().addAll(piece);
        piece.setFill(type ==PieceType.WHITE ? Color.RED : Color.WHITE);
    }

    public double startX;
    public double startY;

    public void makeDraggable(Piece piece){
        piece.setOnMousePressed(e->{
            System.out.println("pressed");
            startX = e.getSceneX() - piece.getTranslateX();
            startY = e.getSceneY() - piece.getTranslateY();
        });
        piece.setOnMouseDragged(e->{
            System.out.println("Dragged");
            piece.setTranslateX( (e.getSceneX() - startX) );
            piece.setTranslateY( (e.getSceneY() - startY) );
        });
        piece.setOnMouseReleased(e->{
            piece.setTranslateX( (e.getSceneX() - startX)/(tileSize/20) );
            piece.setTranslateY( (e.getSceneY() - startY)/(tileSize/20) );
        });

    }
}