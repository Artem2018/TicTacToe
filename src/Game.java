import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

class Game {

    private Stage stage;
    private Scene game;
    private GridPane gridPane = new GridPane();
    private int numOfCells;
    private Scene mainMenu;
    private Cell[][] cell;
    private int width;
    private int height;
    private boolean singlePlay;
    private char currentPlayer;
    private boolean onlineMode;
    private String ip;
    private int port;


    Game(Stage primaryStage, Scene mainMenu, String side, int numOfCells,boolean singleplay,boolean onlineMode,String ip,int port){
        this.stage = primaryStage;
        this.mainMenu = mainMenu;
        this.singlePlay = singleplay;
        this.numOfCells = numOfCells;
        this.onlineMode = onlineMode;
        this.ip = ip;
        this.port = port;

        System.out.println(side + " going first");
        if (side.equals("X")){
            currentPlayer = 'X';
        } else currentPlayer = 'O';

        if (numOfCells ==3){
            width = 450;
            height = 450;

        } else if (numOfCells == 4){
            width = 600;
            height = 600;
        }
        Grid();
    }

    private void Grid(){
        cell = new Cell[numOfCells][numOfCells];
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < numOfCells; i++){
            for (int j = 0; j < numOfCells; j++){
                cell[i][j] = new Cell();
                gridPane.add(cell[i][j],i,j);
            }
        }
        game = new Scene(gridPane,width,height);
        stage.setScene(game);

        if (onlineMode){
            System.out.println(ip + " " + port);
            Client client = new Client(ip,port);
            client.start();
        }


    }

    private boolean isBoardFull(){
        for (int i =0; i < numOfCells; i++){
            for (int j=0; j < numOfCells; j++){
                if (cell[i][j].getPlayer() == ' '){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean hasWon(char player) {
        if (numOfCells == 3) {
            for (int i = 0; i < numOfCells; i++) {
                if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player) {
                    return true;
                }

            }

            for (int i = 0; i < numOfCells; i++) {
                if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer() == player) {
                    return true;
                }
            }

            if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player) {
                return true;
            }

            if (cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player) {
                return true;
            }

        } else if (numOfCells == 4){
            for (int i = 0; i < numOfCells; i++) {
                if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player
                        && cell[i][3].getPlayer() == player) {
                    return true;
                }
            }

            for (int i = 0; i < numOfCells; i++) {
                if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer() == player
                        && cell[3][i].getPlayer() == player) {
                    return true;
                }
            }

            if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player
                    && cell[3][3].getPlayer() == player ) {
                return true;
            }

            if (cell[0][3].getPlayer() == player && cell[1][2].getPlayer() == player && cell[2][1].getPlayer() == player
                    && cell[3][0].getPlayer() == player) {
                return true;
            }
        }
        return false;
    }


    public class Cell extends Pane {
        private char player = ' ';

        Cell(){
            Rectangle r = new Rectangle(150, 150);
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            getChildren().addAll(r);
            this.setOnMouseClicked(e -> put());
        }
        char getPlayer(){
            return player;
        }

        void setPlayer(char c){
            player = c;

            if (player == 'X'){
                Line line1 = new Line(10,10,this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));

                Line line2 = new Line(10,this.getHeight() - 10,this.getWidth() - 10,  10);
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                line2.startYProperty().bind(this.heightProperty().subtract(10));

                getChildren().addAll(line1,line2);
            } else if (player == 'O'){
                Ellipse ellipse = new Ellipse(this.getWidth()/2, this.getHeight() / 2, this.getWidth() / 2 - 10,
                        this.getHeight() / 2 - 10);

                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setStroke(Color.BLUE);
                getChildren().add(ellipse);
            }
        }

         void put(){
            if (player == ' ' && currentPlayer != ' '){
                setPlayer(currentPlayer);


                if (hasWon(currentPlayer)){
                    Alert won  = new Alert(Alert.AlertType.INFORMATION);
                    won.setTitle("Winning");
                    won.setHeaderText(currentPlayer + " WON ");
                    won.showAndWait();
                    stage.setScene(mainMenu);
                    currentPlayer = ' ';

                } else if (isBoardFull()){
                    Alert draw = new Alert(Alert.AlertType.INFORMATION);
                    draw.setTitle("Draw");
                    draw.setHeaderText("Nobody has won");
                    draw.showAndWait();
                    stage.setScene(mainMenu);
                    currentPlayer = ' ';
                } else  {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    System.out.println(currentPlayer + " must play ");
                }
            }
        }
    }
    void InitializeServer(){

    }
}
