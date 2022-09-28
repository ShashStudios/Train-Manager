package Homework2;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Shashwat Panigrahi
 *    e-mail: shashwat.panigrahi@stonybrook.edu
 *    Stony Brook ID: 114848893
 **/

public class TrainManager {
    static TrainLinkedList trainLinkedList;
    static DecimalFormat decimalFormat;
    static {
        trainLinkedList = new TrainLinkedList();
        decimalFormat = new DecimalFormat("0.00");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean isDone = false;
        System.out.println("(F) Cursor Forward\n" +
                "(B) Cursor Backward\n" +
                "(I) Insert Car After Cursor\n" +
                "(R) Remove Car At Cursor\n" +
                "(L) Set Product Load\n" +
                "(S) Search For Product\n" +
                "(T) Display Train\n" +
                "(M) Display Manifest\n" +
                "(D) Remove Dangerous Cars\n" +
                "(Q) Quit");
        System.out.println();
        while (!isDone) {

            System.out.print("Enter Selection:");

            String selection = sc.next();


            switch(selection.toUpperCase().trim()){
                case "F":
                    cursorForward();
                    break;
                case "B":
                    cursorBackward();
                    break;
                case "I":
                    insertCar();
                    break;
                case "R":
                    removeCar();
                    break;
                case "L":
                    setProductLoad();
                    break;
                case "S":
                    search();
                    break;
                case "T":
                    displayTrain();
                    break;
                case "M":
                    displayManifest();
                    break;
                case "D":
                    removeDangerousCars();
                    break;
                case "Q":
                    isDone = true;
                    System.out.println("Program terminating successfully...");
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }


    }

    /**
     * Invokes cursorForward() method in TrainLinkedList Class
     */
    private static void cursorForward(){
        trainLinkedList.cursorForward();
    }

    /**
     * Invokes cursorBackward() method in TrainLinkedList Class
     */
    private static void cursorBackward(){
        trainLinkedList.cursorBackward();
    }

    /**
     * Invokes insertCar() method in TrainLinkedList Class
     */
    private static void insertCar(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter car length in meters:");
            Double carLength = sc.nextDouble();
            System.out.print("Enter car weight in tons:");
            Double carWeight= sc.nextDouble();
            TrainCar trainCar = new TrainCar(carLength, carWeight);
            if(trainCar.isEmpty())
                trainCar.setLoad(new ProductLoad());
            trainLinkedList.insertAfterCursor(trainCar);
            System.out.println("New train car "+carLength+" meters "+carWeight+" tons inserted into train.");
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }

    }

    /**
     * Invokes removeCar() method in the TrainLinkedList class
     */
    private static void removeCar(){
        TrainCar thisCar = trainLinkedList.removeCursor();
        ProductLoad load = thisCar.getLoad();
        System.out.println("Car successfully unlinked. The following load has been removed from the train:");
        System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight(t)", "Value($)", "Dangerous"));
        System.out.println("==================================================");
        //System.out.println(String.format("%8s%16s%14s%12s", load.getName(), load.getWeight(), load.getValue(), (load.isDangerous()? "YES":"NO")));*/
        load.removedProductPrint();
    }

    /**
     * Invokes setProductLoad() method in the TrainLinkedList class
     */
    private static void setProductLoad(){
        Scanner sc = new Scanner(System.in);
        /*Enter produce name: Corn
        Enter product weight in tons: 100.0
        Enter product value in dollars: 15440
        Enter is product dangerous? (y/n): n*/
        boolean dangerous = false;
        String name = null;
        Double weight = null;
        int dollarVal = 0;
        try {
            dangerous = false;
            System.out.print("Enter product name:");
            name = sc.next();
            System.out.print("Enter product weight in tons:");
            weight = sc.nextDouble();
            System.out.print("Enter product value in dollars:");
            dollarVal = sc.nextInt();
            System.out.print("Enter is product dangerous? (y/n):");
            String isDangerous = sc.next();
            if(isDangerous.equalsIgnoreCase("y")){
                dangerous = true;
            } else if(isDangerous.equalsIgnoreCase("n")){
                dangerous = false;
            }
        } catch (Exception e) {
            System.out.println("Invalid Input. Try Again");
            return;
        }
        TrainCar thisCar = trainLinkedList.getCursor().getCar();
        ProductLoad load = new ProductLoad(name, weight, dollarVal, dangerous);

        trainLinkedList.incrementLoadMetrics(thisCar, load);
        thisCar.setLoad(load);
        System.out.println(load.getWeight()+" tons of "+ load.getName() + " added to the current car");
    }

    /**
     * Invokes search() method in the TrainLinkedList class
     */
    private static void search(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product Name:");
        String name = sc.next();
        trainLinkedList.findProduct(name);
    }

    /**
     * Prints all the metrics of the train in a neatly formatted way
     */
    private static void displayTrain(){
        Double value = Double.valueOf(decimalFormat.format(trainLinkedList.getValue()));
        Double weight = Double.valueOf(decimalFormat.format(trainLinkedList.getWeight()));
        //System.out.println("Train: "+ trainLinkedList.size()+" cars, "+ trainLinkedList.getLength()+" meters, "+ trainLinkedList.getWeight()+" tons, $"+ trainLinkedList.getValue()+" value, "+(trainLinkedList.isDangerous()?"DANGEROUS":"not dangerous")+".");
        System.out.println("Train: "+ trainLinkedList.size()+" cars, "+ trainLinkedList.getLength()+" meters, "+ weight+" tons, $"+ + value+", "+(trainLinkedList.isDangerous()?"DANGEROUS":"not dangerous")+".");

    }

    /**
     * Invokes printManifest() method in the TrainLinkedList class
     */
    private static void displayManifest(){
        trainLinkedList.printManifest();
    }

    /**
     * Invokes removeDangerousCars() method in the TrainLinkedList class
     */
    private static void removeDangerousCars(){
        System.out.println(String.format("%8s%16s%14s%12s", "Name", "Weight(t)", "Value($)", "Dangerous"));
        System.out.println("==================================================");
        trainLinkedList.removeDangerousCars();
        System.out.println("Dangerous cars successfully removed from the train.");
    }


}
