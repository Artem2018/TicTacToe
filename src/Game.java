import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game {

    private Stage stage;
    private Scene game;
    private GridPane gridPane = new GridPane();
    private int numOfCells;
    private Scene mainMenu;
    private Cell[][] cells;
    private int width;
    private int height;
    private boolean singlePlay;
    private char currentPlayer;
    private boolean onlineMode;

    Game(Stage primaryStage, Scene mainMenu, String side, int numOfCells,boolean singleplay,boolean onlineMode){
        this.stage = primaryStage;
        this.mainMenu = mainMenu;
        this.singlePlay = singleplay;
        this.numOfCells = numOfCells;
        this.onlineMode = onlineMode;
        System.out.println(side + " going first");
        if (side.equals("X")){
            currentPlayer = 'X';
        } else currentPlayer = 'O';
    }

}
