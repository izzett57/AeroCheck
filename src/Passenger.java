public class Passenger extends Person {
    private String flightNo;
    private String bookingReference;
    private boolean specialNeeds;
    private String specialNeedsDetail;
    private boolean hasBaggage;
    
    
    Passenger(String name, String passportNo, String bookingReference, String flightNo, boolean specialNeeds) {
        super(name, passportNo);
        this.flightNo = flightNo;
        this.bookingReference = bookingReference;
        this.specialNeeds = specialNeeds;
        specialNeedsDetail = "";
        hasBaggage = false;
    }
    
    // Getter
    public String getBookingReference() {
        return bookingReference;
    }
    
    public String getFlightNo() {
        return flightNo;
    }
    
    public boolean getSpecialNeeds() {
        return specialNeeds;
    }

    public String getSpecialNeedsDetail() {
        return specialNeedsDetail;
    }
    
    public boolean getHasBaggage() {
        return hasBaggage;
    }
    
    // Setter
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
    
    public void setSpecialNeeds(boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
    }
    
    public void setSpecialNeedsDetail(String specialNeedsDetail) {
        this.specialNeedsDetail = specialNeedsDetail;
    }
    
    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }
    
    public void setHasBaggage(boolean hasBaggage) {
        this.hasBaggage = hasBaggage;
    }
}
