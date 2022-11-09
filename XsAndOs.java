/*
 *Algorithm
 * 1. Create basic attributes for the game like the board, status label, and whever the game is over.
 * 2. Create a 3x3 GridPane with indivudal cells
 * 3. Create a scene to dispaly with 9 boxes
 * 4. Create a method to determine if board is full
 * 5. Create method to determine if there is a winner either diagonally, horizontally, or verticlally.
 * 6. Create Cell class inlcuding a token for a blank space, X, or O.
 * 7. Create main game method that determines what happens next and updates the board and status label.
 */
// Author(s): Daniel Lubin , Gabriel Lopez, Calvin Zhou 
// Date of Last Modification: 11/8/2022
// Course: CS111B 
// Instructor: C. Conner 
// Assignment #6
// File Name: XsAndOs.Java
// This program creates a Tic Tac Toe game with two players, X and O.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;

public class XsAndOs  extends Application {

    private boolean gameOver = false;  //flag
    private char whoseTurn = 'X'; // 'X' or 'O' but 'X' starts 
    private Cell[][] board =  new Cell[3][3];  //the 2-D array board for playing
    private Label statusLabel = new Label("X's turn to play");  //let user know status of game

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane(); 
        // Nested loop that creates 9 spaces, made of a 3x3 gridpane
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                pane.add(board[i][j] = new Cell(), j, i);
            }
        }

        BorderPane borderPane = new BorderPane(); //the BoarderPane gives us control over layout of stage
        borderPane.setCenter(pane);  //set center of the stage to be the GridPane object 'pane'
        borderPane.setBottom(statusLabel); //set bottom of the stage to be the Label object 'statusLabel'
    
        Scene scene = new Scene(borderPane, 300, 300); 
        primaryStage.setTitle("XsAndOs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //Nested loop that goes through each cell and determines if the board is full.
    public boolean isFull() {
        for (int i = 0; i < 3; i++)
          for (int j = 0; j < 3; j++)
            if (board[i][j].getToken() == ' ')
              return false;
        return true;
      }
    
    public boolean hasWon(char tkn) {
        //Checks to see if there is a winner Horizontally
        for(int i = 0; i < 3; i++){
            if (board[i][0].getToken() == tkn &&
                    board[i][1].getToken() == tkn &&
                    (board[i][2].getToken() == tkn)) {
                    return true;
            }
        //Checks for winner Vertically
        for (int j = 0; j < 3; j++)
            if (board[0][j].getToken() ==  tkn
                && board[1][j].getToken() == tkn
                && board[2][j].getToken() == tkn) {
              return true;
            }
        //Checks for winner Diagonially top left to bottom right
        if (board[0][0].getToken() == tkn 
              && board[1][1].getToken() == tkn        
              && board[2][2].getToken() == tkn) {
            return true;
          }
        //Checks for winner diagonally top right to bottom left
        if (board[0][2].getToken() == tkn
              && board[1][1].getToken() == tkn
              && board[2][0].getToken() == tkn) {
            return true;
          }
    }   return false;
    }

    //HERE IS INNER CLASS REPRESENTING ONE CELL IN BOARD
    //The inner class has access to all of the outer classes data/methods
    //As Cells are also Panes, they generate mouse activity
    public class Cell extends Pane {

        private char token = ' ';   // one of blank, X, or O

        public Cell() {
            setStyle("-fx-border-color: black"); 
            setPrefSize(100, 100);
            //whenever 'this' Cell is clicked we run the code in 'handleMouseClick' method
            setOnMouseClicked(e -> handleMouseClick());
        }

        public char getToken() {
            //returns the token where called on 
            return token;
        }

        //Draws 2 lines to represent the X player
        public void drawX() {
            double w = getWidth();
            double h = getHeight();
            Line line1 = new Line(1,2,w,h);
            Line line2 = new Line(100,4,1,h);
            getChildren().addAll(line1, line2);

        }

        public void drawO() {
            double w = getWidth();
            double h = getHeight();
            //6) I have given you complete CODE HERE TO CREATE AN 'O'
	    // you don't need to do anything else in this method
            Ellipse ellipse = new Ellipse(w/2, h/2, w/2, h/2);
            ellipse.setStroke(Color.BLACK);
            ellipse.setFill(Color.WHITE);
            getChildren().add(ellipse);
        }

        public void setToken(char c) {
            //setToken method draws X or O and updates token to the argument passed
            if (c == 'X')
                drawX();
            else
                drawO();
            token = c;
            }

        //This is the 'listener' class that is run whenever a Cell object is mouseClicked
        //Each time a Cell is mouseClicked we need to check if gaveOver
        private void handleMouseClick() {

            if (!gameOver) {     
                //Sets the clicked space to the token of the current player
                setToken(whoseTurn);   

                //First checks if anyone has won and if so declares a winner
                if(hasWon(whoseTurn)){
                    statusLabel.setText(whoseTurn + " Has won!");
                    whoseTurn = ' ';
                    gameOver = true;
                }
                //If no winner checks if the board is full
                else if (isFull()){
                    statusLabel.setText("Draw!");
                    whoseTurn = ' ';
                    gameOver = true;
                }
                //If no winner and board is not full changes turn to the next player
                else if (whoseTurn == 'X'){
                    whoseTurn = 'O';
                    String s = whoseTurn + "'s Turn to Play!";
                    statusLabel.setText(s);
                 }
                else{
                    whoseTurn = 'X';
                    String s = whoseTurn + "'s Turn to Play!";
                    statusLabel.setText(s);
                }
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}