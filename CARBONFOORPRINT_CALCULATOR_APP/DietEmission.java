package CARBONFOORPRINT_CALCULATOR_APP;

public class DietEmission extends EmissionSource {
    private String dietType;

    public DietEmission(String dietType) {
        this.dietType = dietType;
    }

    public double calculateEmission() {
        emission = switch (dietType.toLowerCase()) {
            case "meat-heavy" -> 3200;
            case "average" -> 2500;
            case "vegetarian" -> 1700;
            case "vegan" -> 1500;
            default -> 0;
        };
        return emission;
    }

    public String toString() {
        return String.format("Diet (%s): %.2f kg CO₂", dietType, getEmission());
    }
}

