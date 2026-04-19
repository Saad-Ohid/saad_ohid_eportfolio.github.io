package Grazioso;

import java.util.HashMap;
import java.util.Scanner;

/**
 * ****************************************************************************
 * Class: Driver
 * Main Controller for Grazioso Salvare Logistics Management.
 * * FEATURES:
 * - Role-Based Access Control (Admin vs User login states).
 * - Advanced Record Update System for live inventory modifications.
 * - Multi-species Intake and Search logic.
 * - Comprehensive reporting modules.
 * - HashMap implementation for O(1) data retrieval.
 * ****************************************************************************
 */
public class Driver {
    // Inventory Database transitioned to HashMaps for optimized search
    private static HashMap<String, Dog> dogMap = new HashMap<String, Dog>();
    private static HashMap<String, Monkey> monkeyMap = new HashMap<String, Monkey>();
    private static String currentUserRole = "GUEST"; 

    public static void main(String[] args) {
        initializeDogList();
        initializeMonkeyList();
        Scanner sc = new Scanner(System.in);

        // --- ENHANCED SECURITY LOGIN GATE ---
        System.out.println("*******************************************************");
        System.out.println("   GRAZIOSO SALVARE SECURE ACCESS GATE(user/ admin)    ");
        System.out.println("*******************************************************");
        
        System.out.print("Enter Access Role: ");
        String roleInput = sc.nextLine().trim().toLowerCase();
        
        // Secure authentication logic
        if (roleInput.equals("admin")) {
            System.out.print("Enter Administrative Password: ");
            String pass = sc.nextLine();
            if (pass.equals("password123")) {
                currentUserRole = "ADMIN";
                System.out.println("\n>> ACCESS GRANTED: ADMINISTRATIVE MODE ENABLED.");
            } else {
                System.out.println("\n>> ACCESS DENIED: INVALID CREDENTIALS. DEFAULTING TO USER.");
                currentUserRole = "USER";
            }
        } else {
            currentUserRole = "USER";
            System.out.println("\n>> ACCESS GRANTED: STANDARD USER MODE ENABLED.");
        }

        boolean systemActive = true;
        while (systemActive) {
            displayMenu();
            String command = sc.nextLine().trim().toLowerCase();

            // Menu command processing with RBAC checks
            switch (command) {
                case "1": 
                    printAnimals("available"); 
                    break;
                case "2": 
                    // Search and Reserve is restricted to Admin as it modifies data
                    if (currentUserRole.equals("ADMIN")) {
                        reserveAnimal(sc); 
                    } else {
                        accessDenied();
                    }
                    break;
                case "3": 
                    if (currentUserRole.equals("ADMIN")) {
                        intakeNewDog(sc); 
                    } else {
                        accessDenied();
                    }
                    break;
                case "4": 
                    if (currentUserRole.equals("ADMIN")) {
                        intakeNewMonkey(sc); 
                    } else {
                        accessDenied();
                    }
                    break;
                case "5": 
                    printAnimals("dog"); 
                    break;
                case "6": 
                    printAnimals("monkey"); 
                    break;
                case "7": 
                    printAnimals("all"); 
                    break;
                case "8": 
                    if (currentUserRole.equals("ADMIN")) {
                        updateAnimalRecords(sc); 
                    } else {
                        accessDenied();
                    }
                    break;
                case "q": 
                    System.out.println("\n>> LOGGING OUT... SECURING DATABASE ASSETS...");
                    systemActive = false; 
                    break;
                default: 
                    System.out.println("\n[!] ERROR: Command unrecognized. Use 1-8 or q.");
            }
        }
        System.out.println(">> SYSTEM TERMINATED. CLEARING SESSION DATA.");
    }

    /**
     * RBAC Error Handler
     */
    private static void accessDenied() {
        System.out.println("\n[!] ACCESS DENIED: This function requires Administrative Clearance.");
    }

    /**
     * Main Navigation UI
     */
    public static void displayMenu() {
        System.out.println("\n------------------------------------------------");
        System.out.println(" SESSION: " + currentUserRole + " | SYSTEM STATUS: ACTIVE");
        System.out.println("------------------------------------------------");
        System.out.println("[1] View Ready Service Animals");
        System.out.println("[2] Search and Reserve Animal (Admin)");
        System.out.println("[3] REGISTER: New Dog (Admin Only)");
        System.out.println("[4] REGISTER: New Monkey (Admin Only)");
        System.out.println("[5] List All Canine Records");
        System.out.println("[6] List All Primate Records");
        System.out.println("[7] Full Inventory Logistics Report");
        System.out.println("[8] UPDATE: Modify Existing Record (Admin Only)");
        System.out.println("[q] Secure Session Logout");
        System.out.print("\nEnter Selection: ");
    }

    /**
     * Admin-Only: Data modification module using HashMap for search
     */
    public static void updateAnimalRecords(Scanner sc) {
        System.out.println("\n--- RECORD MANAGEMENT MODULE ---");
        System.out.print("Enter the name of the animal to modify: ");
        String name = sc.nextLine();
        
        // HashMap lookup replaces linear loops for efficiency
        if (dogMap.containsKey(name)) {
            Dog d = dogMap.get(name);
            System.out.print("New Training Status: "); 
            d.setTrainingStatus(sc.nextLine());
            System.out.print("Update Reservation (true/false): "); 
            d.setReserved(Boolean.parseBoolean(sc.nextLine()));
            System.out.println(">> Database update successful (Canine).");
        } 
        else if (monkeyMap.containsKey(name)) {
            Monkey m = monkeyMap.get(name);
            System.out.print("New Training Status: "); 
            m.setTrainingStatus(sc.nextLine());
            System.out.print("Update Reservation (true/false): "); 
            m.setReserved(Boolean.parseBoolean(sc.nextLine()));
            System.out.println(">> Database update successful (Primate).");
        } 
        else {
            System.out.println(">> [!] Error: No record found for " + name);
        }
    }

