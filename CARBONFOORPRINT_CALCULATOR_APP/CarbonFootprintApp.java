package CARBONFOORPRINT_CALCULATOR_APP;

import java.util.*;

public class CarbonFootprintApp{
    private static final Scanner sc = new Scanner(System.in);
    private static final HashMap<String, EmissionSource> sources = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            try {
                showMenu();
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> enterTransport();
                    case 2 -> enterElectricity();
                    case 3 -> enterDiet();
                    case 4 -> calculateFootprint();
                    case 5 -> {
                        System.out.println("Exiting. Goodbye!");
                        return;
                    }
                    default -> throw new InvalidUserInputException("Please select a valid option (1-5).");
                }

            } catch (InvalidUserInputException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("----------------CALCULATING THE CARBON EMMISION ---------------");
        System.out.println("\nMAIN MENU\n---------");
        System.out.println("1. Enter the Transport Data");
        System.out.println("2. Enter the  Electricity Data");
        System.out.println("3. Enter the  Diet Data");
        System.out.println("4. Calculate Carbon Footprint & Offset Cost");
        System.out.println("5. Exit");
        System.out.print("Choose option (1-5): ");
    }

    private static void enterTransport() throws InvalidUserInputException {
        System.out.println("\n-- Transport Data --");
        System.out.println("Vehicle type?\n 1-Petrol Car\n 2-Diesel Car\n 3-Motorbike\n 4-Bus\n 5-Train\n 6-Flight\n");
        int type = Integer.parseInt(sc.nextLine());

        String vehicle = switch (type) {
            case 1 -> "Petrol Car";
            case 2 -> "Diesel Car";
            case 3 -> "Motorbike";
            case 4 -> "Bus";
            case 5 -> "Train";
            case 6 -> "Flight";
            default -> throw new InvalidUserInputException("Invalid vehicle type.");
        };

        System.out.print("Annual kilometres: ");
        double km = Double.parseDouble(sc.nextLine());
        if (km < 0) throw new InvalidUserInputException("Distance cannot be negative.");

        TransportEmission te = new TransportEmission(vehicle, km);
        sources.put("transport", te);
        System.out.println("Saved.");
    }

    private static void enterElectricity() throws InvalidUserInputException {
        System.out.print("\nAnnual kWh consumed: ");
        double kwh = Double.parseDouble(sc.nextLine());
        if (kwh < 0) throw new InvalidUserInputException("Consumption cannot be negative.");

        ElectricityEmission ee = new ElectricityEmission(kwh);
        sources.put("electricity", ee);
        System.out.println("Saved.");
    }

    private static void enterDiet() throws InvalidUserInputException {
        System.out.println("\nDiet type?\n 1-Meat-heavy\n 2-Average\n 3-Vegetarian\n 4-Vegan\n ");
        int type = Integer.parseInt(sc.nextLine());

        String diet = switch (type) {
            case 1 -> "Meat-heavy";
            case 2 -> "Average";
            case 3 -> "Vegetarian";
            case 4 -> "Vegan";
            default -> throw new InvalidUserInputException("Invalid diet type.");
        };

        DietEmission de = new DietEmission(diet);
        sources.put("diet", de);
        System.out.println("Saved.");
    }

    private static void calculateFootprint() {
        System.out.println("\n==== Annual Footprint ====");
        double total = 0;

        for (Map.Entry<String, EmissionSource> entry : sources.entrySet()) {
            EmissionSource es = entry.getValue();
            es.calculateEmission();
            System.out.printf("%-14s %.2f kg CO2\n", capitalize(entry.getKey()) + ":", es.getEmission());
            total += es.getEmission();
        }

        double totalTonnes = total / 1000.0;
        int offsetCost = (int) Math.round(totalTonnes * 850);

        System.out.println("-------------------------------");
        System.out.printf("Total:         %.2f kg CO2  (≈ %.2f t)\n", total, totalTonnes);
        System.out.printf("\nApprox. offset cost  RS.850/t = Rs.%d\n", offsetCost);
        System.out.println("===========================\n");
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
