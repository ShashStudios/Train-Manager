package Homework2;

/**
 * The <code>TrainLinkedList</code> class implements a doubly-linked list of <code>TrainCarNodes</code>
 * that contain TrainCar objects.
 *
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class TrainLinkedList{
    TrainCarNode head; //The first node of the linked list
    TrainCarNode tail; //The last node of the linked list
    TrainCarNode cursor; //A pointer to the current position in the linked list
    int listSize; //The number of nodes in this linked list

    int dangerCount; //Number indicating if any TrainCar in the linked list is dangerous. If it is greater than 0 then it is dangerous.
    double totalLength; //Value indicating the total length of the TrainCars in the linked list.
    double totalValue; //Value indicating the total value of the TrainCars including value of the load in the linked list.
    double totalWeight; // Value indicating the total weight of the TrainCars including weight of the load in the linked list.



    public TrainLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        listSize =0;
        dangerCount = 0;
        totalLength = 0.0;
        totalValue = 0.0;
        totalWeight= 0.0;
    }



    public TrainCarNode getCursor() {
        return cursor;
    }

    public void setCursor(TrainCarNode cursor) {
        this.cursor = cursor;
    }

    /**
     * Returns a reference to the TrainCar at the node currently referenced by the cursor.
     * Precondition:The list is not empty (cursor is not null).
     * @return
     *  The reference to the TrainCar at the node currently referenced by the cursor.
     */
    public TrainCar getCursorData(){
        if(cursor != null){
            TrainCar data = cursor.getCar();
            return data;
        }
        return null;
    }

    /**
     * Places car in the node currently referenced by the cursor.
     * Preconditions: The list is not empty (cursor is not null).
     * Postconditions: The cursor node now contains a reference to car as its data.
     *
     * @param car
     */
    public void setCursorData(TrainCar car){
        if(cursor!=null){
            cursor.setCar(car);
        }
    }


    /**
     * Moves the cursor to point at the next TrainCarNode.
     * Preconditions: The list is not empty (cursor is not null)
     *                and cursor does not currently reference the tail of the list.
     * Postconditions: The cursor has been advanced to the next TrainCarNode,
     *                 or has remained at the tail of the list.
     */
    public void cursorForward(){
        if(cursor.getNext() != null){
            cursor = cursor.getNext();
            System.out.println("Cursor moved forward");
        } else{
            System.out.println("No next car, cannot move cursor forward.");
        }
    }

    /**
     * Moves the cursor to point at the previous TrainCarNode.
     * Preconditions: The list is not empty (cursor is not null)
     *                and cursor does not currently reference the tail of the list.
     * Postconditions: The cursor has been moved back to the previous TrainCarNode,
     *                or has remained at the head of the list.
     */
    public void cursorBackward(){
        if(cursor.getPrev() != null){
            cursor = cursor.getPrev();
            System.out.println("Cursor moved backward");
        } else{
            System.out.println("No previous car, cannot move cursor backward.");
        }

    }

    /**
     * Inserts a car into the train after the cursor position.
     * Preconditions: This TrainCar object has been instantiated
     * Postconditions:
         * The new TrainCar has been inserted into the train after the position of the cursor.
         * All TrainCar objects previously on the train are still on the train, and their order has been preserved.
         * The cursor now points to the inserted car.
     * @param newCar
     * @throws IllegalArgumentException
     */
    public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException{
        if(newCar == null){
            throw new IllegalArgumentException();
        }
        TrainCarNode newNode = new TrainCarNode(newCar);
        ProductLoad load = newCar.getLoad();
        if(head == null){
            newNode.setPrev(null);
            newNode.setNext(null);
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else{
            if(cursor.getNext() == null){
                cursor.setNext(newNode);
                newNode.setPrev(cursor);
                newNode.next = null;
                tail = newNode;
                cursor = newNode;
            } else{
                TrainCarNode nextNode = cursor.getNext();
                cursor.setNext(newNode);
                newNode.setPrev(cursor);
                newNode.setNext(nextNode);
                nextNode.setPrev(newNode);
                cursor = newNode;
            }

        }
        listSize++;
        incrementLoadMetrics(newCar, load);
        incrementCarMetrics(newCar, load);
    }

    /**
     * Removes the TrainCarNode referenced by the cursor and returns the TrainCar contained within the node.
     * Preconditions: The cursor is not null.
     * Postconditions:
         * The TrainCarNode referenced by the cursor has been removed from the train.
         * The cursor now references the next node, or the previous node if no next node exists.
     * @return
     */
    public TrainCar removeCursor(){
        TrainCarNode nextNode = cursor.getNext();
        TrainCarNode previousNode = cursor.getPrev();
        TrainCarNode thisNode = cursor;
        TrainCar thisCar = thisNode.getCar();
        ProductLoad load = thisNode.getCar().getLoad();
        if(previousNode != null && nextNode != null){
            previousNode.setNext(nextNode);
            nextNode.setPrev(previousNode);
            cursor = nextNode;
        } else if(previousNode == null && nextNode != null){
            nextNode.setPrev(null);
            cursor = nextNode;
            head = cursor;
        } else if(previousNode != null && nextNode == null){
            cursor = previousNode;
            previousNode.setNext(null);
            tail = cursor;
        } else if(previousNode == null && nextNode == null){
            head = null;
            tail = null;
            cursor = null;
        }
        decrementLoadMetrics(thisCar, load);
        decrementCarMetrics(thisCar, load);
        listSize--;
        return thisNode.getCar();
    }

    /**
     * Decrements all the values of the train by the respective values of the car
     * Preconditions: Values are positive
     * Postconditions: All values are updated and are non-negative
     * @param thisCar
     * @param load
     */
    public void decrementCarMetrics(TrainCar thisCar, ProductLoad load) {
        totalLength = totalLength - thisCar.getCarLength();
        totalWeight = totalWeight - thisCar.getCarWeight();
    }

    /**
     * Decrements all the values of the train by the respective values of the load
     * Preconditions: Values are positive
     * Postconditions: All values are updated and are non-negative
     * @param thisCar
     * @param load
     */
    public void decrementLoadMetrics(TrainCar thisCar, ProductLoad load) {
        if(load != null){
            totalWeight = totalWeight - load.getWeight();
            totalValue = totalValue - load.getValue();
        }
        if(load != null && load.isDangerous()) {
            dangerCount--;
        }
    }

    /**
     * Increments all the values of the train by the respective values of the load
     * Preconditions: All values are non-negative
     * Postconditions: Values are updated and are non-negative
     * @param newCar
     * @param load
     */
    public void incrementCarMetrics(TrainCar newCar, ProductLoad load) {
        totalLength = totalLength + newCar.getCarLength();
        totalWeight = totalWeight+ newCar.getCarWeight();
    }

    /**
     * Increments all the values of the train by the respective values of the load
     * Preconditions: All values are non-negative
     * Postconditions: Values are updated and are non-negative
     * @param newCar
     * @param load
     */
    public void incrementLoadMetrics(TrainCar newCar, ProductLoad load) {
        if(load != null){
            totalWeight = totalWeight + load.getWeight();
            totalValue = totalValue + load.getValue();
        }
        if(load != null && load.isDangerous()) {
            dangerCount++;
        }
    }

    /**
     * Determines the number of TrainCar objects currently on the train.
     * @return
     *      The number of TrainCar objects on this train.
     */
    public int size(){
        return listSize;
    }


    /**
     * Returns the total length of the train in meters.
     * @return
     *      The sum of the lengths of each TrainCar in the train.
     */
    public double getLength(){
        return totalLength;
    }

    /**
     * Returns the total value of product carried by the train.
     * @return
     *      The sum of the values of each TrainCar in the train.
     */
    public double getValue(){
        return totalValue;
    }


    /**
     * Returns the total weight in tons of the train.
     * Note that the weight of the train is the sum of the
     * weights of each empty TrainCar, plus the weight of
     * the ProductLoad carried by that car.
     * @return
     *      The sum of the weight of each TrainCar plus the
     *      sum of the ProductLoad carried by that car.
     */
    public double getWeight(){
        return totalWeight;
    }

    /**
     * Whether or not there is a dangerous product on
     * one of the TrainCar objects on the train.
     * @return
     *      Returns true if the train contains at least
     *      one TrainCar carrying a dangerous ProductLoad, false otherwise.
     *
     */
    public boolean isDangerous(){
        return((dangerCount>0)? true: false);
    }

    /**
     * Searches the list for all ProductLoad objects with the indicated name
     * and sums together their weight and value (Also keeps track of whether
     * the product is dangerous or not), then prints a single ProductLoad
     *  record to the console.
     * @param name
     *       The name of the ProductLoad to find on the train.
     */
    public void findProduct(String name){
        TrainCarNode nodePtr = head;
        double value=0;
        double weight = 0;
        boolean isDangerous = false;
        int counter =0;

        while(nodePtr != null){
            ProductLoad load = nodePtr.getCar().getLoad();
            if(name.equalsIgnoreCase(load.getName())){
                value +=load.getValue();
                weight += load.getWeight();
                counter++;
                if(load.isDangerous())
                    isDangerous = true;
            }

            nodePtr = nodePtr.getNext();
        }
        if(counter>0){
            System.out.println("The following products were found on "+counter+" cars:");
            System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight(t)", "Value($)", "Dangerous"));
            System.out.println("==================================================");
            System.out.println(String.format("%8s%16s%14s%12s", name, weight, value, (isDangerous ? "YES": "NO")));
        } else{
            System.out.println("No record of "+name+" on board train.");
        }
    }

    /**
     * Prints a neatly formatted table of the car number, car length,
     * car weight, load name, load weight, load value, and load dangerousness
     * for all of the car on the train.
     *
     */
    public void printManifest(){
        //System.out.println("  CAR:                            LOAD:");
        System.out.printf("%-50s%5s%-80s","    Car:","     ", "Load:");
        System.out.println();
        System.out.print(String.format("%10s%20s%20s%5s", "Num","Length(m)", "Weight(t)", "  |  "));
        System.out.println(String.format("%20s%20s%20s%20s", "Name", "Weight(t)", "Value($)", "Dangerous"));
        System.out.println(String.format("%-50s%5s%-80s","       ===========================================", "  |  ", "================================================================================"));
        System.out.println(this.toString());
    }

    /**
     * Removes all dangerous cars from the train, maintaining the order
     * of the cars in the train.
     * Postconditions:
         * All dangerous cars have been removed from this train.
         * The order of all non-dangerous cars must be maintained upon the completion of this method.
     */
    public void removeDangerousCars(){
        cursor = head;
        while(cursor != null){
            ProductLoad load = cursor.getCar().getLoad();
            if(load.isDangerous()){
                TrainCar car = removeCursor();
                car.getLoad().removedProductPrint();
            } else{
                if(cursor.getNext()==null){
                    return;
                } else{
                    cursor = cursor.getNext();
                }
            }
        }
    }

    /**
     * Returns a neatly formatted String representation of the train.
     * @return
     *      A neatly formatted string containing information about the train,
     *      including its size (number of cars), length in meters, weight in tons,
     *      value in dollars, and whether it is dangerous or not.
     */
    @Override
    public String toString(){

        TrainCarNode nodePtr = this.head;
        int counter = 1;
        String entry="";
        while(nodePtr != null){
            String seqNo = "";
            if(nodePtr == cursor){
                seqNo = seqNo+"-> "+counter;
            } else{
                seqNo+=counter;
            }
            TrainCar car = nodePtr.getCar();
            ProductLoad load = car.getLoad();
            /*if(seqNo.equals("->")){
                entry =entry+seqNo+"  "+seqNo+nodePtr.toString()+"\n";
            } else{
                entry =entry+"    "+seqNo+nodePtr.toString()+"\n";
            }*/
            //entry =entry+String.format("%5s", seqNo,) +nodePtr.toString()+"\n";
            entry =entry+String.format("%10s%89s", seqNo,nodePtr.toString())+"\n";
            counter++;
            nodePtr = nodePtr.getNext();
        }
        return entry;

    }



}
