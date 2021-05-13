package com.learningjava;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Enter Numbers You Wish To Add");
        System.out.println("Enter 'Done' if you wish to perform the addition");

        // initializing a and b as integers and d as the string
        int a = 0, b = 0;
        String d = "Done";
        // asking for input
        Scanner c = new Scanner(System.in);
        while (c.hasNextInt()) {
            a = c.nextInt();
            b += a;
            System.out.println("a is:" +a);
            System.out.println("b is:" +b);
        }
        if (c.hasNext()) {

            if(c.next().equalsIgnoreCase(d)) {

                System.out.println("The sum is: " + b);
            }
            System.out.println("Error input, need \"Done\" to add");
        }
        }
    }
