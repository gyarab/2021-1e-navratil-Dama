package com.example.dama321;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static com.example.dama321.HelloApplication.tileSize;

public class Tile extends Rectangle {
    private Piece piece;

    public boolean hasPiece(){
        return piece != null;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Tile(boolean light, int x , int y){
        setWidth(tileSize);
        setHeight(tileSize);

        relocate(x*tileSize,y*tileSize);

        setFill(light == true ? Color.BLACK : Color.WHITE);
    }
}
