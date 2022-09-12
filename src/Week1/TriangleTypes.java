package Week1;

import java.util.Scanner;

public class TriangleTypes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        double input1 = sc.nextDouble();
        double input2 = sc.nextDouble();
        double input3 = sc.nextDouble();

        if(input1 > input2 && input2 > input3 || input1 >= input2 && input2 > input3 || input1 > input2 && input2 >= input3 || input1 > input2 && input2 <= input3 || input1 == input2 && input2 == input3){
            double a = input1;
            double b = input2;
            double c = input3;
            if(a >= b + c){
                System.out.println("NAO FORMA TRIANGULO");
                return;
            }
            if((a * a) == (b * b) + (c * c)){
                System.out.println("TRIANGULO RETANGULO");
            }
            if((a * a) > (b * b) + (c * c)){
                System.out.println("TRIANGULO OBTUSANGULO");
            }
            if((a * a) < (b * b) + (c * c)){
                System.out.println("TRIANGULO ACUTANGULO");
            }
            if(a == b && b == c){
                System.out.println("TRIANGULO EQUILATERO");
            }
            if(a == b && b > c || a == b && b < c || a == c && c < b || a == c && c > b || b == c && c < a || b ==c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }else if(input2 > input1 && input1 > input3 || input2 >= input1 && input1 > input3 || input2 > input1 && input1 >= input3 || input2 >= input1 && input2 > input3 || input2 > input1 && input2 >= input3 || input2 == input1 && input1 == input3){
            double a = input2;
            double b = input3;
            double c = input1;
            if(a >= b + c){
                System.out.println("NAO FORMA TRIANGULO");
                return;
            }
            if((a * a) == (b * b) + (c * c)){
                System.out.println("TRIANGULO RETANGULO");
            }
            if((a * a) > (b * b) + (c * c)){
                System.out.println("TRIANGULO OBTUSANGULO");
            }
            if((a * a) < (b * b) + (c * c)){
                System.out.println("TRIANGULO ACUTANGULO");
            }
            if(a == b && b == c){
                System.out.println("TRIANGULO EQUILATERO");
            }
            if(a == b && b > c || a == b && b < c || a == c && c < b || a == c && c > b || b == c && c < a || b ==c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }else if(input3 > input1 && input1 > input2 || input3 >= input1 && input1 > input2 || input3 > input1 && input1 >= input2|| input3 >= input1 && input3 > input2 || input3 > input1 && input1 >= input2 || input3 == input1 && input1 == input2){
            double a = input3;
            double b = input2;
            double c = input1;
            if(a >= b + c){
                System.out.println("NAO FORMA TRIANGULO");
                return;
            }
            if((a * a) == (b * b) + (c * c)){
                System.out.println("TRIANGULO RETANGULO");
            }
            if((a * a) > (b * b) + (c * c)){
                System.out.println("TRIANGULO OBTUSANGULO");
            }
            if((a * a) < (b * b) + (c * c)){
                System.out.println("TRIANGULO ACUTANGULO");
            }
            if(a == b && b == c){
                System.out.println("TRIANGULO EQUILATERO");
            }
            if(a == b && b > c || a == b && b < c || a == c && c < b || a == c && c > b || b == c && c < a || b ==c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }
    }
}
