package com.ricarditodev.figuras;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double area;
        double perimetro;
        double largo;
        double ancho;
        double radio;
        double longitud;
        double base;
        double altura;
        final double PI = 3.14159265358979323846;
        String opcion;

        System.out.print("Escoge una opción: \n1 o a) Rectangulo \n2 o b) Circunferencia \n3 o c) Cuadrado \n4 o d) Triangulo \n0 o s) Para salir \n>> ");

        opcion = entrada.nextLine();

        if (opcion.equals("1") || opcion.equals("a") || opcion.equals("A")) {
            System.out.print("Ingresa el largo: ");
            largo = entrada.nextDouble();
            entrada.nextLine();

            System.out.print("Ingresa el ancho: ");
            ancho = entrada.nextDouble();
            entrada.nextLine();

            perimetro = largo * 2 + ancho * 2;
            area = largo * ancho;

            System.out.println("Perimetro: " + perimetro + "\nArea: " + area);
        } else if (opcion.equals("2") || opcion.equals("b") || opcion.equals("B")) {
            System.out.print("Ingresa el radio: ");
            radio = entrada.nextDouble();
            entrada.nextLine();

            perimetro = radio * 2 * PI;
            area = radio * radio * PI;

            System.out.println("Perimetro: " + perimetro + "\nArea: " + area);
        } else if (opcion.equals("3") || opcion.equals("c") || opcion.equals("C")) {
            System.out.print("Ingresa la longitud: ");
            longitud = entrada.nextDouble();
            entrada.nextLine();

            perimetro = longitud * 4;
            area = longitud * longitud;

            System.out.println("Perimetro: " + perimetro + "\nArea: " + area);
        } else if (opcion.equals("4") || opcion.equals("d") || opcion.equals("D")) {
            System.out.print("Ingresa la base: ");
            base = entrada.nextDouble();
            entrada.nextLine();

            System.out.print("Ingresa la altura: ");
            altura = entrada.nextDouble();
            entrada.nextLine();

            System.out.print("Ingresa la longitud del primer lado: ");
            double lado1 = entrada.nextDouble();
            entrada.nextLine();

            System.out.print("Ingresa la longitud del segundo lado: ");
            double lado2 = entrada.nextDouble();
            entrada.nextLine();

            System.out.print("Ingresa la longitud del tercer lado: ");
            double lado3 = entrada.nextDouble();
            entrada.nextLine();

            area = (altura * base) / 2;
            perimetro = lado1 + lado2 + lado3;

            System.out.println("Perimetro: " + perimetro + "\nArea: " + area);
        } else if (opcion.equals("0") || opcion.equals("s") || opcion.equals("S")) {
            System.out.print("Presione ENTER para salir");
            entrada.nextLine();
        } else {
            System.out.println("ERROR");
        }
    }
}
