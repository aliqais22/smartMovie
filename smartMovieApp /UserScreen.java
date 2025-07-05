import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserScreen extends Application {

    private User user;

    public UserScreen() {
        // يمكنك ترك username فارغ لأنك ستسجل دخوله بالبيانات لاحقًا
        this.user = new User("DemoUser", "demo@email.com", "1234");
    }

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("SMARTMOVIE - USER LOGIN");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: white;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            // يمكنك تعيينها للمستخدم هنا إن أردت
            user.setEmail(email);
            user.setPassword(password);

            boolean success = user.login(email, password);
            if (success) {
                statusLabel.setText("✅ Login successful!");

                // ✅ انتقل إلى SelectMovieScreen بعد تسجيل الدخول
                SelectMovieScreen movieScreen = new SelectMovieScreen();
                movieScreen.start(new Stage());
                stage.close();
            } else {
                statusLabel.setText("❌ Login failed!");
            }
        });

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        registerButton.setOnAction(e -> {
            boolean registered = user.register();
            statusLabel.setText(registered ? "✅ Registered successfully!" : "❌ Registration failed.");
        });

        VBox root = new VBox(15, titleLabel, emailField, passwordField, loginButton, registerButton, statusLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 350, 350);
        stage.setScene(scene);
        stage.setTitle("User Login/Register");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // ✅ هذه تجعلها الشاشة الأساسية عند التشغيل
    }
}
