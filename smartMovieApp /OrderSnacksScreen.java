import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class OrderSnacksScreen {

    private Movie movie;
    private Seat seat;
    private List<Snack> snacks = new ArrayList<>();

    private Label snacksTotalLabel = new Label("SNACKS: 0.0 $");
    private Label moviePriceLabel;
    private Label totalLabel = new Label("TOTAL: 0.0 $");

    private final double moviePrice = 14.99;

    public OrderSnacksScreen(Movie movie, Seat seat) {
        this.movie = movie;
        this.seat = seat;
    }

    public OrderSnacksScreen() {
        // فارغ إذا شُغل مباشرة
    }

    public void start(Stage stage) {
        System.out.println("Opening OrderSnacksScreen...");

        Label titleLabel = new Label("ORDER SNACKS");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        // ✅ المسارات مُحدثة لتطابق imageBatman
        snacks.add(new Snack("Cola", 1.99, "/imageBatman/cola.png"));
        snacks.add(new Snack("Water", 1.99, "/imageBatman/water.png"));
        snacks.add(new Snack("Beer", 2.99, "/imageBatman/beer.png"));
        snacks.add(new Snack("Orange", 1.99, "/imageBatman/orange-png.png"));
        snacks.add(new Snack("Popcorn", 3.99, "/imageBatman/popcorn.png"));
        snacks.add(new Snack("Fries", 4.99, "/imageBatman/french-fries.png"));
        snacks.add(new Snack("Churros", 2.99, "/imageBatman/churros.png"));

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        int col = 0, row = 0;
        for (Snack snack : snacks) {
            VBox snackBox = createSnackBox(snack);
            grid.add(snackBox, col, row);
            col++;
            if (col >= 3) {
                col = 0;
                row++;
            }
        }

        snacksTotalLabel.setStyle("-fx-text-fill: white;");
        moviePriceLabel = new Label("MOVIE: " + moviePrice + " $");
        moviePriceLabel.setStyle("-fx-text-fill: white;");
        totalLabel.setStyle("-fx-text-fill: white;");

        Button continueButton = new Button("CONTINUE TO PAYMENT");
        continueButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        continueButton.setOnAction(e -> {
            System.out.println("Proceed to payment...");
            double snacksTotal = snacks.stream()
                    .mapToDouble(s -> s.getPrice() * s.getQuantity())
                    .sum();
            double grandTotal = snacksTotal + moviePrice;

            System.out.println("Grand total: " + grandTotal);

            PaymentScreen paymentScreen = new PaymentScreen(grandTotal, seat);
            paymentScreen.start(new Stage());
            stage.close();
        });

        VBox totalsBox = new VBox(5, snacksTotalLabel, moviePriceLabel, totalLabel, continueButton);
        totalsBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, titleLabel, grid, totalsBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 400, 700);
        stage.setScene(scene);
        stage.setTitle("Order Snacks");
        stage.show();

        updateTotals();
    }

    private VBox createSnackBox(Snack snack) {
        System.out.println("Trying to load: " + snack.getImagePath());
        if (getClass().getResource(snack.getImagePath()) == null) {
            System.out.println("⚠️ IMAGE NOT FOUND: " + snack.getImagePath());
        }

        ImageView imageView;
        try {
            imageView = new ImageView(new Image(getClass().getResource(snack.getImagePath()).toExternalForm()));
        } catch (Exception e) {
            System.out.println("⚠️ Failed to load image, using placeholder!");
            imageView = new ImageView();
        }

        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

        Label nameLabel = new Label(snack.getName() + " - " + snack.getPrice() + " $");
        nameLabel.setStyle("-fx-text-fill: white;");

        Label quantityLabel = new Label("0");
        quantityLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        Button plusButton = new Button("+");
        Button minusButton = new Button("-");

        plusButton.setOnAction(e -> {
            snack.incrementQuantity();
            quantityLabel.setText(String.valueOf(snack.getQuantity()));
            updateTotals();
        });

        minusButton.setOnAction(e -> {
            snack.decrementQuantity();
            quantityLabel.setText(String.valueOf(snack.getQuantity()));
            updateTotals();
        });

        HBox controls = new HBox(5, minusButton, quantityLabel, plusButton);
        controls.setAlignment(Pos.CENTER);

        VBox box = new VBox(5, imageView, nameLabel, controls);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private void updateTotals() {
        double snacksTotal = snacks.stream()
                .mapToDouble(s -> s.getPrice() * s.getQuantity())
                .sum();

        snacksTotalLabel.setText(String.format("SNACKS: %.2f $", snacksTotal));
        moviePriceLabel.setText(String.format("MOVIE: %.2f $", moviePrice));
        totalLabel.setText(String.format("TOTAL: %.2f $", snacksTotal + moviePrice));
    }
}
