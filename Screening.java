import java.time.LocalDateTime;

public class Screening {
    private int availableSeats;
    private LocalDateTime dateTime;
    private int hallNumber;
    private int screeningID;

    public Screening(int availableSeats, LocalDateTime dateTime, int hallNumber, int screeningID) {
        this.availableSeats = availableSeats;
        this.dateTime = dateTime;
        this.hallNumber = hallNumber;
        this.screeningID = screeningID;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean reserveSeat(String seatNumber) {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println("Seat " + seatNumber + " reserved for screening " + screeningID);
            return true;
        } else {
            System.out.println("No available seats for screening " + screeningID);
            return false;
        }
    }
}
