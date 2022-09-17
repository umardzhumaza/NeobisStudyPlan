

import java.util.Scanner;

public class LogicalSequence1145 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int from = sc.nextInt();
        int to = sc.nextInt();

        if (from > 1 && from < 20 && to > from && to < 100000) {
            for (int i = 1; i <= to; i++) {
                System.out.print(i);
                if(i % from == 0){
                    System.out.println("");
                }else{
                    System.out.print(" ");
                }
            }
            if(to % from != 0){
                System.out.println("");
            }
        }
    }
}
