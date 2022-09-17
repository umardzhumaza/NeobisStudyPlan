

import java.util.Scanner;

public class Banknotes1018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println(input);
        System.out.printf(input/100 + " nota(s) de R$ 100,00\n");
        input%=100;
        System.out.printf(input/50 + " nota(s) de R$ 50,00\n");
        input%=50;
        System.out.printf(input/20 + " nota(s) de R$ 20,00\n");
        input%=20;
        System.out.printf(input/10 + " nota(s) de R$ 10,00\n");
        input%=10;
        System.out.printf(input/5 + " nota(s) de R$ 5,00\n");
        input%=5;
        System.out.printf(input/2 + " nota(s) de R$ 2,00\n");
        input%=2;
        System.out.printf(input/1 + " nota(s) de R$ 1,00\n");
    }
}
