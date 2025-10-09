import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private ScoreManager scoreManager;
    private Label currentScoreLabel;
    private Label currentStreakLabel;
    private Label longestStreakLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        scoreManager = new ScoreManager();

        // Main layout - BorderPane for organized sections
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2C3E50;");

        // LEFT SIDE - Control Panel with scores and start button
        VBox leftPanel = createLeftPanel();
        root.setLeft(leftPanel);

        // CENTER - Game grid area
        GridPane gameGrid = createGameGrid();
        root.setCenter(gameGrid);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Tiles Game!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(20);
        leftPanel.setPadding(new Insets(20));
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.setPrefWidth(200);
        leftPanel.setStyle("-fx-background-color: #34495E; -fx-border-color: #3498DB; -fx-border-width: 2;");

        // Title
        Text title = new Text("TILES GAME");
        title.setFont(Font.font("Arial", 18));
        title.setStyle("-fx-fill: #3498DB;");

        // Start Button
        Button startBtn = new Button("START");
        startBtn.setPrefSize(120, 40);
        startBtn.setFont(Font.font("Arial", 14));
        startBtn.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white;");
        startBtn.setOnAction(event -> {
            System.out.println("Game has started!");
            // Add your game start logic here
        });

        // Score Board Section
        Text scoreTitle = new Text("SCORE BOARD");
        scoreTitle.setFont(Font.font("Arial", 14));
        scoreTitle.setStyle("-fx-fill: white;");

        // Current Score
        VBox scoreBox = new VBox(5);
        scoreBox.setAlignment(Pos.CENTER);
        Text scoreLabel = new Text("Score");
        scoreLabel.setFont(Font.font("Arial", 12));
        scoreLabel.setStyle("-fx-fill: #BDC3C7;");
        currentScoreLabel = new Label("0");
        currentScoreLabel.setFont(Font.font("Arial", 20));
        currentScoreLabel.setStyle("-fx-text-fill: #E74C3C;");
        scoreBox.getChildren().addAll(scoreLabel, currentScoreLabel);

        // Current Streak
        VBox streakBox = new VBox(5);
        streakBox.setAlignment(Pos.CENTER);
        Text streakLabel = new Text("Current Streak");
        streakLabel.setFont(Font.font("Arial", 12));
        streakLabel.setStyle("-fx-fill: #BDC3C7;");
        currentStreakLabel = new Label("0");
        currentStreakLabel.setFont(Font.font("Arial", 20));
        currentStreakLabel.setStyle("-fx-text-fill: #F39C12;");
        streakBox.getChildren().addAll(streakLabel, currentStreakLabel);

        // Longest Streak
        VBox longestBox = new VBox(5);
        longestBox.setAlignment(Pos.CENTER);
        Text longestLabel = new Text("Longest Streak");
        longestLabel.setFont(Font.font("Arial", 12));
        longestLabel.setStyle("-fx-fill: #BDC3C7;");
        longestStreakLabel = new Label("0");
        longestStreakLabel.setFont(Font.font("Arial", 20));
        longestStreakLabel.setStyle("-fx-text-fill: #27AE60;");
        longestBox.getChildren().addAll(longestLabel, longestStreakLabel);

        // Test Buttons (remove these later when you have real gameplay)
        Button testBtn = new Button("TEST +10");
        testBtn.setPrefSize(120, 30);
        testBtn.setStyle("-fx-background-color: #8E44AD; -fx-text-fill: white;");
        testBtn.setOnAction(e -> testScore());

        Button resetBtn = new Button("RESET");
        resetBtn.setPrefSize(120, 30);
        resetBtn.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
        resetBtn.setOnAction(e -> resetScore());

        leftPanel.getChildren().addAll(
                title,
                startBtn,
                scoreTitle,
                scoreBox,
                streakBox,
                longestBox,
                testBtn,
                resetBtn
        );

        return leftPanel;
    }

    private GridPane createGameGrid() {
        GridPane gameGrid = new GridPane();
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setHgap(2);
        gameGrid.setVgap(2);
        gameGrid.setPadding(new Insets(20));
        gameGrid.setStyle("-fx-background-color: #34495E;");

        // Create placeholder for game tiles (8x8 grid)
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button tileButton = new Button();
                tileButton.setPrefSize(50, 50);
                tileButton.setStyle("-fx-background-color: #95A5A6; -fx-border-color: #2C3E50; -fx-border-width: 1;");

                // Add click handler for tiles
                final int r = row, c = col;
                tileButton.setOnAction(e -> {
                    System.out.println("Clicked tile at " + r + ", " + c);
                    // Add your tile click logic here
                });

                gameGrid.add(tileButton, col, row);
            }
        }

        return gameGrid;
    }

    private void testScore() {
        scoreManager.addScore(10);
        scoreManager.incrementStreak();
        updateScoreDisplay();
    }

    private void resetScore() {
        scoreManager.reset();
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        currentScoreLabel.setText(String.valueOf(scoreManager.getCurrentScore()));
        currentStreakLabel.setText(String.valueOf(scoreManager.getCurrentStreak()));
        longestStreakLabel.setText(String.valueOf(scoreManager.getLongestStreak()));
    }
}