package Grazioso;

/**
 * ****************************************************************************
 * Class: Monkey
 * Inherits: RescueAnimal
 * * Manages Primate assets. Implements species validation and physical
 * measurement tracking (Tail, Height, Body) required for specialized tasks.
 * ****************************************************************************
 */
public class Monkey extends RescueAnimal {
    
    private String species;
    private double tailLength;
    private double height;
    private double bodyLength;

    // Approved species registry
    public final static String[] AUTHORIZED_SPECIES = {
        "Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel monkey", "Tamarin"
    };

    /**
     * Specialized Primate Constructor including physical metrics.
     */
    public Monkey(String name, String species, String gender, String age,
                  String weight, String acquisitionDate, String acquisitionCountry,
                  String trainingStatus, boolean reserved, String inServiceCountry,
                  double tailLength, double height, double bodyLength) {
        setName(name);
        setSpecies(species);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);
        setAnimalType("Monkey");
        
        this.tailLength = tailLength;
        this.height = height;
        this.bodyLength = bodyLength;
    }

    // Getters and Setters
    public String getSpecies() { return species; }
    public double getTailLength() { return tailLength; }
    public double getHeight() { return height; }
    public double getBodyLength() { return bodyLength; }

    public void setSpecies(String species) { this.species = species; }
    public void setTailLength(double tailLength) { this.tailLength = tailLength; }
    public void setHeight(double height) { this.height = height; }
    public void setBodyLength(double bodyLength) { this.bodyLength = bodyLength; }

    /**
     * Comprehensive search-format output for Primate records including physicals.
     */
    @Override
    public String toString() {
        return String.format("\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %b, \"%s\", %.1f, %.1f, %.1f",
                getName(), species, getGender(), getAge(), getWeight(), 
                getAcquisitionDate(), getAcquisitionLocation(), 
                getTrainingStatus(), getReserved(), getInServiceLocation(),
                tailLength, height, bodyLength);
    }
}