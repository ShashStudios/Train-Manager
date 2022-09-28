package Homework2;
/**
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class ProductLoad {

    String name;
    double weight;
    double value;
    boolean isDangerous;

    public ProductLoad(String productName, double weight, double value, boolean isDangerous) {
        this.name = productName;
        this.weight = weight;
        this.value = value;
        this.isDangerous = isDangerous;
    }
    public ProductLoad(){
        this.name ="Empty";
        this.weight = 0.0;
        this.value = 0.0;
        this.isDangerous = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    public String toString(){
        String format = String.format("%8s%16s%14s%12s", name, weight, value, (isDangerous? "YES":"NO"));
        return format;
    }
    public void removedProductPrint(){

        System.out.println(String.format("%8s%16s%14s%12s", this.getName(), this.getWeight(), this.getValue(), (this.isDangerous()? "YES":"NO")));
    }
}
