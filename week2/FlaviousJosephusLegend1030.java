import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlaviousJosephusLegend1030 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCount = sc.nextInt();
        int test = 0, steps, peoples, tempVariable = 0, value = 0;
        while (testCount != 0){
            peoples = sc.nextInt();
            test++;
            List<Integer> list = new ArrayList<>(peoples);
            List<Integer> temp = new ArrayList<>(peoples);
            for (int i = 0; i < peoples; i++) {
                list.add(i, i+1);
                temp.add(i, i+1);
            }
            steps = sc.nextInt();
            for (int i = 0; ; i++) {

                if(list.size() < tempVariable+steps){
                    i = -1;
                    steps = list.size() - tempVariable - steps;
                    steps = Math.abs(steps) - 1;

                }
                if(list.size() == tempVariable)
                    i=0;
                if (list.size() == 1) {
                    System.out.println("Case " + test + ": " + value);
                    break;
                }
                tempVariable = list.indexOf(list.get(i + (steps - 1)));
                value = list.get(0);
                list.remove(tempVariable);
                i = tempVariable - 1;
            }
            testCount--;
        }
    }
}
