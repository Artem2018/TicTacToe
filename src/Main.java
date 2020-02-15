
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.IOException;


public class Main extends Application {

    private Image logo;
    private ImageView imageView;
    private VBox middleBox;
    private ChoiceDialog<String> choiceDialog;
    private Scene mainMenu;
    private int cellnumb = 3;
    private Button start1player;

    @Override
    public void start(Stage stage) {
        middleBox = new VBox();

        try {
            logo = new Image(new FileInputStream("img/logo.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        start1player = new Button("SingleGame");

    }
}
