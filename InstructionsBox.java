import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Jordle instruction button brings up a new stage that is not a warning/ alert. Idk I thought it was cool.
 *
 * @version 1.0
 * @author Joseph Ferraro
 */
public class InstructionsBox {

    /**
     * Displays jordle instructions.
     *
     */
    public static void display() {
        Stage window = new Stage();
        window.setWidth(600);
        window.setHeight(150);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("How to play Jordle");

        Label instructions = new Label("1) Guess a 5 letter word.\n2) If a letter in the guessed word is in the "
                + "Jordle and in the correct spot, then the box will light up green.\n3) If a letter in your guessed "
                + "word belongs in the jordle but is in the incorrect spot, then it will light up yellow.\n4) You can"
                + " guess 6 words total");
        instructions.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(instructions, closeButton);
        closeButton.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
