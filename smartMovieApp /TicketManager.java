import java.util.Random;

public class TicketManager {

    public boolean checkMovieAvailability(String movieName) {
        System.out.println("Checking availability for movie: " + movieName);
        return true; // دائمًا متاح لتجربة الشاشة
    }

    public Ticket issueTicket(float price, String seatNumber) {
        int ticketID = new Random().nextInt(1000) + 1; // توليد رقم عشوائي للتجربة
        Ticket ticket = new Ticket(price, seatNumber, ticketID);
        System.out.println("✅ Ticket issued! ID: " + ticketID);
        return ticket;
    }
}
