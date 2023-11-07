package com.ll.famousSaying.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.famousSaying.FamousSaying;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FamousSayingRepository {
    private static Map<Integer, FamousSaying> famousSayingMap = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    private static int idx = 0;

    private static String recentFileName = "";


    public boolean fileExist(String fileName){
        if(new File(fileName).exists()){
            return true;
        }else{
            return false;
        }
    }

    public boolean mapExist(Integer i){
        if(famousSayingMap.containsKey(i)){
            return true;
        }else{
            return false;
        }
    }



    public boolean readFile(String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            famousSayingMap = (Map<Integer, FamousSaying>) inputStream.readObject();
            recentFileName = fileName;
            idx = famousSayingMap.size();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("파일 읽어오기 실패했습니다.");
            return false;
        }
        return true;
    }

    public void writeFile(String fileName){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(famousSayingMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 저장에 실패하였습니다.");
        }
    }


    public void saveFamousSayingData(String content, String author){
        FamousSaying famousSaying = new FamousSaying(++idx, author, content);
        famousSayingMap.put((Integer) idx, famousSaying);
    }

    public Map<Integer, FamousSaying> returnMap(){
        return famousSayingMap;
    }

    public void remove(){
        famousSayingMap.remove((Integer) idx);
    }
    public void remove(int idx){
        famousSayingMap.remove((Integer) idx);
    }

    public void modifyMap(int idx, FamousSaying famousSaying){
        remove(idx);
        famousSayingMap.put((Integer) idx, famousSaying);
    }

    public void makeJsonFile(){
        try {
            // 객체를 JSON으로 변환하여 파일에 저장
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("arr.json"), famousSayingMap);
            System.out.println("JSON 파일이 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("JSON 파일 생성에 실패하였습니다.");
        }
    }

    public int nowIdx() {
        System.out.println(idx);
        return idx;
    }

    public String getContent(int idx){
        return famousSayingMap.get(idx).getFamousSayingStr();
    }

    public String getAuthor(int idx) {
        return famousSayingMap.get(idx).getAuthor();
    }
}
