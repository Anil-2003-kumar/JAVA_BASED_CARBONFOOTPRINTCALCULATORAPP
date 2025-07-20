package CARBONFOORPRINT_CALCULATOR_APP;

public class ElectricityEmission extends EmissionSource {
    private double annualKWh;

    public ElectricityEmission(double annualKWh) {
        this.annualKWh = annualKWh;
    }

    public double calculateEmission() {
        emission = 0.82 * annualKWh;
        return emission;
    }

    public String toString() {
        return String.format("Electricity: %.2f kg CO₂", getEmission());
    }
}

