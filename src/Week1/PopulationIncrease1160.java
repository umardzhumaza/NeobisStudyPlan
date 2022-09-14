package Week1;

import java.util.Scanner;

public class PopulationIncrease1160 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int pa, pb, pa1, pb1, years = 0;
        float g1, g2;
        for (int i = 0; i < count; i++) {
            pa = sc.nextInt();
            pb = sc.nextInt();
            g1 = sc.nextFloat();
            g2 = sc.nextFloat();

            pa1 = pa;
            pb1 = pb;
            while (pa1 <= pb1){
                pa1 += (g1 / 100 * pa1);
                pb1 += (g2 / 100 * pb1);
                if(years>100){
                    break;
                }
                years++;
            }

            if(years > 100) {
                System.out.println("Mais de 1 seculo.");
            }else {
                System.out.println(years + " anos.");
            }
            pa1 = 0;
            pb1 = 0;
            years = 0;
        }
    }
}
