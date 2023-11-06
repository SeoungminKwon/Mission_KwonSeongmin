package com.ll.famousSaying;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OrderRequest {
    private String order;

    public OrderRequest(){}

    public OrderRequest(String order){
        this.order = order;
    }

    public void startOrder(String order){
        if(order.equals("종료")){

        } else if (order.equals("등록")) {

        }else if(order.equals("목록")) {

        }else if(order.equals("삭제")){

        }else if(order.equals("수정")){

        } else if (order.equals("빌드")) {

        }
    }
}
