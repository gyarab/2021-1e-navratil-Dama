package com.example.dama321;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Arrays;

public class HelloApplication extends Application {
    //Creating parameters
    public static final int tileSize = 100;
    public static final int width = 8;
    public static final int height = 8;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private Tile[][] board= new Tile[width][height];
    public Button[][] b = new Button[width][height];

    @Override
    public void start(Stage stage) throws IOException {
        //Creating basic window
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        root.setPrefSize(height*tileSize, width*tileSize);
        root.getChildren().addAll(tileGroup,pieceGroup);

        //Setting up main board with pieces
        for (int y =0; y<height;y++){
            for (int x = 0; x<width;x++){
                Tile tile = new Tile((x + y )%2==0, x , y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);
                Piece piece = null;

                if (y<2 &&(x + y )%2!=0){
                    piece=makePiece(PieceType.WHITE, x,y);
                    piece.makeDraggable(piece);
                }else if (y>5 &&(x + y )%2 != 0){
                    piece=makePiece(PieceType.RED, x,y);
                    piece.makeDraggable(piece);
                }
                if (piece != null){
                    pieceGroup.getChildren().add(piece);
                    tile.setPiece(piece);
                }

            }
        }
        stage.setResizable(true);
        stage.setScene(scene);
        stage.setTitle("dama");
        stage.show();
    }

    private Piece makePiece(PieceType type, int x, int y){
    Piece piece = new Piece(type, x , y);


    return piece;
    }
}



