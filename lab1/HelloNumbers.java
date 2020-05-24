/** it prints out the cumulative sum of the integers from 0 to 9.
 *  For example, your output should start with 0 1 3 6 10... and should end with 45. */  

 public class HelloNumbers {
    public static void main(String[] args) {
        int i = 0;
        int sum = i;

        while (i < 10) {
            sum = sum + i;
            i++;
            System.out.print(sum + " ");
        }
        System.out.println("");

        System.out.println(5+10);
    }
}
