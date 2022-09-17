

public class SequenceIJ1098 {
    public static void main(String[] args) {
        for (double i = 0; i <= 2; i += 0.2) {
            for (double j = 1; j < 4; j++) {
                if(i==0 || i==1 || i>1.8){
                    System.out.printf("I=%.0f", i);
                    System.out.printf(" J=%.0f\n", (j + i));
                }else {
                    System.out.printf("I=%.1f", i);
                    System.out.printf(" J=%.1f", (j + i));
                    if ((j + i) == 5.0) {
                        return;
                    } else
                        System.out.println("");
                }
            }
        }
    }
}
