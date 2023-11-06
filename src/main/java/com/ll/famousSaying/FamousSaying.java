package com.ll.famousSaying;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class FamousSaying implements Serializable {
    private int id;
    private String author;
    private String famousSayingStr;
}
