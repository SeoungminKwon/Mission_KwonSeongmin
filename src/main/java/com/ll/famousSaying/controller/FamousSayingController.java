package com.ll.famousSaying.controller;

import com.ll.famousSaying.model.FamousSayingRepository;
import com.ll.famousSaying.model.FamousSayingService;

public class FamousSayingController {
    OrderRequest orderRequest = new OrderRequest();
    FamousSayingService fs = new FamousSayingService();
    FamousSayingRepository fsr = new FamousSayingRepository();

    public boolean orderStr(String order){

        //order을 order - id로 분리하기
        if(order.length() >= 3){
            String[] split = order.split("\\?id=");
            return orderRequest.startOrder(split[0], Integer.parseInt(split[1]));
        }else{
            return orderRequest.startOrder(order);
        }
    }

    public void roadData(String fileName) {
        if(fs.Exist(fileName)){
            fs.readAll(fileName);
        }
    }


}
