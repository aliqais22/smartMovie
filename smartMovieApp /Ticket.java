public class Ticket {
    private float price;
    private String seatNumber;
    private int ticketID;

    public Ticket(float price, String seatNumber, int ticketID) {
        this.price = price;
        this.seatNumber = seatNumber;
        this.ticketID = ticketID;
    }
    public int getTicketID() {
        return ticketID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public float getPrice() {
        return price;
    }


    public boolean cancelTicket() {
        System.out.println("Ticket " + ticketID + " canceled");
        return true;
    }

    public void printTicket() {
        System.out.println("Printing Ticket: ID " + ticketID + ", Seat: " + seatNumber + ", Price: $" + price);
    }
}
