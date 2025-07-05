public class Payment {
    private float amount;
    private int paymentID;
    private String paymentMethod;
    private String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }


    public Payment(float amount, int paymentID, String paymentMethod, String paymentStatus) {
        this.amount = amount;
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public boolean processPayment() {
        System.out.println("Processing payment: ID " + paymentID + ", Amount $" + amount);
        this.paymentStatus = "Processed";
        return true;
    }

    public boolean refund() {
        System.out.println("Refunding payment: ID " + paymentID);
        this.paymentStatus = "Refunded";
        return true;
    }
}
