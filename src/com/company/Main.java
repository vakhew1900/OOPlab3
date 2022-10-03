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
//                String str2 = sc.next();
                URL url = new URL(str);
                /*URL url2 = url.calculateNewUrl(str2);
//                System.out.println("ddd :" + str);*/
                System.out.println(url.toString());
            }
            catch (URLNotCreatedException e){
                System.out.println("error");
            }

        }
    }
}
