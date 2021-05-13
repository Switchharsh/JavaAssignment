package com.learningjava;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Enter your email please");
        // Scanning for an input email
        Scanner email = new Scanner(System.in);
        // storing the input in a variable
        String a = email.next();
        // defining pattern; excludes trailing, consecutive and leading dots
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$");
        // matching the input to the pattern
        Matcher matcher = pattern.matcher(a);
        // if pattern matches, send email for validation(not implemented but to be followed)
        if(matcher.matches()) {
            System.out.println("Your email although passes the test still needs to be verified, please click the link sent on your email");
        }else{
            // the email is invalid
            System.out.println("Your email is invalid");
        }

    }
}
