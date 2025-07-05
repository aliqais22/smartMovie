import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QRCodeScreen {

    private Ticket ticket;

    public QRCodeScreen(Ticket ticket) {
        this.ticket = ticket;
    }

    public void start(Stage stage) {
        Label titleLabel = new Label("Your Ticket QR Code");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        // ✅ حمل صورة QR الجديدة من المسار الصحيح
        ImageView qrImage = new ImageView(new Image(
                getClass().getResource("/imageBatman/Qrcode.jpg").toExternalForm()
        ));
        qrImage.setFitWidth(200);
        qrImage.setPreserveRatio(true);

        // ✅ عرض بيانات التذكرة باستخدام Getters
        Label ticketInfo = new Label(
                "Ticket ID: " + ticket.getTicketID() + "\n" +
                        "Seat: " + ticket.getSeatNumber() + "\n" +
                        "Price: $" + ticket.getPrice()
        );
        ticketInfo.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        Label instruction = new Label("Show this QR Code when entering the cinema.");
        instruction.setStyle("-fx-text-fill: white;");

        VBox root = new VBox(20, titleLabel, qrImage, ticketInfo, instruction);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 300, 400);
        stage.setScene(scene);
        stage.setTitle("Ticket QR Code");
        stage.show();
    }
}
