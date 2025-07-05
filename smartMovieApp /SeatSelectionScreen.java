import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

public class SeatSelectionScreen extends Application {

    private Movie movie;                // الفيلم المختار
    private List<Seat> seats = new ArrayList<>(); // كل المقاعد
    private Seat selectedSeat = null;   // المقعد الذي اختاره المستخدم

    public SeatSelectionScreen(Movie movie) {
        this.movie = movie;
    }

    public SeatSelectionScreen() {
        // Constructor افتراضي إذا شغّلت مباشرة
    }

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("SELECT YOUR SEAT");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        // Legend
        Label bookedLabel = new Label("BOOKED");
        bookedLabel.setTextFill(Color.RED);
        Label availableLabel = new Label("AVAILABLE");
        availableLabel.setTextFill(Color.LIMEGREEN);
        Label selectedLabel = new Label("SELECTED");
        selectedLabel.setTextFill(Color.BLUE);

        HBox legend = new HBox(20, bookedLabel, availableLabel, selectedLabel);
        legend.setAlignment(Pos.CENTER);

        GridPane seatsGrid = new GridPane();
        seatsGrid.setHgap(10);
        seatsGrid.setVgap(10);
        seatsGrid.setAlignment(Pos.CENTER);

        int rows = 5;
        int cols = 6;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Seat seat = new Seat(row * cols + col + 1, "A", row * cols + col + 1);

                if (Math.random() < 0.3) {
                    seat.reserve(); // احجزه مسبقًا
                }

                Rectangle seatShape = new Rectangle(30, 30);
                seatShape.setArcWidth(8);
                seatShape.setArcHeight(8);
                seatShape.setStroke(Color.BLACK);

                seatShape.setUserData(seat);
                seats.add(seat);

                updateSeatColor(seatShape, seat);

                seatShape.setOnMouseClicked(e -> {
                    if (!seat.isAvailable() && seat != selectedSeat) {
                        System.out.println("Seat already booked!");
                        return;
                    }

                    selectedSeat = seat;

                    // إعادة تلوين كل المقاعد
                    for (Node node : seatsGrid.getChildren()) {
                        Rectangle rect = (Rectangle) node;
                        Seat s = (Seat) rect.getUserData();
                        updateSeatColor(rect, s);
                    }
                });

                seatsGrid.add(seatShape, col, row);
            }
        }

        Label projector = new Label("📽️");
        projector.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        backButton.setOnAction(e -> stage.close());

        Button confirmButton = new Button("Confirm Seat");
        confirmButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        confirmButton.setOnAction(e -> {
            if (selectedSeat != null && selectedSeat.isAvailable()) {
                selectedSeat.reserve();
                System.out.println("Seat confirmed: " + selectedSeat.getSeatID());

                // ✅ بعد تأكيد المقعد افتح شاشة السناكات
                OrderSnacksScreen orderScreen = new OrderSnacksScreen(movie, selectedSeat);
                orderScreen.start(new Stage());

                stage.close();
            } else {
                System.out.println("Please select a seat first!");
            }
        });

        HBox buttons = new HBox(20, backButton, confirmButton);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, titleLabel, legend, seatsGrid, projector, buttons);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Seat Selection");
        stage.show();
    }

    private void updateSeatColor(Rectangle rect, Seat seat) {
        if (seat == selectedSeat) {
            rect.setFill(Color.BLUE); // المختار
        } else if (!seat.isAvailable()) {
            rect.setFill(Color.RED); // محجوز
        } else {
            rect.setFill(Color.LIMEGREEN); // متاح
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
