import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class PaymentScreen {

    private double totalAmount;
    private Seat seat; // ✅ حقل جديد

    public PaymentScreen(double totalAmount, Seat seat) {
        this.totalAmount = totalAmount;
        this.seat = seat;
    }

    public void start(Stage stage) {
        Label titleLabel = new Label("PAYMENT");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        Label totalLabel = new Label(String.format("TOTAL AMOUNT: %.2f $", totalAmount));
        totalLabel.setStyle("-fx-text-fill: white;");

        Button payButton = new Button("Pay Now");
        payButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        payButton.setOnAction(e -> {
            int paymentID = new Random().nextInt(1000) + 1;
            Payment payment = new Payment((float) totalAmount, paymentID, "Credit Card", "Pending");

            boolean success = payment.processPayment();
            if (success) {
                System.out.println("✅ Payment successful! Status: " + payment.getPaymentStatus());

                TicketManager ticketManager = new TicketManager();
                String seatNumber = seat.getRow() + seat.getNumber();
                Ticket ticket = ticketManager.issueTicket((float) totalAmount, seatNumber);

                ticket.printTicket();

                QRCodeScreen qrCodeScreen = new QRCodeScreen(ticket);
                qrCodeScreen.start(new Stage());
            } else {
                System.out.println("❌ Payment failed!");
            }

            stage.close();
        });

        VBox root = new VBox(20, titleLabel, totalLabel, payButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Payment");
        stage.show();
    }
}
