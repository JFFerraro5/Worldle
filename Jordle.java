import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Jordle game exactly like wordle game but better.
 *
 * @version 1.0
 * @author Joseph Ferraro
 */
public class Jordle extends Application {

    public VBox root = new VBox();
    public Label jordleCoach = new Label();
    public int i = 0;
    public int j = 0;
    public String jordler;
    public boolean won = false;
    public int isGreen = 0;
    public int loser = 0;

    /**
     * Startup jordle.
     *
     * @param args main arguments of jordle
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Checks guess against actual jord.
     *
     * @param jord guessed jord
     * @param randJord random word to check against
     */
    public void checkJord(ArrayList<String> jord, String randJord) {
        char[] alpha = checkAlpha(randJord);
        System.out.println(randJord);
        boolean colored = false;
        boolean green = false;
        isGreen = 0;
        ArrayList<String> rannd = jord;


        for (int b = 0; b < 5; b++) {
            ((Label) ((HBox) root.getChildren().get(i - 1)).getChildren().get(b)).setStyle("-fx-background-"
                    + "color: Grey;-fx-min-height:100;-fx-min-width:100;-fx-text-alignment:right;");
        }

        for (int k = 0; k < 5; k++) {
            colored = false;
            String checker = jord.get(k).toUpperCase();
            String rand = String.valueOf(randJord.charAt(k)).toUpperCase();
            if (checker.equals(rand) && alpha[checker.charAt(0) - 'A'] > 0) {
                ((Label) ((HBox) root.getChildren().get(i - 1)).getChildren().get(k)).setStyle("-fx-background-"
                        + "color: GREEN;-fx-min-height:100;-fx-min-width:100;-fx-text-alignment:right;");
                alpha[rand.charAt(0) - 'A']--;
                isGreen += 1;
            }
        }

        for (int m = 0; m < 5; m++) {
            String checker = jord.get(m).toUpperCase();
            String rand = String.valueOf(randJord.charAt(m)).toUpperCase();
            if (alpha[checker.charAt(0) - 'A'] > 0) {
                ((Label) ((HBox) root.getChildren().get(i - 1)).getChildren().get(m)).setStyle("-fx-background-"
                        + "color: YELLOW;-fx-min-height:100;-fx-min-width:100;-fx-text-alignment:right;");
                alpha[checker.charAt(0) - 'A']--;
            }
        }

    }

    /**
     * Helper method for check jord.
     *
     * @param rand random jord
     * @return returns char array
     */
    public char[] checkAlpha(String rand) {
        char[] alphabet = new char[26];
        for (int t = 0; t < 5; t++) {
            System.out.println(alphabet[rand.charAt(t) - 'A']++);
        }
        return alphabet;
    }


    /**
     * Resets the game.
     *
     */
    private void resetBoard() {
        jordleCoach.setText("Try guessing a word.");
        jordler = randomJordle(Words.list);
        won = false;
        isGreen = 0;
        loser = 0;
        i = 0;
        j = 0;
        for (int p = 0; p < 6; p++) {
            for (int m = 0; m < 5; m++) {
                ((Label) ((HBox) root.getChildren().get(p)).getChildren().get(m)).setStyle(
                        "-fx-border-color:Darkgray;-fx-min-height:100;-fx-min-width:100;"
                                + "-fx-text-alignment:right;-fx-text-fill:black;-fx-font-wight:bold;"
                );
                ((Label) ((HBox) root.getChildren().get(p)).getChildren().get(m)).setText("");
            }
        }
    }

    /**
     * gets a random jord
     *
     * @param randJordle random jord list
     * @return random jord
     */
    private String randomJordle(ArrayList<String> randJordle) {
        int num = (int) ((Math.random() * 300) / 10);
        if (num == 29) {
            num = 28;
        }
        System.out.println(num);
        String jord = randJordle.get(num).toUpperCase();
        return jord;
    }