    /**
     * Multi-step intake logic for new Canines
     */
    public static void intakeNewDog(Scanner sc) {
        System.out.print("\nEnter Dog's Name: ");
        String name = sc.nextLine();
        
        // HashMap duplicate check
        if(dogMap.containsKey(name)) {
            System.out.println("\n[!] Error: " + name + " is already in the system.");
            return;
        }

        System.out.print("Breed: "); String breed = sc.nextLine();
        System.out.print("Gender: "); String gender = sc.nextLine();
        System.out.print("Age: "); String age = sc.nextLine();
        System.out.print("Weight (lbs): "); String weight = sc.nextLine();
        System.out.print("Acquisition Date: "); String date = sc.nextLine();
        System.out.print("Acquisition Country: "); String country = sc.nextLine();
        System.out.print("Training Status: "); String status = sc.nextLine();
        System.out.print("Reserved (true/false): "); 
        boolean reserved = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Deployment Country: "); String service = sc.nextLine();

        dogMap.put(name, new Dog(name, breed, gender, age, weight, date, country, status, reserved, service));
        System.out.println(">> Canine Unit Intake Complete.");
    }

    /**
     * Multi-step intake logic for new Primates
     */
    public static void intakeNewMonkey(Scanner sc) {
        System.out.print("\nEnter Monkey's Name: ");
        String name = sc.nextLine();
        
        // HashMap duplicate check
        if(monkeyMap.containsKey(name)) {
            System.out.println("\n[!] Error: " + name + " is already in the system.");
            return;
        }

        System.out.print("Species: ");
        String species = sc.nextLine();
        
        boolean authorized = false;
        for(String s : Monkey.AUTHORIZED_SPECIES) {
            if(s.equalsIgnoreCase(species)) authorized = true;
        }
        
        if(!authorized) {
            System.out.println("\n[!] Error: Species not authorized for the rescue program.");
            return;
        }

        try {
            System.out.print("Tail (cm): "); double tail = Double.parseDouble(sc.nextLine());
            System.out.print("Height (cm): "); double height = Double.parseDouble(sc.nextLine());
            System.out.print("Body (cm): "); double body = Double.parseDouble(sc.nextLine());
            
            System.out.print("Gender: "); String gen = sc.nextLine();
            System.out.print("Age: "); String age = sc.nextLine();
            System.out.print("Weight: "); String weight = sc.nextLine();
            System.out.print("Date: "); String date = sc.nextLine();
            System.out.print("Country: "); String country = sc.nextLine();
            System.out.print("Status: "); String status = sc.nextLine();
            System.out.print("Service Country: "); String service = sc.nextLine();

            monkeyMap.put(name, new Monkey(name, species, gen, age, weight, date, country, status, false, service, tail, height, body));
            System.out.println(">> Primate Unit Intake Complete.");
        } catch (NumberFormatException e) {
            System.out.println("\n[!] CRITICAL: Physical measurements must be numeric.");
        }
    }

    /**
     * Search and reserve an animal by name using HashMap lookup
     */
    public static void reserveAnimal(Scanner sc) {
        System.out.println("\n--- CURRENTLY AVAILABLE ANIMALS FOR RESERVATION ---");
        printAnimals("available");
        
        System.out.print("\nEnter target animal name for reservation: ");
        String name = sc.nextLine();
        
        if (dogMap.containsKey(name)) {
            Dog d = dogMap.get(name);
            if (d.isEligibleForDeployment()) {
                d.setReserved(true);
                System.out.println(">> Reservation confirmed for " + name + " (Canine).");
                return;
            }
        }
        
        if (monkeyMap.containsKey(name)) {
            Monkey m = monkeyMap.get(name);
            if (m.isEligibleForDeployment()) {
                m.setReserved(true);
                System.out.println(">> Reservation confirmed for " + name + " (Primate).");
                return;
            }
        }
        
        System.out.println("\n[!] Error: No available record found matching " + name);
    }

    /**
     * Centralized Reporting Engine using HashMap Iteration
     */
    public static void printAnimals(String listType) {
        System.out.println("\n--- GENERATING LOGISTICS REPORT: " + listType.toUpperCase() + " ---");
        
        if (listType.equals("available")) {
            for (Dog d : dogMap.values()) if (d.isEligibleForDeployment()) System.out.println(d);
            for (Monkey m : monkeyMap.values()) if (m.isEligibleForDeployment()) System.out.println(m);
        } else if (listType.equals("dog")) {
            for (Dog d : dogMap.values()) System.out.println(d);
        } else if (listType.equals("monkey")) {
            for (Monkey m : monkeyMap.values()) System.out.println(m);
        } else {
            for (Dog d : dogMap.values()) System.out.println(d);
            for (Monkey m : monkeyMap.values()) System.out.println(m);
        }
        System.out.println("--- REPORT END ---");
    }

    public static void initializeDogList() {
        dogMap.put("Bella", new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada"));
        dogMap.put("Daisy", new Dog("Daisy", "Dalmatian", "female", "2", "30", "01-01-2021", "USA", "in service", false, "USA"));
    }

    public static void initializeMonkeyList() {
        monkeyMap.put("Bongo", new Monkey("Bongo", "Macaque", "male", "3", "7.5", "09-10-2019", "United States", "in service", true, "United States", 30.0, 40.0, 50.0));
        monkeyMap.put("Kong", new Monkey("Kong", "Capuchin", "male", "5", "12", "06-06-2022", "Brazil", "in service", false, "UK", 20, 35, 45));
    }
}