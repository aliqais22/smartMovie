public class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean login(String email, String password) {
        // مثال للتحقق البسيط فقط
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("✅ User logged in with email: " + email);
            return true;
        } else {
            System.out.println("❌ Invalid email or password!");
            return false;
        }
    }

    public Ticket purchaseTicket(Screening screening) {
        System.out.println("Purchasing ticket for screening ID: " + screening.getAvailableSeats());
        // يولّد Ticket تجريبي
        return new Ticket(30.0f, "A5", 1001);
    }

    public boolean register() {
        System.out.println("✅ User registered: " + username + " with email: " + email);
        return true;
    }

    // Getters (اختياري)
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}
