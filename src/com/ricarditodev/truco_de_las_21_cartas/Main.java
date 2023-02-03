package com.ricarditodev.truco_de_las_21_cartas;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String opcion;
        int secuencia = 1;
        int j;
        int k;

        final short MAX_TARJETAS_GRUPO = 7; //Tarjetas por grupo
        final short MAX_GRUPOS = 3; //Cantidad de grupos
        final short MAX_TARJETAS = MAX_TARJETAS_GRUPO * MAX_GRUPOS; //Total de tarjetas.
        final short MIN_TARJETA_VALOR = 'A'; //Tarjeta incial, en este caso letra A.
        final short MAX_TARJETA_VALOR = (char) (MAX_TARJETAS + (int) ('A') - 1); //Tarjeta final.

        char[] grupo1 = new char[MAX_TARJETAS_GRUPO];
        char[] grupo2 = new char[MAX_TARJETAS_GRUPO];
        char[] grupo3 = new char[MAX_TARJETAS_GRUPO];
        char[] deck = new char[MAX_TARJETAS];

        char[] copia1 = new char[grupo1.length];
        char[] copia2 = new char[grupo2.length];
        char[] copia3 = new char[grupo3.length];

        var generador = new Random();
        int indice;

        //generar letras al azar sin repetir en el array deck
        // Generating a random number and checking if it is already in the array. If it is, it generates a new number.
        for (int i = 0; i < deck.length; i++) {
            indice = generador.nextInt((int) MAX_TARJETA_VALOR - (int) MIN_TARJETA_VALOR + 1) + (int) (MIN_TARJETA_VALOR);

            int l = 0;
            while (l < i) {
                if (deck[l] == indice) {
                    l = 0;
                    indice = generador.nextInt((int) MAX_TARJETA_VALOR - (int) MIN_TARJETA_VALOR + 1) + (int) (MIN_TARJETA_VALOR);
                } else {
                    l++;
                }
            }

            deck[i] = (char) indice;
        }

        //asignar a los 3 grupos sus 7 cartas
        // Creating three arrays of 7 elements each.
        for (int i = 0, m = deck.length - 1; i < deck.length; i++, m--) {
            if (i < 7) {
                grupo1[i] = deck[m];
            } else if (i >= 7 && i < 14) {
                grupo2[i - 7] = deck[m];
            } else if (i >= 14) {
                grupo3[i - 14] = deck[m];
            }
        }

        // Printing the three arrays in a single line.
        System.out.println("Haremos 3 secuencias. Empecemos...");
        System.out.println("Secuencia 1: ");
        System.out.println();

        //mostrar las cartas al usuario
        for (int i = 0; i < 7; i++) {
            System.out.print(grupo1[i] + "\t");

            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(grupo2[i] + "\t");

            if ((i + -1) % 3 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(grupo3[i] + "\t");

            if ((i + -3) % 3 == 0) {
                System.out.println();
            }
        }


        //pedimos al usuario una opcion, la opcion ingresada la guardamos como un string, luego esa string la convertimos a un caracter de la tabla ASCII
        System.out.print("En qué grupo está tu tarjeta [1,2,3]: ");
        opcion = entrada.nextLine();
        char letra = opcion.charAt(0);

        //49 es la opcion 1, 50 es la opcion 2, 51 es la opcion 3
        while (letra != 49 && letra!= 50 && letra!= 51) {
            System.out.print("ERROR - Opción incorrecta, ingresa una opción válida [1,2,3]: ");
            opcion = entrada.nextLine();
            letra = opcion.charAt(0);
        }

        //recoger las cartas al mazo
        switch (letra) {
            case 49:
                if (letra == 49) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 49) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 49) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia2. length - 1; i < 7; i++, m--) {
                    deck[i] = copia2[m];
                }

                j = 0;
                for (int i = 7, m = copia1.length - 1; i < 14; i++, m--) {
                    deck[i] = copia1[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia3.length - 1; i < 21; i++, m--) {
                    deck[i] = copia3[m];
                    k++;
                }
                break;
            case 50:
                if (letra == 50) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 50) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 50) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia1.length - 1; i < 7; i++, m--) {
                    deck[i] = copia1[m];
                }

                j = 0;
                for (int i = 7, m = copia2.length - 1; i < 14; i++, m--) {
                    deck[i] = copia2[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia3.length - 1; i < 21; i++, m--) {
                    deck[i] = copia3[m];
                    k++;
                }
                break;
            case 51:
                if (letra == 51) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 51) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 51) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia2.length - 1; i < 7; i++, m--) {
                    deck[i] = copia2[m];
                }

                j = 0;
                for (int i = 7, m = copia3.length - 1; i < 14; i++, m--) {
                    deck[i] = copia3[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia1.length - 1; i < 21; i++, m--) {
                    deck[i] = copia1[m];
                    k++;
                }
                break;
        }


        //asignar a los 3 grupos sus 7 cartas
        // Creating three arrays of 7 elements each.
        for (int i = 0, m = deck.length - 1; i < deck.length; i++, m--) {
            if (i < 7) {
                grupo1[i] = deck[m];
            } else if (i >= 7 && i < 14) {
                grupo2[i - 7] = deck[m];
            } else if (i >= 14) {
                grupo3[i - 14] = deck[m];
            }
        }

        // Printing the three arrays in a single line.
        System.out.println("Secuencia 2: ");
        System.out.println();

        //mostrar las cartas al usuario
        for (int i = 0; i < 7; i++) {
            System.out.print(grupo1[i] + "\t");

            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(grupo2[i] + "\t");

            if ((i + -1) % 3 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(grupo3[i] + "\t");

            if ((i + -3) % 3 == 0) {
                System.out.println();
            }
        }

        //pedimos al usuario una opcion, la opcion ingresada la guardamos como un string, luego esa string la convertimos a un caracter de la tabla ASCII
        System.out.print("En qué grupo está tu tarjeta [1,2,3]: ");
        opcion = entrada.nextLine();
        letra = opcion.charAt(0);

        //49 es la opcion 1, 50 es la opcion 2, 51 es la opcion 3
        while (letra != 49 && letra!= 50 && letra!= 51) {
            System.out.print("ERROR - Opción incorrecta, ingresa una opción válida [1,2,3]: ");
            opcion = entrada.nextLine();
            letra = opcion.charAt(0);
        }

        //recoger las cartas al mazo
        switch (letra) {
            case 49:
                if (letra == 49) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 49) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 49) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia2. length - 1; i < 7; i++, m--) {
                    deck[i] = copia2[m];
                }

                j = 0;
                for (int i = 7, m = copia1.length - 1; i < 14; i++, m--) {
                    deck[i] = copia1[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia3.length - 1; i < 21; i++, m--) {
                    deck[i] = copia3[m];
                    k++;
                }
                break;
            case 50:
                if (letra == 50) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 50) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 50) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia1.length - 1; i < 7; i++, m--) {
                    deck[i] = copia1[m];
                }

                j = 0;
                for (int i = 7, m = copia2.length - 1; i < 14; i++, m--) {
                    deck[i] = copia2[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia3.length - 1; i < 21; i++, m--) {
                    deck[i] = copia3[m];
                    k++;
                }
                break;
            case 51:
                if (letra == 51) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 51) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 51) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia2.length - 1; i < 7; i++, m--) {
                    deck[i] = copia2[m];
                }

                j = 0;
                for (int i = 7, m = copia3.length - 1; i < 14; i++, m--) {
                    deck[i] = copia3[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia1.length - 1; i < 21; i++, m--) {
                    deck[i] = copia1[m];
                    k++;
                }
                break;
        }

        //asignar a los 3 grupos sus 7 cartas
        // Creating three arrays of 7 elements each.
        for (int i = 0, m = deck.length - 1; i < deck.length; i++, m--) {
            if (i < 7) {
                grupo1[i] = deck[m];
            } else if (i >= 7 && i < 14) {
                grupo2[i - 7] = deck[m];
            } else if (i >= 14) {
                grupo3[i - 14] = deck[m];
            }
        }

        // Printing the three arrays in a single line.
        System.out.println("Secuencia 3: ");
        System.out.println();

        //mostrar las cartas al usuario
        for (int i = 0; i < 7; i++) {
            System.out.print(grupo1[i] + "\t");

            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(grupo2[i] + "\t");

            if ((i + -1) % 3 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < 7; i++) {
            System.out.print(grupo3[i] + "\t");

            if ((i + -3) % 3 == 0) {
                System.out.println();
            }
        }


        //pedimos al usuario una opcion, la opcion ingresada la guardamos como un string, luego esa string la convertimos a un caracter de la tabla ASCII
        System.out.print("En qué grupo está tu tarjeta [1,2,3]: ");
        opcion = entrada.nextLine();
        letra = opcion.charAt(0);

        //49 es la opcion 1, 50 es la opcion 2, 51 es la opcion 3
        while (letra != 49 && letra!= 50 && letra!= 51) {
            System.out.print("ERROR - Opción incorrecta, ingresa una opción válida [1,2,3]: ");
            opcion = entrada.nextLine();
            letra = opcion.charAt(0);
        }

        //recoger las cartas al mazo
        switch (letra) {
            case 49:
                if (letra == 49) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 49) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 49) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia2. length - 1; i < 7; i++, m--) {
                    deck[i] = copia2[m];
                }

                j = 0;
                for (int i = 7, m = copia1.length - 1; i < 14; i++, m--) {
                    deck[i] = copia1[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia3.length - 1; i < 21; i++, m--) {
                    deck[i] = copia3[m];
                    k++;
                }
                break;
            case 50:
                if (letra == 50) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 50) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 50) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia1.length - 1; i < 7; i++, m--) {
                    deck[i] = copia1[m];
                }

                j = 0;
                for (int i = 7, m = copia2.length - 1; i < 14; i++, m--) {
                    deck[i] = copia2[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia3.length - 1; i < 21; i++, m--) {
                    deck[i] = copia3[m];
                    k++;
                }
                break;
            case 51:
                if (letra == 51) {
                    int grupoSeleccionado = 2;

                    for (int i = 0; i < 7; i++) {
                        copia2[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 51) {
                    int grupoSeleccionado = 1;

                    for (int i = 0; i < 7; i++) {
                        copia3[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                if (letra == 51) {
                    int grupoSeleccionado = 3;

                    for (int i = 0; i < 7; i++) {
                        copia1[i] = deck[i * 3 + (grupoSeleccionado - 1)];
                    }
                }

                for (int i = 0, m = copia2.length - 1; i < 7; i++, m--) {
                    deck[i] = copia2[m];
                }

                j = 0;
                for (int i = 7, m = copia3.length - 1; i < 14; i++, m--) {
                    deck[i] = copia3[m];
                    j++;
                }

                k = 0;
                for (int i = 14, m = copia1.length - 1; i < 21; i++, m--) {
                    deck[i] = copia1[m];
                    k++;
                }
                break;
        }

        //asignar a los 3 grupos sus 7 cartas
        // Creating three arrays of 7 elements each.
        for (int i = 0, m = deck.length - 1; i < deck.length; i++, m--) {
            if (i < 7) {
                grupo1[i] = deck[m];
            } else if (i >= 7 && i < 14) {
                grupo2[i - 7] = deck[m];
            } else if (i >= 14) {
                grupo3[i - 14] = deck[m];
            }
        }

        char[] colunmMedio = new char[7];
        int column = 2;

        for (int i = 0; i < 7; i++) {
            colunmMedio[i] = deck[i * 3 + (column - 1)];
        }

        System.out.println("Obviamente elegiste la " + colunmMedio[3]);

    }
}
