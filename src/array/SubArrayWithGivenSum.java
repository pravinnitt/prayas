package array;

public class SubArrayWithGivenSum {

    public static int[] input ={1 ,2 ,3, 7,-1, 5,4,-3};
    public static int sum = 12;
    public static void main(String[] args){

        for (int i=0; i<input.length;i++) {
            int localsum =0;
            for (int j = i; j < input.length; j++) {
                localsum+= input[j];
                if(localsum==sum) {
                    System.out.println("sum " + sum +" found at (" + i + ", "+ j + " )");
                }
            }
        }
    }
}
