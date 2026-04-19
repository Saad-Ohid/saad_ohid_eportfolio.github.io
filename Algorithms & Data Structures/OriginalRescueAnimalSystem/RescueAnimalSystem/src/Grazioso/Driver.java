package Grazioso;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for the Rescue Animal System.
 * This program allows users to manage a list of rescue animals, including dogs and monkeys.
 * Users can intake new animals, reserve them, and print lists of available animals.
 * 
 * @author Saad Ohiduzzaman
 * @date 10/19/2024
 */

public class Driver {
    private static ArrayList<Dog> dogList = new ArrayList<Dog>(); // List to store Dog objects
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>(); // List to store Monkey objects

    public static void main(String[] args) {
        initializeDogList(); // Populate the dog list with sample data
        initializeMonkeyList(); // Populate the monkey list with sample data

        Scanner scanner = new Scanner(System.in); // Create a scanner object for user input
        boolean acceptingInput = true; // Flag to control the input loop

        // Menu loop for user interaction
        while (acceptingInput) {
            displayMenu(); // Display the menu options
            String option = scanner.nextLine().trim().toLowerCase(); // Read user input and normalize it

            // Switch case to handle user input
            switch (option) {
                case "1":
                    try {
                        intakeNewDog(scanner); // Intake a new dog
                    } catch (Exception e) {
                        System.out.println("Error adding new dog: " + e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        intakeNewMonkey(scanner); // Intake a new monkey
                    } catch (Exception e) {
                        System.out.println("Error adding new monkey: " + e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        reserveAnimal(scanner); // Reserve an animal
                    } catch (Exception e) {
                        System.out.println("Error reserving animal: " + e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        printAnimals("dog"); // Print all dogs
                    } catch (Exception e) {
                        System.out.println("Error printing dogs: " + e.getMessage());
                    }
                    break;
                case "5":
                    try {
                        printAnimals("monkey"); // Print all monkeys
                    } catch (Exception e) {
                        System.out.println("Error printing monkeys: " + e.getMessage());
                    }
                    break;
                case "6":
                    try {
                        printAnimals("available"); // Print all available animals
                    } catch (Exception e) {
                        System.out.println("Error printing available animals: " + e.getMessage());
                    }
                    break;
                case "q":
                    acceptingInput = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option, please input a valid option."); // Error message
                    break;
            }
        }

        System.out.println("Goodbye"); // Farewell message
        scanner.close(); // Close scanner to prevent resource leak
    }

    // This method prints the menu options
    public static void displayMenu() {
        System.out.println("\n\n");
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.println("Enter a menu selection");
    }

    // Adds dogs to a list for testing
    public static void initializeDogList() {
        Dog dog1 = new Dog("Spot", "German Shepherd", "male", "1", "25.6", "05-12-2019", "United States", "intake", false, "United States");
        Dog dog2 = new Dog("Rex", "Great Dane", "male", "3", "35.2", "02-03-2020", "United States", "Phase I", false, "United States");
        Dog dog3 = new Dog("Bella", "Chihuahua", "female", "4", "25.6", "12-12-2019", "Canada", "in service", true, "Canada");
        Dog dog4 = new Dog("Max", "Golden Retriever", "male", "2", "30.5", "06-15-2020", "United States", "intake", false, "United States");
        Dog dog5 = new Dog("Luna", "Poodle", "female", "1", "20.3", "03-20-2021", "Canada", "Phase I", false, "Canada");

        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        dogList.add(dog4);
        dogList.add(dog5);
    }

    // Adds monkeys to a list for testing
    public static void initializeMonkeyList() {
        Monkey monkey1 = new Monkey("Cappy", "Capuchin", "male", "1", "5.6", "05-21-2021", "United States", "intake", false, "United States", 20, 30, 40);
        Monkey monkey2 = new Monkey("Mango", "Tamarin", "male", "2", "6.2", "07-15-2020", "Canada", "Phase I", false, "Canada", 25, 35, 45);
        Monkey monkey3 = new Monkey("Bongo", "Macaque", "male", "3", "7.5", "09-10-2019", "United States", "in service", true, "United States", 30, 40, 50);

        monkeyList.add(monkey1);
        monkeyList.add(monkey2);
        monkeyList.add(monkey3);
    }

    // Complete the intakeNewDog method
    public static void intakeNewDog(Scanner scanner) {
        System.out.println("What is the dog's name?");
        String name = scanner.nextLine();

        // Check if the dog already exists
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis dog is already in our system\n\n");
                return; // Return to menu if dog exists
            }
        }

        // Prompt user for dog details and validate input
        String breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus;
        boolean reserved;
        String inServiceCountry;

        System.out.println("What is the dog's breed?");
        breed = scanner.nextLine();

        System.out.println("What is the dog's gender?");
        gender = scanner.nextLine();

        System.out.println("What is the dog's age?");
        age = scanner.nextLine();

        System.out.println("What is the dog's weight?");
        weight = scanner.nextLine();

        System.out.println("What is the acquisition date?");
        acquisitionDate = scanner.nextLine();

        System.out.println("What is the acquisition country?");
        acquisitionCountry = scanner.nextLine();

        System.out.println("What is the training status?");
        trainingStatus = scanner.nextLine();

        System.out.println("Is the dog reserved? (true/false)");
        reserved = scanner.nextBoolean();
        scanner.nextLine(); // Clear the newline character

        System.out.println("What is the dog's in service country?");
        inServiceCountry = scanner.nextLine();

        // Create a new Dog object with the provided information
        Dog newDog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry);
        dogList.add(newDog); // Add the new dog to the list

        System.out.println("New dog added: " + newDog); // Confirm addition of new dog
    }

