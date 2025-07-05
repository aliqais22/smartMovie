public class Seat {
    private boolean isAvailable;
    private int number;
    private String row;
    private int seatID;
    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }


    public Seat(int number, String row, int seatID) {
        this.isAvailable = true;
        this.number = number;
        this.row = row;
        this.seatID = seatID;
    }

    public void release() {
        this.isAvailable = true;
        System.out.println("Seat released: " + seatID);
    }

    public boolean reserve() {
        if (isAvailable) {
            this.isAvailable = false;
            System.out.println("Seat reserved: " + seatID);
            return true;
        } else {
            System.out.println("Seat not available: " + seatID);
            return false;
        }
    }

    // Getters
    public boolean isAvailable() { return isAvailable; }
    public int getSeatID() { return seatID; }
}
