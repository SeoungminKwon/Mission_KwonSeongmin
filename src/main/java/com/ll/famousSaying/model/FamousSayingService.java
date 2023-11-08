package com.ll.famousSaying.model;

public class FamousSayingService {

    FamousSayingRepository fsr = new FamousSayingRepository();
    public void roadData() {

    }

    public boolean Exist(String fileName) {
       return fsr.fileExist(fileName);
    }

    public void readAll(String fileName) {
        fsr.readFile(fileName);
    }

    public void wirteFIle(String fileName) {
        fsr.writeFile(fileName);
    }

    public void saveData(String content, String author) {
        fsr.saveFamousSayingData(content, author);
    }

    public Object bringData() {
        return fsr.returnMap();
    }

    public int bringIdx() {
        return fsr.nowIdx();
    }

    public void removeContent(int idx) {
        fsr.remove(idx);
    }

    public String getContent(int idx) {
        return fsr.getContent(idx);
    }

    public String getAuthor(int idx) {
        return fsr.getAuthor(idx);
    }
}
