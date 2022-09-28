package Homework2;

import java.awt.*;
import java.util.List;
/**
 * The <code>TrainCar/code> class contains ProductLoad objects.
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class TrainCar {
    double carLength;
    double carWeight;
    ProductLoad load;

    /**
     * Makes a car onbject with the given length and weight
     * @param carLength
     * @param carWeight
     */
    public TrainCar(double carLength, double carWeight) {
        this.carLength = carLength;
        this.carWeight = carWeight;
    }

    /**
     * Returns the length of this car
     * @return
     *      Returns the length in meters of this car
     */
    public double getCarLength() {
        return carLength;
    }

    /**
     * Returns the weight in tons of this car
     * @return
     *      Returns the weight in tons of this car
     */
    public double getCarWeight() {

        return carWeight;
    }

    /**
     * Returns the load object contained within this car
     * @return
     *      Returns the load object contained within this car
     */
    public ProductLoad getLoad() {
        return load;
    }

    /**
     * Sets the load object of this car to the load object given as a parameter
     * @param load
     */
    public void setLoad(ProductLoad load) {
        this.load = load;
    }


    /**
     * Returns true if the there is no load object in the car, otherwise returns false
     * @return
     *      Returns true if the there is no load object in the car, otherwise returns false
     */
    public boolean isEmpty(){
        return (this.load == null);
    }

    /**
     * Returns a properly formatted String representation of a car with all of its respective values
     * @return
     *      Returns a properly formatted String representation of a car with all of its respective values
     */
    public String toString(){
        String carFormat = String.format("%13s%14s", carLength, carWeight) +"  |";
        return carFormat;
    }

}
