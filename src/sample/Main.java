package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Button btnRun = new Button();
        btnRun.setText("Click here to generate the fractal");
        btnRun.setPrefSize(300, 50);
        btnRun.setLayoutX(75);
        btnRun.setLayoutY(30);

        TextArea expInput = new TextArea();
        expInput.setPrefSize(440, 100);
        expInput.setLayoutX(5);
        expInput.setLayoutY(90);
        expInput.setText("Isabelle Okuma\tRA: 082180004\nLaura Bento\tRA: 082180029\nMaria Akamine\tRA: 082180010\nIf you want to generate another fractal, just change the rules in 'input.txt'!");
        expInput.setEditable(false);

        Pane root = new Pane();
        root.getChildren().add(btnRun);
        root.getChildren().add(expInput);

        Scene scene = new Scene(root, 450, 200);

        primaryStage.setTitle("Fractal");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnRun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Image.start();
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
