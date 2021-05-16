package com.learningjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        // asking user to input the path of the file with file name
        System.out.println("Enter the path of the file with file name");
        // Scanning for the file name
        Scanner file_name = new Scanner(System.in);
        // Storing scanned input into a string variable
        String name_file = file_name.next();
        // opening the file at the path
        File file = new File(name_file);
        // scanning lines from the file
        Scanner scan = new Scanner(file);
        // ensuring that everything in the file is printed
        while(scan.hasNextLine()){
            System.out.println(scan.nextLine());
        }
    }
}