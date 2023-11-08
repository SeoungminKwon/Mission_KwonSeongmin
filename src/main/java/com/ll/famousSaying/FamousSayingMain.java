package com.ll.famousSaying;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.famousSaying.controller.FamousSayingController;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FamousSayingMain {
    public static void main(String[] args) {

        //todo
        /*
        * 0.MVC패턴으로 바꾸기
        *
        * 1.리펙터링하기
        * 2. testCode짜기
        * 3. DB코드 짜기
        * 4. 어노테이션 코드짜기
        * 5.레펙터링하기*/

        Scanner sc = new Scanner(System.in);
        String str = "";
        int cnt = 0;

        FamousSayingController fsc = new FamousSayingController();




        String fileName = "arr.bin";
        fsc.roadData(fileName);

        System.out.println("== 명언 앱 ==");

        while(true){
            System.out.print("명령) ");
            String s = sc.nextLine();
            if(!fsc.orderStr(s)){
                break;
            }
        }

    }
}
