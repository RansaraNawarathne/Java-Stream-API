public class Aircraft {
    private ManufacturerEnum manufacturer;
    private String model;
    private float maxPayLoadCapacity;
    private int seatingCapacity;
    private float range;

    public Aircraft(ManufacturerEnum manufacturer, String model, float maxPayLoadCapacity, int seatingCapacity, float range) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.maxPayLoadCapacity = maxPayLoadCapacity;
        this.seatingCapacity = seatingCapacity;
        this.range = range;
    }

    public ManufacturerEnum getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public float getMaxPayLoadCapacity() {
        return maxPayLoadCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public float getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Manufacturer : " + this.manufacturer + "\n"
                + "Model : " + this.model + "\n"
                + "Max Payload : " + this.maxPayLoadCapacity + "kg\n"
                + "Seating Capacity : " + this.seatingCapacity + "\n"
                + "Range : " + this.range + "km\n";
    }
}
