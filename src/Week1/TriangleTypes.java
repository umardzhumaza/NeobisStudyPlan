package Week1;

import java.util.Scanner;

public class TriangleTypes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int input1 = sc.nextInt();
        int input2 = sc.nextInt();
        int input3 = sc.nextInt();

//        если A ≥ B + C, напишите сообщение: NAO FORMA TRIANGULO
//        если A2 = B2 + C2, напишите сообщение: TRIANGULO RETANGULO
//        если A2 > B2 + C2, напишите сообщение: TRIANGULO OBTUSANGULO
//        если A2 < B2 + C2, напишите сообщение: TRIANGULO ACUTANGULO
//        если три стороны имеют одинаковый размер, напишите сообщение: TRIANGULO EQUILATERO
//        если только две стороны совпадают, а третья отличается, напишите сообщение: TRIANGULO ISOSCELES
        if(input1 > input2 && input2 > input3 || input1 >= input2 && input2 > input3 || input1 > input2 && input2 >= input3 || input1 >= input2 && input2 < input3 || input1 > input2 && input2 <= input3 || input1 == input2 && input2 == input3){
            int a = input1;
            int b = input2;
            int c = input3;
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
            if(a == b && b > c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(b == c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(a == b && b < c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(b == c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(a == c && b < c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(c == a && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }else if(input2 > input1 && input1 > input3 || input2 >= input1 && input1 > input3 || input2 > input1 && input1 >= input3 || input2 >= input1 && input2 > input3 || input2 > input1 && input2 >= input3 || input2 == input1 && input1 == input3){
            int a = input2;
            int b = input3;
            int c = input1;
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
            if(a == b && b > c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(b == c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(a == b && b < c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(b == c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(a == c && b < c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(c == a && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }else if(input3 > input1 && input1 > input2 || input3 >= input1 && input1 > input2 || input3 > input1 && input1 >= input2|| input3 >= input1 && input3 > input2 || input3 > input1 && input1 >= input2 || input3 == input1 && input1 == input2){
            int a = input3;
            int b = input2;
            int c = input1;
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
            if(a == b && b > c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(b == c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(a == b && b < c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(b == c && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(a == c && b < c){
                System.out.println("TRIANGULO ISOSCELES");
            }
            if(c == a && c > a){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }
    }
}
