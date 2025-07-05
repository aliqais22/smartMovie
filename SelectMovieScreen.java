import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SelectMovieScreen extends Application {

    @Override
    public void start(Stage primaryStage) {

        // شعار التطبيق
        Label logoLabel = new Label("SMART\nMOVIE");
        logoLabel.setFont(Font.font("Verdana", 28));
        logoLabel.setTextFill(Color.RED);

        // شريط البحث
        TextField searchField = new TextField();
        searchField.setPromptText("Search Movies...");
        searchField.setMaxWidth(250);

        VBox topBox = new VBox(10, logoLabel, searchField);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));

        // NOW SHOWING
        Label nowShowingLabel = new Label("NOW SHOWING");
        nowShowingLabel.setTextFill(Color.RED);
        nowShowingLabel.setFont(Font.font("Arial", 20));

        // Movies مع imagePath
        Movie batmanMovie = new Movie(
                "Batman faces a sadistic killer known as the Riddler while uncovering deep corruption in Gotham City...",
                176, "Action", "English", 1, "The Batman", "/imageBatman/batman2.png");

        Movie avatarMovie = new Movie(
                "Jake Sully lives with his newfound family formed on the planet of Pandora...",
                150, "Fantasy", "English", 2, "Avatar", "/imageBatman/avatarPoster.jpg");

        Movie interMovie = new Movie(
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival...",
                180, "Sci-Fi", "English", 3, "Interstellar", "/imageBatman/interPoster.jpg");

        Movie titanicMovie = new Movie(
                "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious Titanic...",
                195, "Romance", "English", 4, "Titanic", "/imageBatman/taitanicPos.png");

        Movie lucyMovie = new Movie(
                "A woman, accidentally caught in a dark deal, turns the tables on her captors...",
                100, "Action", "English", 5, "Lucy", "/imageBatman/lucyPoster.jpeg");

        // ImageViews
        ImageView batmanView = new ImageView(new Image(getClass().getResource(batmanMovie.getImagePath()).toExternalForm()));
        batmanView.setFitWidth(140);
        batmanView.setPreserveRatio(true);

        ImageView avatarView = new ImageView(new Image(getClass().getResource(avatarMovie.getImagePath()).toExternalForm()));
        avatarView.setFitWidth(140);
        avatarView.setPreserveRatio(true);

        ImageView interView = new ImageView(new Image(getClass().getResource(interMovie.getImagePath()).toExternalForm()));
        interView.setFitWidth(140);
        interView.setPreserveRatio(true);

        ImageView titanicView = new ImageView(new Image(getClass().getResource(titanicMovie.getImagePath()).toExternalForm()));
        titanicView.setFitWidth(140);
        titanicView.setPreserveRatio(true);

        ImageView lucyView = new ImageView(new Image(getClass().getResource(lucyMovie.getImagePath()).toExternalForm()));
        lucyView.setFitWidth(145);
        lucyView.setPreserveRatio(true);

        // On Click: show details window
        batmanView.setOnMouseClicked(event -> showMovieDetails(batmanMovie));
        avatarView.setOnMouseClicked(event -> showMovieDetails(avatarMovie));
        interView.setOnMouseClicked(event -> showMovieDetails(interMovie));
        titanicView.setOnMouseClicked(event -> showMovieDetails(titanicMovie));
        lucyView.setOnMouseClicked(event -> showMovieDetails(lucyMovie));

        // Movies Box
        HBox moviesBox = new HBox(20, batmanView, avatarView, interView, titanicView, lucyView);
        moviesBox.setAlignment(Pos.CENTER_LEFT);
        moviesBox.setStyle("-fx-background-color: transparent;");

        ScrollPane scrollPane = new ScrollPane(moviesBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setStyle("-fx-background: transparent; -fx-control-inner-background: transparent;");
        scrollPane.getContent().setStyle("-fx-background-color: transparent;");
        scrollPane.setPadding(Insets.EMPTY);

        VBox centerBox = new VBox(20, nowShowingLabel, scrollPane);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(20));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(centerBox);
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 400, 800);

        primaryStage.setTitle("SmartMovie App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // نافذة التفاصيل
    public void showMovieDetails(Movie movie) {
        Stage detailsStage = new Stage();

        ImageView moviePoster = new ImageView(new Image(
                getClass().getResource(movie.getImagePath()).toExternalForm()));
        moviePoster.setFitWidth(200);
        moviePoster.setPreserveRatio(true);

        Label titleLabel = new Label(movie.getTitle());
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Label genreLabel = new Label("Genre: " + movie.getGenre());
        genreLabel.setStyle("-fx-text-fill: white;");

        Label durationLabel = new Label("Duration: " + (movie.getDuration() / 60) + "h " + (movie.getDuration() % 60) + "m");
        durationLabel.setStyle("-fx-text-fill: white;");

        Label descriptionLabel = new Label("Description:\n" + movie.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-text-fill: white;");

        Button buyTicketButton = new Button("Buy Ticket");
        buyTicketButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        buyTicketButton.setOnAction(e -> {
            // ✅ هنا يفتح شاشة المقاعد مع الفيلم المختار
            SeatSelectionScreen seatScreen = new SeatSelectionScreen(movie);
            seatScreen.start(new Stage());

            detailsStage.close();
        });

        VBox layout = new VBox(15, moviePoster, titleLabel, genreLabel, durationLabel, descriptionLabel, buyTicketButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black;");
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 350, 600);
        detailsStage.setScene(scene);
        detailsStage.setTitle(movie.getTitle());
        detailsStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
