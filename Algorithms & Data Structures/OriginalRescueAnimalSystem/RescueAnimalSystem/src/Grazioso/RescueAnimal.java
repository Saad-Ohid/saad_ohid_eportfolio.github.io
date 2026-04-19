package Grazioso;

import java.lang.String; // Importing the String class from the java.lang package

public class RescueAnimal {

    // Instance variables to hold information about the animal
    private String name; // The name of the animal
    private String animalType; // The type of animal (e.g., dog, monkey)
    private String gender; // The gender of the animal (e.g., male, female)
    private String age; // The age of the animal
    private String weight; // The weight of the animal
    private String acquisitionDate; // The date when the animal was acquired
    private String acquisitionCountry; // The country where the animal was acquired
    private String trainingStatus; // The current training status of the animal
    private boolean reserved; // Whether the animal is reserved or not
    private String inServiceCountry; // The country where the animal is currently in service

    // Default constructor for creating a new RescueAnimal object
    public RescueAnimal() {
        // No initialization needed in this constructor
    }

    // Accessor method to get the name of the animal
    public String getName() {
        return name; // Return the name variable
    }

    // Mutator method to set the name of the animal
    public void setName(String name) {
        this.name = name; // Update the name variable with the provided name
    }

    // Accessor method to get the type of the animal
    public String getAnimalType() {
        return animalType; // Return the animalType variable
    }

    // Mutator method to set the type of the animal
    public void setAnimalType(String animalType) {
        this.animalType = animalType; // Update the animalType variable
    }

    // Accessor method to get the gender of the animal
    public String getGender() {
        return gender; // Return the gender variable
    }

    // Mutator method to set the gender of the animal
    public void setGender(String gender) {
        this.gender = gender; // Update the gender variable
    }

    // Accessor method to get the age of the animal
    public String getAge() {
        return age; // Return the age variable
    }

    // Mutator method to set the age of the animal
    public void setAge(String age) {
        this.age = age; // Update the age variable
    }

    // Accessor method to get the weight of the animal
    public String getWeight() {
        return weight; // Return the weight variable
    }

    // Mutator method to set the weight of the animal
    public void setWeight(String weight) {
        this.weight = weight; // Update the weight variable
    }

    // Accessor method to get the acquisition date of the animal
    public String getAcquisitionDate() {
        return acquisitionDate; // Return the acquisitionDate variable
    }

    // Mutator method to set the acquisition date of the animal
    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate; // Update the acquisitionDate variable
    }

    // Accessor method to get the acquisition location of the animal
    public String getAcquisitionLocation() {
        return acquisitionCountry; // Return the acquisitionCountry variable
    }

    // Mutator method to set the acquisition location of the animal
    public void setAcquisitionLocation(String acquisitionCountry) {
        this.acquisitionCountry = acquisitionCountry; // Update the acquisitionCountry variable
    }

    // Accessor method to check if the animal is reserved
    public boolean getReserved() {
        return reserved; // Return the reserved variable
    }

    // Mutator method to set the reserved status of the animal
    public void setReserved(boolean reserved) {
        this.reserved = reserved; // Update the reserved variable
    }

    // Accessor method to get the in-service location of the animal
    public String getInServiceLocation() {
        return inServiceCountry; // Return the inServiceCountry variable
    }

    // Mutator method to set the in-service location of the animal
    public void setInServiceCountry(String inServiceCountry) {
        this.inServiceCountry = inServiceCountry; // Update the inServiceCountry variable
    }

    // Accessor method to get the training status of the animal
    public String getTrainingStatus() {
        return trainingStatus; // Return the trainingStatus variable
    }

    // Mutator method to set the training status of the animal
    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus; // Update the trainingStatus variable
    }
}
