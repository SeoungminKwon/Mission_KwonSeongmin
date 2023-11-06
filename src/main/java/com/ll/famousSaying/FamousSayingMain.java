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
        ArrayList<FamousSaying> famousSayings = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        FamousSayingController fsController = new FamousSayingController();

        //c -> s -> r
        //Repository - fireExists("fileName");
        if(new File("arr.bin").exists()){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("arr.bin"))) {
                famousSayings = (ArrayList<FamousSaying>) inputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("파일 읽어오기 실패했습니다.");
                return;
            }
        }else{
            System.out.println("기존 기록이 존재하지 않습니다.");
        }



        System.out.println("== 명언 앱 ==");



        while(true){
            int selectedId = 0;
            System.out.print("명령)");
            str = sc.nextLine();

            //controller
            if(str.contains("?")){
                int idx = str.indexOf("?");
                String temp = str.substring(0,idx);
                selectedId = Integer.parseInt(str.substring(idx+4));
                str = temp;
            }

            //Ropo
            if(str.equals("종료")){
                // 객체를 파일에 저장
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("arr.bin"))) {
                    outputStream.writeObject(famousSayings);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("파일 저장에 실패하였습니다.");
                }
                break;
                //Repo
            } else if (str.equals("등록")) {
                System.out.print("명언 :  ");
                String fs = sc.nextLine();
                System.out.print("작가 :  ");
                String author = sc.nextLine();
                famousSayings.add(new FamousSaying(++cnt, author, fs));
                System.out.println(cnt + "번 명언이 등록되었습니다.");
                //controller
            }else if(str.equals("목록")) {
                System.out.println("번호  /  작가  /  명언");
                System.out.println("--------------------------------------------");
                for (int i = famousSayings.size() -1; i >= 0; i--) {
                    FamousSaying famousSaying = famousSayings.get(i);
                    System.out.println(famousSaying.getId() + " / " + famousSaying.getAuthor() + " / " + famousSaying.getFamousSayingStr());
                }
                //Repo
            }else if(str.equals("삭제")){
                try {
                    famousSayings.remove(selectedId);
                }catch (IndexOutOfBoundsException e){
                    System.out.println(selectedId + "번 명령은 존재하지 않습니다.");
                }
                famousSayings.remove(selectedId);
                //Repo
            }else if(str.equals("수정")){
                try{
                    FamousSaying famousSaying = famousSayings.get(selectedId-1);
                    int i = famousSayings.indexOf(famousSaying);

                    System.out.println("명언(기존) : " + famousSaying.getFamousSayingStr());
                    System.out.print("명언 : ");
                    String famousSayingStr = sc.nextLine();
                    System.out.println("작가(기존) : " + famousSaying.getAuthor());
                    System.out.print("작가 :");
                    String famousSayingAuthor = sc.nextLine();
                    famousSaying.setFamousSayingStr(famousSayingStr);
                    famousSaying.setAuthor(famousSayingAuthor);

                    famousSayings.remove(i);
                    famousSayings.add(i, famousSaying);
                }catch (IndexOutOfBoundsException e){
                    System.out.println(selectedId + "번 명령은 존재하지 않습니다.");
                }
                //Repo
            } else if (str.equals("빌드")) {
                try {
                    // 객체를 JSON으로 변환하여 파일에 저장
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("arr.json"), famousSayings);
                    System.out.println("JSON 파일이 성공적으로 생성되었습니다.");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("JSON 파일 생성에 실패하였습니다.");
                }
            }
        }
    }
}