    // Complete intakeNewMonkey
    public static void intakeNewMonkey(Scanner scanner) {
        System.out.println("What is the monkey's name?");
        String name = scanner.nextLine();

        // Check if the monkey already exists
        for (Monkey monkey : monkeyList) {
            if (monkey.getName().equalsIgnoreCase(name)) {
                System.out.println("\n\nThis monkey is already in our system\n\n");
                return; // Return to menu if monkey exists
            }
        }

        // Prompt user for monkey details and validate input
        String breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus;
        boolean reserved;
        String inServiceCountry;
        double tailLength, height, bodyLength;

        System.out.println("What is the monkey's breed?");
        breed = scanner.nextLine();

        // Validate monkey breed
        boolean validBreed = false;
        for (String validBreedOption : Monkey.VALID_BREEDS) {
            if (breed.equalsIgnoreCase(validBreedOption)) {
                validBreed = true;
                break;
            }
        }
        if (!validBreed) {
            System.out.println("Invalid breed. Please choose from: " + String.join(", ", Monkey.VALID_BREEDS));
            return;
        }

        System.out.println("What is the monkey's gender?");
        gender = scanner.nextLine();

        System.out.println("What is the monkey's age?");
        age = scanner.nextLine();

        System.out.println("What is the monkey's weight?");
        weight = scanner.nextLine();

        System.out.println("What is the acquisition date?");
        acquisitionDate = scanner.nextLine();

        System.out.println("What is the acquisition country?");
        acquisitionCountry = scanner.nextLine();

        System.out.println("What is the training status?");
        trainingStatus = scanner.nextLine();

        System.out.println("Is the monkey reserved? (true/false)");
        reserved = scanner.nextBoolean();
        scanner.nextLine(); // Clear the newline character

        System.out.println("What is the monkey's in service country?");
        inServiceCountry = scanner.nextLine();

        System.out.println("What is the monkey's tail length (in cm)?");
        tailLength = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline character

        System.out.println("What is the monkey's height (in cm)?");
        height = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline character

        System.out.println("What is the monkey's body length (in cm)?");
        bodyLength = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline character

        // Create a new Monkey object with the provided information
        Monkey newMonkey = new Monkey(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus, reserved, inServiceCountry, tailLength, height, bodyLength);
        monkeyList.add(newMonkey); // Add the new monkey to the list

        System.out.println("New monkey added: " + newMonkey); // Confirm addition of new monkey
    }

    // Complete reserveAnimal
    public static void reserveAnimal(Scanner scanner) {
        System.out.println("Enter the name of the animal to reserve:");
        String name = scanner.nextLine();

        boolean found = false; // Flag to check if the animal is found

        // Check if the animal is a dog and not already reserved
        for (Dog dog : dogList) {
            if (dog.getName().equalsIgnoreCase(name) && !dog.getReserved()) {
                dog.setReserved(true); // Mark the dog as reserved
                System.out.println("Dog " + dog.getName() + " has been reserved."); // Confirmation message
                found = true; // Set the flag to true
                break; // Exit the loop as we found the dog
            }
        }

        // Check if the animal is a monkey and not already reserved
        if (!found) {
            for (Monkey monkey : monkeyList) {
                if (monkey.getName().equalsIgnoreCase(name) && !monkey.getReserved()) {
                    monkey.setReserved(true); // Mark the monkey as reserved
                    System.out.println("Monkey " + monkey.getName() + " has been reserved."); // Confirmation message
                    found = true; // Set the flag to true
                    break; // Exit the loop as we found the monkey
                }
            }
        }

        // If animal is not found or already reserved, inform the user
        if (!found) {
            System.out.println("Animal not found or already reserved."); // Error message
        }
    }

    // Complete printAnimals
    public static void printAnimals(String listType) {
        switch (listType) {
            case "dog":
                // Print all dogs
                for (Dog dog : dogList) {
                    System.out.println(dog); // Print each dog's information
                }
                break;
            case "monkey":
                // Print all monkeys
                for (Monkey monkey : monkeyList) {
                    System.out.println(monkey); // Print each monkey's information
                }
                break;
            case "available":
                // Print all available animals (not reserved)
                for (Dog dog : dogList) {
                    if (!dog.getReserved()) {
                        System.out.println(dog); // Print each available dog's information
                    }
                }
                for (Monkey monkey : monkeyList) {
                    if (!monkey.getReserved()) {
                        System.out.println(monkey); // Print each available monkey's information
                    }
                }
                break;
            default:
                // Handle invalid list type input
                System.out.println("Invalid list type."); // Error message
                break;
        }
    }
}