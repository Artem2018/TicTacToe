
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main extends Application {

    private Image logo;
    private VBox middleBox;
    private ChoiceDialog<String> choiceDialog;
    private ChoiceDialog<String> cellnum;
    private Scene mainMenu;
    private int cellnumb = 3;
    private Socket socket;
    private InetAddress inetAddress;

    @Override
    public void start(Stage primaryStage) {
        middleBox = new VBox();

        //logo
        try {
            logo = new Image(new FileInputStream("/home/artem/Desktop/TicTacToe_/src/img/logo.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(logo);

        //choose side
        List<String> choices = new ArrayList<>();
        choices.add("X");
        choices.add("O");
        choiceDialog = new ChoiceDialog<>("X",choices);
        choiceDialog.setTitle("Choose side");
        choiceDialog.setHeaderText("Which side do you want to choose?");
        choiceDialog.setContentText("Choose your side:");
        choiceDialog.setResizable(true);

        // choose cell number
        List<String> cells = new ArrayList<>();
        cells.add("3x3");
        cells.add("4x4");
        cellnum = new ChoiceDialog<>("3x3", cells);
        cellnum.setTitle("Choose size");
        cellnum.setHeaderText("Choose the size of your board");
        cellnum.setContentText("Size:");
        cellnum.setResizable(true);


        TextInputDialog tdp = new TextInputDialog();
        tdp.setHeaderText("Port");
        tdp.setContentText("Enter the port number");
        tdp.setResizable(true);
        


        // 1 button
        Button start1player = new Button("Single Game");
        start1player.setOnAction(actionEvent -> {
            Optional<String> res = choiceDialog.showAndWait();
            res.ifPresent(s -> new Game(primaryStage, mainMenu, s, cellnumb, true,false));
        });
        start1player.setScaleX(1.5);
        start1player.setScaleY(1.5);
        start1player.setMinWidth(115);
        start1player.setDisable(true);                  // coming soon with AI implementation

        // 2 button
        Button twoPlayers = new Button("2 players");
        twoPlayers.setOnAction(actionEvent -> {
            Optional<String> res = choiceDialog.showAndWait();
            res.ifPresent(s -> new Game(primaryStage, mainMenu, s, cellnumb, false,false));
        });
        twoPlayers.setScaleX(1.5);
        twoPlayers.setScaleY(1.5);
        twoPlayers.setMinWidth(115);

        // 3 button
        Button onlineMode = new Button("Play online");
        onlineMode.setOnAction(actionEvent -> {
            Optional<String> str = tdp.showAndWait();
            Optional<String> res = choiceDialog.showAndWait();
            if (res.isPresent() && str.isPresent()) {
                int port = Integer.parseInt(str.get());
                try {
                    inetAddress = InetAddress.getLocalHost();
                    socket = new Socket(inetAddress, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new Game(primaryStage, mainMenu, res.get(), cellnumb, false, true, port, socket);
            }
        });
        onlineMode.setScaleX(1.5);
        onlineMode.setScaleY(1.5);
        onlineMode.setMinWidth(115);

        // 4 button
        Button settings = new Button("Settings");
        settings.setOnAction(actionEvent -> {
            Optional<String> res = cellnum.showAndWait();
            if (res.isPresent()){
                if (res.get().equals("3x3")){
                    cellnumb = 3;
                } else if (res.get().equals("4x4")){
                    cellnumb = 4;
                }
                System.out.println(cellnumb);
            }
        });
        settings.setScaleX(1.5);
        settings.setScaleY(1.5);
        settings.setMinWidth(115);

        // 5 button
        Button exit = new Button("Exit");
        exit.setOnAction(actionEvent -> System.exit(0));
        exit.setScaleX(1.5);
        exit.setScaleY(1.5);
        exit.setMinWidth(115);

        // adding all elements to the mainMenu layout
        middleBox.setAlignment(Pos.CENTER);
        middleBox.setSpacing(25);
        middleBox.getChildren().addAll(imageView,start1player,twoPlayers,onlineMode,settings,exit);

        // launch the MainMenu Scene
        primaryStage.setTitle("Tic-Tac-Toe XOXOXO");
        mainMenu = new Scene(createContent());
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    private Parent createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(550, 480);
        root.setCenter(middleBox);
        return root;
    }
}
