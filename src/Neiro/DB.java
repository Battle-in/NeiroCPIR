package Neiro;
import java.sql.*;

public class DB {

    private Connection connection;
    private String theme;

    public void setTheme(String theme) {
        this.theme = theme;
        if(check_theme(theme) == 0){
            insert_newTheme(theme);
        }
    }

    public DB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:neiro.db");
            theme = "null";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void push(String word) {
        System.out.println(check_exist(word));
        if (check_exist(word) == 0) {
            insert_new(word);
        } else {
            int freq = check_word(word);
            freq++;
            upd_word(word, freq);
        }
    }

    private void upd_word(String word, int freq) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE words SET frequency = " + freq + " WHERE word = '" + word + "' ";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private int check_exist(String word){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT EXISTS(SELECT frequency FROM words WHERE word = '" + word + "')";
            ResultSet rs = st.executeQuery(sqlQuery);
            System.out.print(rs.getInt(1));
            return rs.getInt(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    private static void write_neiro(){

    }

    private int check_word(String word) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT frequency FROM words WHERE word = '" + word + "'";
            //  System.out.println(sql);
            //select frequency from words where word = 'syka';
            ResultSet rs = st.executeQuery(sql);
            int res = 0;
            res = rs.getInt(1);

            st.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void insert_new(String word) {
        Statement st = null;
        try {
            st = connection.createStatement();
            String sql = "INSERT INTO words (word, frequency ,theme)" +
                    "VALUES('" + word + "', ' 1 ' , '" + theme + "') ";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int check_theme(String theme) {
        Statement st = null;
        try {
            st = connection.createStatement();
            String sql = "SELECT frequency FROM themes WHERE theme = '" + theme + "'";
            //  System.out.println(sql);
            //select frequency from words where word = 'syka';
            ResultSet rs = st.executeQuery(sql);
            int res = 0;
            res = rs.getInt(1);

            st.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void insert_newTheme(String theme) {
        Statement st = null;
        try {
            st = connection.createStatement();
            String sql = "INSERT INTO theme (theme) VALUES('" + theme + "', ' 1 ') ";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void pushNetWorck(String name, String json){
        System.out.println(existNetWorck(name));
        if (existNetWorck(name) == 1){
            updateNetWorck(name, json);
        } else {
            writeNetWorck(name, json);
        }
    }

    void updateNetWorck(String name, String json){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = "UPDATE networcks SET json = '"+json+"' WHERE name = '"+name+"' ";
            st.execute(sql);
            st.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    int existNetWorck(String name){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = "SELECT EXISTS(SELECT json FROM networcks WHERE name = '"+name+"')";
            ResultSet r = st.executeQuery(sql);
            return r.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    void writeNetWorck(String name, String json) {
        Statement st;
        try {
            st = connection.createStatement();
            String sql = "INSERT INTO `networcks`(`name`,`json`) VALUES ('"+name+"','"+json+"')";
            st.execute(sql);
            st.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    String readNW(String name){
        Statement st;
        try {
            st = connection.createStatement();
            String sql = "select json from networcks where (name = '"+name+"');";
            ResultSet r = st.executeQuery(sql);
            return r.getString(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
}
