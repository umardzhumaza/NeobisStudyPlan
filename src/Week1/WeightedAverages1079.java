package Week1;

import java.util.Scanner;

public class WeightedAverages1079 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCount = sc.nextInt();

        float firstValue, secondValue, thirdValue, averages;
        for (int i = 0; i < testCount; i++) {
            firstValue = sc.nextFloat();
            secondValue = sc.nextFloat();
            thirdValue = sc.nextFloat();
            averages = ((firstValue * 2) + (secondValue * 3) + (thirdValue * 5)) / 10;
            System.out.printf("%.1f\n", averages);
        }
    }
}
