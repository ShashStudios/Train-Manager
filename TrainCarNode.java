package Homework2;
/**
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class TrainCarNode {
    TrainCarNode prev;
    TrainCarNode next;
    TrainCar car;

    /**
     * Makes an empty TrainCarNode object
     */
    public TrainCarNode() {
    }

    /**
     * Makes a TrainCarNode object with the TrainCar object in it
     * that s given as parameter
     * @param car
     */
    public TrainCarNode(TrainCar car) {
        this.car = car;
    }

    /**
     * Returns the node that comes before this node
     * @return
     *      Returns the node that comes before this node
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * Sets the previous node of this node to the TrainCarNode that is passed as the parameter
     * @param prev
     */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     * Returns the node that comes after this node. Returns null if no node exists
     * @return
     *      Returns the node that comes after this node. Returns null if no node exists
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * Sets the next node of this node to the node that is passes as the parameter
     * @param next
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * Returns the car object contained within this node
     * @return
     *      Returns the car object contained within this node
     */
    public TrainCar getCar() {
        return car;
    }

    /**
     * Sets this node's car object to the TrainCar provided as the parameter
     * @param car
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * Returns a properly formatted String representation of the TrainCarNode which includes
     * the string representation of the car and the load within the car
     * @return
     *      Returns a properly formatted String representation of the TrainCarNode which includes
     *      the string representation of the car and the load within the car
     */
    @Override
    public String toString() {
        String carFormat = String.format("%20.2f%20.2f", car.getCarLength(), car.getCarWeight()) +"  |";
        ProductLoad load = car.getLoad();
        if(load == null){
            load = new ProductLoad();
        }
        String loadFormat = String.format("%22s%21.2f%20.2f%19s", load.getName(), load.getWeight(), load.getValue(), (load.isDangerous()? "YES":"NO"));
        return carFormat+loadFormat;
    }
}
