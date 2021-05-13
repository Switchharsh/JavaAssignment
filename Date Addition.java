package com.learningjava;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner day = new Scanner(System.in);
        System.out.println("Insert the number of days you wish to know the date from today");
        int a =  day.nextInt();
        System.out.println("date from today " +(LocalDate.now().plusDays(a)));

    }
}
