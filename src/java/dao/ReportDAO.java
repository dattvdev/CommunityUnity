/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBUtils;
import entity.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ytbhe
 */
public class ReportDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ReportDAO() {
        try {
            conn = new DBUtils().getConnection();
        } catch (Exception e) {
        }
    }

    public boolean insertReport(int userid, int activitiesID, String text) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("insert into reports values(?,?,getdate(),?);");

                stm.setInt(1, userid);
                stm.setInt(2, activitiesID);
                stm.setString(3, text);

                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {

                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {

                }
            }
        }
        return check;
    }

    public List<Report> getAllReport() {
        List<Report> list = new ArrayList<>();
        String query = "    SELECT a.*,b.activity_name,c.name\n"
                + "               FROM reports A\n"
                + "               JOIN volunteer_activities B ON A.activitiesID = B.activity_id\n"
                + "			   JOIN Accounts c ON a.user_id = c.UserID;";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int activitiId = rs.getInt(3);
                int acid = rs.getInt(2);
                String text = rs.getString("report_text");
                Date date = rs.getDate("report_date");
                String nameor = rs.getString("name");
                String nameaccTi = rs.getString("activity_name");

                Report a = new Report(activitiId, acid, text, date, nameaccTi, nameor);
                list.add(a);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public boolean deleteReport(int report) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();if (conn != null) {
                stm = conn.prepareStatement("   delete from reports where activitiesID = ?;");

                stm.setInt(1, report);

                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {

                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {

                }
            }
        }
        return check;
    }

    public static void main(String[] args) {
        ReportDAO dao = new ReportDAO();
        System.out.println(dao.getAllReport());

    }
}