package week1;

import java.util.Scanner;

public class GameTimeWithMinutes1047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int startHour, startMinute, endHour, endMinute, hour = 0, minute = 0;
        startHour = sc.nextInt();
        startMinute = sc.nextInt();
        endHour = sc.nextInt();
        endMinute = sc.nextInt();
        if (startHour >= 0 && startHour <= 24 && endHour >= 0
                && endHour <= 24 && startMinute >= 0 && startMinute <= 60
                && endMinute >= 0 && endMinute <= 60) {
            if (endMinute > startMinute) {
                minute = endMinute - startMinute;
            } else if (endMinute < startMinute) {
                minute = endMinute - startMinute + 60;
                endHour = endHour - 1;
            }
            if (endHour >= startHour) {
                hour = endHour - startHour;
            } else if (endHour < startHour) {
                hour = endHour - startHour + 24;
            }
            if (hour == 0 && minute == 0) {
                hour = 24;
            }
        }
        System.out.println("O JOGO DUROU " + hour + " HORA(S) E " + minute + " MINUTO(S)");
    }
}
