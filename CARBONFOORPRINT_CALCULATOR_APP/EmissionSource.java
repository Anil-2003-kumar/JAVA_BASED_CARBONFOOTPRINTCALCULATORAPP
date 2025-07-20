package CARBONFOORPRINT_CALCULATOR_APP;

public abstract class EmissionSource {
    protected double emission; // in kilograms

    public abstract double calculateEmission();

    public double getEmission() {
        return Math.round(emission * 100.0) / 100.0;
    }
}

