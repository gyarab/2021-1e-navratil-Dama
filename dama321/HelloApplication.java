package com.example.dama321;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.Pane;



public class HelloApplication extends Application {

    public static final int tileSize = 90;
    public static final int width = 8;
    public static final int height = 8;
    public Player currentPlayer=null;
    public Player redPlayer = new Player(PieceType.RED);
    public Player whitePlayer = new Player(PieceType.WHITE);



    private Tile[][] board = new Tile[width][height];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);
                Piece piece = null;
                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.RED, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(PieceType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        Scene scene = new Scene(root);
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        int startX = boardCoords(piece.getOldX());
        int startY = boardCoords(piece.getOldY());

        if (((newX - startX) == 1 || (newX - startX) == -1 )&& newY - startY == piece.getType().moveDir) {

            return new MoveResult(MoveType.NORMAL);
        } else if (((newX - startX) == 2 || (newX - startX) == -2 ) && newY - startY == piece.getType().moveDir * 2) {

            int x1 = startX + (newX - startX) / 2;
            int y1 = startY + (newY - startY) / 2;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
            }
        }



        return new MoveResult(MoveType.NONE);
    }

    private int boardCoords(double pixel) {
        return (int)(pixel + tileSize / 2) / tileSize;
    }
    //davani figurek na mista


    private Piece makePiece(PieceType type, int x, int y) {
        currentPlayer = whitePlayer;

        Piece piece = new Piece(type, x, y);
        //piece i pravidla jak se piece muze pohybovat

        /*piece.setOnMouseReleased(e -> {
            if (t.equals(true)){

                if (type == PieceType.RED){
                    /*
                    int newX = boardCoords(piece.getLayoutX());
                    int newY = boardCoords(piece.getLayoutY());

                    MoveResult result;

                    if (newX < 0 || newY < 0 ) {
                        result = new MoveResult(MoveType.NONE);
                    } else {
                        result = tryMove(piece, newX, newY);
                    }

                    int startX = boardCoords(piece.getOldX());
                    int startY = boardCoords(piece.getOldY());


                    switch (result.getType()) {
                        case NONE:
                            piece.abortMove();
                            break;
                        case NORMAL:
                            piece.move(newX, newY);
                            board[startX][startY].setPiece(null);
                            board[newX][newY].setPiece(piece);
                            if (newY == 7 || newY == 0){
                                Label l = new Label("WIN");
                                Label p = new Label(" ");
                                p.setTranslateY(0);
                                p.setTranslateX(0);
                                p.setPrefSize(width * tileSize, height*tileSize);
                                l.setTranslateX(tileSize);
                                l.setTranslateY(3*tileSize);
                                l.setTextFill(Color.BLUE);
                                l.setFont(new Font("Arial", 300));
                                System.out.println("vyhra");
                                pieceGroup.getChildren().addAll(l,p);
                            }
                            break;
                        case KILL:
                            piece.move(newX, newY);
                            board[startX][startY].setPiece(null);
                            board[newX][newY].setPiece(piece);
                            if (newY == 7 || newY == 0){
                                Label l = new Label("WIN");
                                Label p = new Label(" ");
                                p.setTranslateY(0);
                                p.setTranslateX(0);
                                p.setPrefSize(width * tileSize, height*tileSize);
                                l.setTranslateX(tileSize);
                                l.setTranslateY(3*tileSize);
                                l.setTextFill(Color.BLUE);
                                l.setFont(new Font("Arial", 300));
                                System.out.println("WIN");
                                pieceGroup.getChildren().addAll(l,p);
                            }
                            Piece otherPiece = result.getPiece();
                            board[boardCoords(otherPiece.getOldX())][boardCoords(otherPiece.getOldY())].setPiece(null);
                            pieceGroup.getChildren().remove(otherPiece);
                            break;
                    }

                    t.equals(false);


                }else{

                }

            }else if (t.equals(false)){

                    if (type == PieceType.WHITE){

                        t.equals(false);
                    }else{

                    }

                }
            */
        /**
         int newX = boardCoords(piece.getLayoutX());
         int newY = boardCoords(piece.getLayoutY());

         MoveResult result;

         if (newX < 0 || newY < 0 ) {
         result = new MoveResult(MoveType.NONE);
         } else {
         result = tryMove(piece, newX, newY);
         }

         int startX = boardCoords(piece.getOldX());
         int startY = boardCoords(piece.getOldY());


         switch (result.getType()) {
         case NONE:
         piece.abortMove();
         break;
         case NORMAL:
         piece.move(newX, newY);
         board[startX][startY].setPiece(null);
         board[newX][newY].setPiece(piece);
         if (newY == 7 || newY == 0){
         Label l = new Label("WIN");
         Label p = new Label(" ");
         p.setTranslateY(0);
         p.setTranslateX(0);
         p.setPrefSize(width * tileSize, height*tileSize);
         l.setTranslateX(tileSize);
         l.setTranslateY(3*tileSize);
         l.setTextFill(Color.BLUE);
         l.setFont(new Font("Arial", 300));
         System.out.println("vyhra");
         pieceGroup.getChildren().addAll(l,p);
         }
         break;
         case KILL:
         piece.move(newX, newY);
         board[startX][startY].setPiece(null);
         board[newX][newY].setPiece(piece);
         if (newY == 7 || newY == 0){
         Label l = new Label("WIN");
         Label p = new Label(" ");
         p.setTranslateY(0);
         p.setTranslateX(0);
         p.setPrefSize(width * tileSize, height*tileSize);
         l.setTranslateX(tileSize);
         l.setTranslateY(3*tileSize);
         l.setTextFill(Color.BLUE);
         l.setFont(new Font("Arial", 300));
         System.out.println("WIN");
         pieceGroup.getChildren().addAll(l,p);
         }
         Piece otherPiece = result.getPiece();
         board[boardCoords(otherPiece.getOldX())][boardCoords(otherPiece.getOldY())].setPiece(null);
         pieceGroup.getChildren().remove(otherPiece);
         break;
         }

         });
         */


        piece.setOnMouseReleased(e -> {

            if (currentPlayer == whitePlayer && PieceType.WHITE == type){
                int newX = boardCoords(piece.getLayoutX());
                int newY = boardCoords(piece.getLayoutY());

                MoveResult result;

                if (newX < 0 || newY < 0 ) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(piece, newX, newY);
                }

                int startX = boardCoords(piece.getOldX());
                int startY = boardCoords(piece.getOldY());


                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.move(newX, newY);
                        board[startX][startY].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        if (newY == 7 || newY == 0){
                            Label l = new Label("WIN");
                            Label p = new Label(" ");
                            p.setTranslateY(0);
                            p.setTranslateX(0);
                            p.setPrefSize(width * tileSize, height*tileSize);
                            l.setTranslateX(tileSize);
                            l.setTranslateY(3*tileSize);
                            l.setTextFill(Color.BLUE);
                            l.setFont(new Font("Arial", 300));

                            pieceGroup.getChildren().addAll(l,p);
                        }
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[startX][startY].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        if (newY == 7 || newY == 0){
                            Label l = new Label("WIN");
                            Label p = new Label(" ");
                            p.setTranslateY(0);
                            p.setTranslateX(0);
                            p.setPrefSize(width * tileSize, height*tileSize);
                            l.setTranslateX(tileSize);
                            l.setTranslateY(3*tileSize);
                            l.setTextFill(Color.BLUE);
                            l.setFont(new Font("Arial", 300));
                            pieceGroup.getChildren().addAll(l,p);
                        }
                        Piece otherPiece = result.getPiece();
                        board[boardCoords(otherPiece.getOldX())][boardCoords(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;

                }
                currentPlayer = redPlayer;
            }else if (currentPlayer == redPlayer && type == PieceType.RED){
                int newX = boardCoords(piece.getLayoutX());
                int newY = boardCoords(piece.getLayoutY());

                MoveResult result;

                if (newX < 0 || newY < 0 ) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(piece, newX, newY);
                }

                int startX = boardCoords(piece.getOldX());
                int startY = boardCoords(piece.getOldY());


                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.move(newX, newY);
                        board[startX][startY].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        if (newY == 7 || newY == 0) {
                            Label l = new Label("WIN");
                            Label p = new Label(" ");
                            p.setTranslateY(0);
                            p.setTranslateX(0);
                            p.setPrefSize(width * tileSize, height * tileSize);
                            l.setTranslateX(tileSize);
                            l.setTranslateY(3 * tileSize);
                            l.setTextFill(Color.BLUE);
                            l.setFont(new Font("Arial", 300));
                            System.out.println("vyhra");
                            pieceGroup.getChildren().addAll(l, p);
                        }
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[startX][startY].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        if (newY == 7 || newY == 0) {
                            Label l = new Label("WIN");
                            Label p = new Label(" ");
                            p.setTranslateY(0);
                            p.setTranslateX(0);
                            p.setPrefSize(width * tileSize, height * tileSize);
                            l.setTranslateX(tileSize);
                            l.setTranslateY(3 * tileSize);
                            l.setTextFill(Color.BLUE);
                            l.setFont(new Font("Arial", 300));
                            System.out.println("WIN");
                            pieceGroup.getChildren().addAll(l, p);
                        }
                        Piece otherPiece = result.getPiece();
                        board[boardCoords(otherPiece.getOldX())][boardCoords(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;
                }
                currentPlayer = whitePlayer;
            }else {
                int newX = boardCoords(piece.getLayoutX());
                int newY = boardCoords(piece.getLayoutY());

                MoveResult result;

                if (newX < 0 || newY < 0) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(piece, newX, newY);
                }

                int startX = boardCoords(piece.getOldX());
                int startY = boardCoords(piece.getOldY());


                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.abortMove();
                        break;
                    case KILL:
                        piece.abortMove();
                        break;
                }
            }
                /*
                int newX = boardCoords(piece.getLayoutX());
                int newY = boardCoords(piece.getLayoutY());

                MoveResult result;

                if (newX < 0 || newY < 0 ) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(piece, newX, newY);
                }

                int startX = boardCoords(piece.getOldX());
                int startY = boardCoords(piece.getOldY());


                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.move(newX, newY);
                        board[startX][startY].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        if (newY == 7 || newY == 0){
                            Label l = new Label("WIN");
                            Label p = new Label(" ");
                            p.setTranslateY(0);
                            p.setTranslateX(0);
                            p.setPrefSize(width * tileSize, height*tileSize);
                            l.setTranslateX(tileSize);
                            l.setTranslateY(3*tileSize);
                            l.setTextFill(Color.BLUE);
                            l.setFont(new Font("Arial", 300));
                            System.out.println("vyhra");
                            pieceGroup.getChildren().addAll(l,p);
                        }
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[startX][startY].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        if (newY == 7 || newY == 0){
                            Label l = new Label("WIN");
                            Label p = new Label(" ");
                            p.setTranslateY(0);
                            p.setTranslateX(0);
                            p.setPrefSize(width * tileSize, height*tileSize);
                            l.setTranslateX(tileSize);
                            l.setTranslateY(3*tileSize);
                            l.setTextFill(Color.BLUE);
                            l.setFont(new Font("Arial", 300));
                            System.out.println("WIN");
                            pieceGroup.getChildren().addAll(l,p);
                        }
                        Piece otherPiece = result.getPiece();
                        board[boardCoords(otherPiece.getOldX())][boardCoords(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;

                }
            */
        });

        return piece;
    }
    public static void main(String[] args) {
        launch(args);
    }

}