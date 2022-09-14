package week1;

import java.util.Scanner;
//NOTAS:
//        0 nota(s) de R$ 100.00
//        0 nota(s) de R$ 50.00
//        0 nota(s) de R$ 20.00
//        0 nota(s) de R$ 10.00
//        0 nota(s) de R$ 5.00
//        2 nota(s) de R$ 2.00
//        MOEDAS:
//        0 moeda(s) de R$ 1.00
//        0 moeda(s) de R$ 0.50
//        0 moeda(s) de R$ 0.25
//        0 moeda(s) de R$ 0.10
//        0 moeda(s) de R$ 0.05
//        0 moeda(s) de R$ 0.01
public class BanknotesAndCoins1021 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double input = sc.nextDouble();
        String numberA = String.valueOf(input);
        numberA = numberA.substring ( 0, numberA.indexOf ( "." ));
        int value = Integer.parseInt(numberA);
        System.out.println("NOTAS:");
        System.out.printf(value/100 + " nota(s) de R$ 100.00\n");
        value%=100;
        System.out.printf(value/50 + " nota(s) de R$ 50.00\n");
        value%=50;
        System.out.printf(value/20 + " nota(s) de R$ 20.00\n");
        value%=20;
        System.out.printf(value/10 + " nota(s) de R$ 10.00\n");
        value%=10;
        System.out.printf(value/5 + " nota(s) de R$ 5.00\n");
        value%=5;
        System.out.printf(value/2 + " nota(s) de R$ 2.00\n");
        value%=2;
        String numberD = String.valueOf(input);
        numberD = numberD.substring ( numberD.indexOf ( "." ) + 1 );
        int temp = Integer.parseInt(numberD);
        System.out.println("MOEDAS:");
        System.out.printf(value/1 + " moeda(s) de R$ 1.00\n");
        System.out.printf(temp/50 + " moeda(s) de R$ 0.50\n");
        temp %= 50;
        System.out.printf(temp/25 + " moeda(s) de R$ 0.25\n");
        temp %= 25;
        System.out.printf(temp/10 + " moeda(s) de R$ 0.10\n");
        temp %= 10;
        System.out.printf(temp/5 + " moeda(s) de R$ 0.05\n");
        temp %= 5;
        System.out.printf(temp/1 + " moeda(s) de R$ 0.01\n");
    }
}
