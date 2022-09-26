package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            String str = sc.next();

            if (str.equals("break")){
                break;
            }

            try {
                URL url = new URL(str);
                System.out.println(url.toString());
            }
            catch (URLNotCreatedException e){
                System.out.println("error");
            }

        }
    }
}
