
/**
 * Write a description of class ArrayDemo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ArrayDemo
{
    
    public static void main(String[] args) {
        int[] numbers;
        String[] names;
        double[] examResults;
        
        numbers = new int[10];
        names = new String[10];
        examResults = new double[10];
        
        System.out.println(numbers.length);
        System.out.println(names.length);
        System.out.println(examResults.length);
        
        System.out.println(numbers[0]);
        System.out.println(names[1]);
        System.out.println(examResults[2]);
    }
}
