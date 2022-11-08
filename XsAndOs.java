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
        //1) CODE HERE: use nested loop to create a Cell object for each location [row][col]in board 
        //Also use pane.add(object, column, row) to add that Cell object to the GridPane 
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                Cell box = new Cell();
                pane.add(box,i,j);
  
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

   /*  public boolean isFull() {
        //2) CODE HERE to check if board is full  (don't forget to uncomment) 
            }
    } 
    }

    
    /*public boolean hasWon(char tkn) {
        //3) CODE HERE to check board to see if there is a winner (don't forget to uncomment
    }*/

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
            //4) CODE HERE note we are returning a char value 
            return token;
        }

        public void drawX() {
            double w = getWidth();
            double h = getHeight();
            //5) CODE HERE TO CREATE TWO LINES FOR 'X' and then uncomment line below
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
            //setToken method draws X or O
            //and then updates token to the argument passed
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
                //8) CODE HERE FOR LOGIC OF THE GAME
                //DON'T FORGET TO UPDATE whoseTurn
                //Also UPDATE s TO LET USER KNOW WHAT IS HAPPENING   
                setToken(whoseTurn);   
                if (whoseTurn == 'X'){
                    whoseTurn = 'O';
                 }
                else{
                    whoseTurn = 'X';
                }
                String s = whoseTurn + "'s Turn to Play!";
                statusLabel.setText(s);

                
            }
            
        }
    }
  
    public static void main(String[] args) {
        launch(args);
    }
}