    /**
     * Makes game grid.
     *
     */
    private void gridMaker() {

        root = new VBox(10, new HBox(10), new HBox(10), new HBox(10), new HBox(10),
                        new HBox(10), new HBox(10));

        for (int q = 0; q < 6; q++) {
            ((HBox) root.getChildren().get(q)).setAlignment((Pos.TOP_CENTER));
            for (int jqr = 0; jqr < 5; jqr++) {
                Label label = new Label();
                ((HBox) root.getChildren().get(q)).getChildren().add(jqr, label);
                label.setStyle(
                        "-fx-border-color:Darkgray;-fx-min-height:100;-fx-min-width:100;"
                        + "-fx-text-alignment:right;-fx-text-fill:black;-fx-font-wight:bold;"
                );
                label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70.0));
                label.setAlignment(Pos.CENTER);
            }
        }
        VBox.setMargin(root.getChildren().get(0), new Insets(50, 0, 0, 0));

    }


    /**
     * Start method.
     *
     * @param mainStage jordle stage.
     * @throws Exception exception being thrown.
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        jordleCoach.setText("Try guessing a word.");
        jordler = randomJordle(Words.list);
        mainStage.setTitle("Jordle");
        mainStage.setWidth(700);
        mainStage.setHeight(800);

        mainStage.setResizable(false);


        HBox buttons = new HBox();
        buttons.setSpacing(10);

        Button instructions = new Button("Instructions");
        instructions.setOnAction(e -> InstructionsBox.display());
        instructions.setFocusTraversable(false);

        Button reset = new Button("Restart");
        reset.setFocusTraversable(false);
        reset.setOnAction(e -> resetBoard());

        buttons.getChildren().addAll(jordleCoach, instructions, reset);

//        Set forwardKeys = getFocusTraversalKeys(
//                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
//        Set newForwardKeys = new HashSet(forwardKeys);
//        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
//        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
//                newForwardKeys);

        buttons.setAlignment(Pos.BOTTOM_CENTER);

        gridMaker();
        root.getChildren().add(buttons);


        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();



        ArrayList<String> jord = new ArrayList<>();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent kE) {
                if (kE.getCode().equals(KeyCode.ENTER)) {
                    if (j >= 5) {
                        j = 0;
                        i++;
                        checkJord(jord, jordler);
                        jord.clear();
                        loser++;
                        if (isGreen == 5) {
                            jordleCoach.setText("You Win!!");
                            won = true;
                        }
                        if (loser == 6) {
                            jordleCoach.setText("LOSER! lmao, jord was " + jordler);
                        }
                        if (won) {
                            Alert b = new Alert(Alert.AlertType.CONFIRMATION);
                            b.setContentText("Winner");
                            b.showAndWait();
                            resetBoard();
                        }
                    } else if (j <= 4) {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Please enter a 5-letter word");
                        a.showAndWait();
                    }
                }
                if (i < 6) {
                    if (j < 5) {
                        if (kE.getCode().equals(KeyCode.BACK_SPACE)) {
                            if (j > 0) {
                                j--;
                                ((Label) ((HBox) root.getChildren().get(i)).getChildren().get(j)).setText("");
                                jord.remove(j);
                            } else if (j == 0) {
                                ((Label) ((HBox) root.getChildren().get(i)).getChildren().get(j)).setText("");
                                jord.remove(0);
                                j = 0;
                            }
                        }
                        if (kE.getCode().isLetterKey()) {
                            ((Label) ((HBox) root.getChildren().get(i)).getChildren().get(j)).setText(kE.getText());
                            jord.add(kE.getText());
                            j++;
                        }
                    }
                    if (j == 5) {
                        if (kE.getCode().equals(KeyCode.BACK_SPACE)) {
                            ((Label) ((HBox) root.getChildren().get(i)).getChildren().get(4)).setText("");
                            j--;
                            jord.remove(4);
                        }
                    }
                }
            }
        });
    }
}
