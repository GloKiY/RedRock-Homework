package team.redrock.demo;

import java.sql.*;

public class DaoTest {
    Connection con;
    Statement statement;
    ResultSet rs;

    public Connection getCon() {
        return con;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getRs() {
        return rs;
    }

    public DaoTest(Connection con) {
        this.con = con;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Student student, Connection conn) throws SQLException {
        String sql = "insert into student(name,student_id,class_id,college) values(?,?,?,?)";
        System.out.println(sql);
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getStudent_id());
        pstmt.setString(3, student.getClass_id());
        pstmt.setString(4, student.getCollege());
        System.out.println(pstmt.toString());

        pstmt.executeUpdate();

    }


    public static void insertStudentDao(Student student){
        Connection con = JDBCUtil.getConnection();
        DaoTest dao = new DaoTest(con);
        try {
            dao.insert(student,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(dao.getRs(),dao.getStatement(),dao.getCon());
        }
    }

    public static void main(String[] args) {

    }
}