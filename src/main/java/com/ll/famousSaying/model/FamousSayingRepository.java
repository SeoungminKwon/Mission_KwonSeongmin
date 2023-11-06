package com.ll.famousSaying.model;

import com.ll.famousSaying.FamousSaying;

import java.io.*;
import java.util.ArrayList;

public class FamousSayingRepository {
    private static ArrayList<FamousSaying> famousSayings = new ArrayList<>();


    public boolean fileExist(String fileName){
        if(new File(fileName).exists()){
            return true;
        }else{
            return false;
        }
    }

    public boolean readFile(String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            famousSayings = (ArrayList<FamousSaying>) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("파일 읽어오기 실패했습니다.");
            return false;
        }
        return true;
    }

    public boolean writeFile(String fileName){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(famousSayings);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 저장에 실패하였습니다.");
            return false;
        }
        return true;
    }

    public void saveFamousSayingData(int id, String content, String author){
        FamousSaying famousSaying = new FamousSaying(id, author, content);
        famousSayings.add(famousSaying);
    }

    public Object returnArrList(){
        return famousSayings;
    }

    public void remove(int id){
        for(int i = 0; i < famousSayings.size(); i++){
            if(famousSayings.get(i).getId() == id){
                famousSayings.remove(i);
                break;
            }
        }
    }


}
