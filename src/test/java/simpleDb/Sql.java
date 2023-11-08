package simpleDb;

import java.util.List;

public class Sql{


    private String query;

    SimpleDb simpleDb = new SimpleDb("localhost", "root", "", "simpleDb__test");


    public Sql append(String partQuery) {
        query +=partQuery;
        return this;
    }

    public Sql append(String partQuery, String newTitle) {
        query += partQuery.replace("?", newTitle);
        return this;
    }

    public Sql append(String partQuery, int i1, int i2, int i3, int i4) {
        partQuery.replaceFirst("\\?", Integer.toString(i1));
        partQuery.replaceFirst("\\?", Integer.toString(i2));
        partQuery.replaceFirst("\\?", Integer.toString(i3));
        partQuery.replaceFirst("\\?", Integer.toString(i4));
        query += partQuery;
        return this;
    }

    public Sql append(String partQuery, int i1, int i2, int i3) {
        partQuery.replaceFirst("\\?", Integer.toString(i1));
        partQuery.replaceFirst("\\?", Integer.toString(i2));
        partQuery.replaceFirst("\\?", Integer.toString(i3));
        query += partQuery;
        return this;
    }

    public Sql append(String partQuery, int i1, int i2) {
        partQuery.replaceFirst("\\?", Integer.toString(i1));
        partQuery.replaceFirst("\\?", Integer.toString(i2));
        query += partQuery;
        return this;
    }
    public Sql appendIn(String partQuery, List<Long> list) {
        String temp = "";
        for(int i = 0; i < list.size(); i++){
            temp += Long.toString(list.get(i));
        }
        partQuery.replace("?", temp);
        query += partQuery;
        return this;
    }



    public long delete() {
        return simpleDb.run(query);
    }


    public long update() {
        return  simpleDb.run(query);
    }



}
