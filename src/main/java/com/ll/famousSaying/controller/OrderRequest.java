package com.ll.famousSaying.controller;

import com.ll.famousSaying.FamousSaying;
import com.ll.famousSaying.model.FamousSayingRepository;
import com.ll.famousSaying.model.FamousSayingService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

public class OrderRequest {

    FamousSayingRepository fsr = new FamousSayingRepository();
    FamousSayingService fss = new FamousSayingService();
    Scanner sc = new Scanner(System.in);



    public boolean startOrder(String order){
        if(order.equals("종료")){
            fss.wirteFIle("arr.bin");
            return false;
        } else if (order.equals("등록")) {
            System.out.print("명언 : ");
            String content = sc.nextLine();
            System.out.print("작가 : ");
            String author = sc.nextLine();
            fss.saveData(content, author);
            return true;
        }else if(order.equals("목록")) {
            Map<Integer, FamousSaying> map = (Map<Integer, FamousSaying>) fss.bringData();
            int i = fss.bringIdx();
            System.out.println("번호 / 작가 / 명언");
            for(int j = i; j >= 0; j--){
                if(map.containsKey(j)){
                    System.out.println(map.get(j).getId() + " / " + map.get(j).getAuthor() + " / " + map.get(j).getFamousSayingStr());
                }
            }
            return true;
        } else if (order.equals("빌드")) {
            fsr.makeJsonFile();
        }
        return true;
    }

    public boolean startOrder(String order, int idx){
        if(order.equals("삭제")){
            fss.removeContent(idx);
        }else if(order.equals("수정")){
            System.out.print("명언(기존) : ");
            System.out.println(fss.getContent(idx));

            System.out.print("명언 : ");
            String newContent = sc.nextLine();

            System.out.print("작가(기존) : ");
            System.out.println(fss.getAuthor(idx));

            System.out.print("작가 : ");
            String newAuthor = sc.nextLine();

            fsr.modifyMap(idx, new FamousSaying(idx, newAuthor, newContent));
        }
        return true;
    }
}
