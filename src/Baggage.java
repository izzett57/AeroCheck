public class Baggage {
    private int noOfBaggage;
    private double baggageWeight;
    private String baggageTag;
    private boolean hasScreened;
    
    Baggage(int noOfBaggage, double baggageWeight, String baggageTag, boolean hasScreened) {
        this.noOfBaggage = noOfBaggage;
        this.baggageWeight = baggageWeight;
        this.baggageTag = baggageTag;
        this.hasScreened = hasScreened;
    }
    
    // Getter

    
    public int getNoOfBaggage() {
        return noOfBaggage;
    }
    
    public double getBaggageWeight() {
        return baggageWeight;
    }
    
    public String getBaggageTag() {
        return baggageTag;
    }

    public boolean getHasScreened() {
        return hasScreened;
    }
    
    // Setter
    public void setNoOfBaggage(int noOfBaggage) {
        this.noOfBaggage = noOfBaggage;
    }
    
    public void setBaggageWeight(double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }
    
    public void setBaggageTag(String baggageTag) {
        this.baggageTag = baggageTag;
    }

    public void setHasScreened(boolean hasScreened) {
        this.hasScreened = hasScreened;
    }
}
