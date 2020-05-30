import java.util.List;
import java.util.ArrayList;

/**
 * File Name: Triangle.java
 * Author: Abilash Bodapati
 * Date: 05/29/2020
 * Version: 20200529
 * 
 * Description: 
 *      This is a program that takes two arguments and prints out a triangle output 
 * mentioned in the README file. Runtime Complexity of this program is O(n^2);
 */

public class Triangle {

    /**
     * 
     * This is a helper method that will print out the triangle list in visually appealing format.
     * 
     * @param row
     * @param rowNum
     * @param numOfRows
     */
    public static void prettyPrint(List<Integer> row, int rowNum, int numOfRows) {
        // A string with the number of spaces we need before and after the row

        // Start with the empty spring and concatenate based on the row 
        String spaces = "";
        for (int i = numOfRows; i >= rowNum; i--){
            spaces += "\t";
        }
        
        // Print the Space before the row
        System.out.print(spaces);

        // Print out the row with the a tab preceding the value
        for (int value : row){
            System.out.print("\t");
            System.out.print(value);
        }

        // Print the Space after the row
        System.out.print(spaces+"\n");

    }

    /**
     * This is a helper method to reverse the subList that we provide from the previous row.
     * 
     * @param subList
     * @return
     */
    public static List<Integer> reverseList(List<Integer> subList) {
        // ArrayList constructor to store the reversed sublist
        List<Integer> reverseSubList = new ArrayList<>();

        // For loop to reverse the subList
        for (int subListSize = subList.size()-1; subListSize >= 0; subListSize--){
            reverseSubList.add(subList.get(subListSize));
        }

        return reverseSubList;
    }

    /**
     * 
     * This is a main helper method that generates all the values in every row for the triangle.
     * Worst case running time:
     *      O(n^2) 
     * 
     * Prerequisite: 
     *      num1 <= num2 => True
     * 
     * Assumption of the Result:
     *      You can assume that the Lists of Integer in the triangle list are in order.
     *      All the values of each row are supposed to have the right values.
     * 
     * 
     * @param num1
     * @param num2
     * @return List<List<Integer>> resultList
     */
    public static List<List<Integer>> generateTriangle(int num1, int num2){
        // Create a new List of List object.
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();

        // Number of rows we can expect using the params
        int numRows = (num2 - num1) + 1;

        // Base case; First row is always num1.
        resultList.add(new ArrayList<>());
        resultList.get(0).add(num1);

        // For loop to create each individual row and add them to the list from 2nd row onwards
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = resultList.get(rowNum-1);

            // The first row element is always num1.
            row.add(num1);

            // From the Previous row we ge the sublist from 0th element up until but excluding the midpoint
            List<Integer> subList = prevRow.subList(1, rowNum);
            // Add the sublist following the first element of the row.
            row.addAll(subList);
            // Add the new midpoint value for the new row.
            row.add(row.get(row.size()-1)+1);
            // Add the reversed sublist after the midpoint
            row.addAll(reverseList(subList));

            // The last row element is always num1.
            row.add(num1);

            // Add the final version of the row into the resulting list
            resultList.add(row);
        }

        return resultList;
    }

    /**
     * Description:
     *      Main Method (Driver file)
     *      Assumption made that the arguments are strictly Integers.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // check if length of args array is greater than 1 
        if ((args.length > 1) && 
            (Integer.parseInt(args[0]) >= 0) && 
            (Integer.parseInt(args[1]) >= 0)) {

            // Min and Max variables
            int minValue = Math.min(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            int maxValue = Math.max(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

            // Print out input values entered by the user.
            System.out.println(" Min Value: "+ minValue + " and Max Value: " + maxValue+"\n");

            // Call a static method to create our triangle array
            List<List<Integer>> triangleList = generateTriangle(minValue, maxValue);

            // Call the pretty print method to print the values in a visually appealing form
            for (int i = 0; i < triangleList.size(); i++){
                prettyPrint(triangleList.get(i), i+1, triangleList.size());
            }
            System.out.println();

        } 
        else{
            // Print out an Error Messages
            System.err.println("Argument Format Invalid!");

            if (args.length <= 1){ // If there are not enough arguments
                String stringResult = (args.length == 0) ? "no arguments were passed in." 
                                                    : "only 1 argument was passed in.";
                System.err.println("You should provide 2 arguments. Instead " + stringResult);

            }else if((Integer.parseInt(args[0]) < 0) || 
                    (Integer.parseInt(args[1]) < 0)){ // If either one of the arguments are negative
                        String stringResult = (Integer.parseInt(args[0]) < 0) ? "Num1" : "Num2";
                        System.err.println(stringResult + " is negative!");
            }
            System.err.println("Please Enter a Valid Format!");

        }
    }
}