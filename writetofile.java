package com.learningjava;

import java.io.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	 // write your code here
        // asking for user to write
        System.out.println("Write lines you want in the text file, type \"Done\" once you are done writing");
        // scanning input
        Scanner a = new Scanner(System.in);
        // initiating while loop to ensure the scanner got an input
        while (a.hasNext()){
            // Storing data entered in a variable
            String b = a.nextLine();
            // ending condition
            if(b.equalsIgnoreCase("done")){
                break;
            }
            else {
                // catching IO error
                try {
                    // printing
                    PrintWriter out = new PrintWriter(
                        // ensuring the code is appending to the already entered text
                        new FileOutputStream(
                        new File("writetofile.txt")
                                ,true));
                    // printing to the file
                    out.println(b);
                    // closing file to flush data into the file
                    out.close();
                }
                // catch error to the IO error
                catch(FileNotFoundException z) {
                    z.printStackTrace();
                }
                }

            }


    }

        }