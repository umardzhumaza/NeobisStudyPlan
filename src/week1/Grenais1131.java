package week1;

import java.util.Scanner;

public class Grenais1131 {
    public static Scanner sc = new Scanner(System.in);
    public static int inter, gremio, next, grenais, interWinCount, gremioWinCounter, empates;
    public static void main(String[] args) {
        while (next != 2){
            match();
        }
        System.out.println(grenais + " grenais");
        System.out.println("Inter:" + interWinCount);
        System.out.println("Gremio:" + gremioWinCounter);
        System.out.println("Empates:" + empates);
        if(interWinCount > gremioWinCounter){
            System.out.println("Inter venceu mais");
        }else if(gremioWinCounter > interWinCount){
            System.out.println("Gremio venceu mais");
        }else{
            System.out.println("Não houve vencedor");
        }
    }
    public static void match(){
        grenais++;
        inter = sc.nextInt();
        gremio = sc.nextInt();
        if(inter>gremio){
            interWinCount++;
        }else if(gremio > inter){
            gremioWinCounter++;
        }else {
            empates++;
        }
        System.out.println("Novo grenal (1-sim 2-nao)");
        next = sc.nextInt();
    }
}
