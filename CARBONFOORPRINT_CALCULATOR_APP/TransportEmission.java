package CARBONFOORPRINT_CALCULATOR_APP;

public class TransportEmission extends EmissionSource {
    private String vehicleType;
    private double annualKm;

    public TransportEmission(String vehicleType, double annualKm) {
        this.vehicleType = vehicleType;
        this.annualKm = annualKm;
    }

    public double calculateEmission() {
        double factor = switch (vehicleType.toLowerCase()) {
            case "petrol car" -> 0.192;
            case "diesel car" -> 0.171;
            case "motorbike" -> 0.072;
            case "bus" -> 0.027;
            case "train" -> 0.041;
            case "flight" -> 0.255;
            default -> 0;
        };
        emission = factor * annualKm;
        return emission;
    }

    public String toString() {
        return String.format("Transport (%s): %.2f kg CO₂", vehicleType, getEmission());
    }
}
