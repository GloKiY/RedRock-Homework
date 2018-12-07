package team.redrock.demo;

import java.sql.*;

public class Grades {
    private String name;
    private String math;
    private String English;
    private String C;

    Grades(String name,String math,String English,String C)
    {
        this.name = name;
        this.math = math;
        this.English = English;
        this.C = C;
    }

    public String getName() {
        return name;
    }

    public String getC() {
        return C;
    }

    public String getEnglish() {
        return English;
    }

    public String getMath() {
        return math;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setC(String c) {
        C = c;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public void setMath(String math) {
        this.math = math;
    }
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/homework?"+ "useUnicode=true&characterEncoding=UTF8";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    private static int insert(Grades grades) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into Grades (name,math,English,C) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,grades.getName());
            pstmt.setString(2,grades.getMath());
            pstmt.setString(3,grades.getEnglish());
            pstmt.setString(4,grades.getC());
            i = pstmt.executeUpdate();
            if(i == 1) System.out.println("修改成功");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static Integer getAll() {
        Connection conn = getConn();
        String sql = "select * from Grades";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args)
    {
        Grades.getAll();
    }
}
