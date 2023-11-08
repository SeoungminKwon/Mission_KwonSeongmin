package simpleDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class SimpleDb {

    private String url;
    private String dbUser;
    private String dbPw;

    private String dbName;






    public SimpleDb(String url, String dbUser, String dbPw, String DbName) {
        this.url = url;
        this.dbUser = dbUser;
        this.dbPw = dbPw;
        this.dbName = DbName;



    }

    public void setDevMode(boolean b) {
        //Todo setmode???
    }

    public Map<String, Object> runSelect(String sql){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        HashMap<String, Object> map = new HashMap<>();
        try{
            con = DriverManager.getConnection(url, dbUser, dbPw);
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                LocalDateTime createdDate =
                        LocalDateTime.ofInstant(rs.getDate("createdDate").toInstant(), ZoneId.systemDefault());
                LocalDateTime modifiedDate =
                        LocalDateTime.ofInstant(rs.getDate("modifiedDate").toInstant(), ZoneId.systemDefault());
                boolean isBlind = rs.getBoolean("isBlind");
                String body = rs.getString("body");
                String title = rs.getString("title");
                map.put("id", id);
                map.put("createdDate", createdDate);
                map.put("modifiedDate", modifiedDate);
                map.put("isBlind", isBlind);
                map.put("body", body);
                map.put("title", title);
            }


            rs.close();
            st.close();
            con.close();
        }catch (Exception e){

        }finally {
            if(rs != null){try{rs.close();}catch (Exception e){}}
            if(st != null){try{rs.close();}catch (Exception e){}}
            if(con != null){try{rs.close();}catch (Exception e){}}
        }
        return map;
    }

    public int run(String sql) {
        Connection con = null;
        PreparedStatement st = null;
        int ret = 0;
        try{
            con = DriverManager.getConnection(url, dbUser, dbPw);
            st = con.prepareStatement(sql);
            ret = st.executeUpdate();

            st.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(con != null){try{con.close();}catch (Exception e){e.printStackTrace();}}
            if(st != null){try{con.close();}catch (Exception e){e.printStackTrace();}}
        }
        return ret;
    }




    public void run(String sql, String title, String body, boolean isBlind) {
        Connection con = null;

        try{
            con = DriverManager.getConnection(url, dbUser, dbPw);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, body);
            ps.setBoolean(3, isBlind);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(con != null){try{con.close();}catch (Exception e){e.printStackTrace();}}

        }
    }

    public Sql genSql() {
        return new Sql();
    }


}
