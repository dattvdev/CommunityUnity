package dao;

import entity.Notification;
import context.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificateDAO {

    List<Notification> listOfNotifications = new ArrayList<>();

    // hàm get tất cả thông báo ở database
    public List<Notification> getAllNotifications() throws ClassNotFoundException {
        Connection cnt = null;
        PreparedStatement stm = null;
        ResultSet res = null;
        try {
            // lấy tất cả thông báo trong db sử dụng câu lệnh của sql sever
            String sql = "select * from Notification";
            cnt = DBUtils.getConnection();
            stm = cnt.prepareStatement(sql);
            res = stm.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                int user_id = res.getInt("user_id");
                String notification_content = res.getString("content");
                Date notification_date = res.getDate("date");
                String link_notify = res.getString("link_notification");
                int reacter = res.getInt("reacter");

                Notification notify = new Notification(id, user_id, notification_content,
                        notification_date, link_notify, reacter);
                listOfNotifications.add(notify);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                cnt.close();
                stm.close();
                res.close();
            } catch (SQLException ex) {
                Logger.getLogger(NotificateDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listOfNotifications;
    }

    public void addNotification(int user_id, String notification_content, Date notification_date, String link_notify,
            int reacter) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtils.getConnection();
            // câu lệnh insert dữ liệu vafo db
            String sql = "INSERT INTO Notification (user_id,content, date, link_notification, Reacter)"
                    + "VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            stmt.setString(2, notification_content);
            stmt.setObject(3, new java.sql.Date(notification_date.getTime()));
            stmt.setString(4, link_notify);
            stmt.setInt(5, reacter);
            stmt.executeUpdate();
            System.out.println("Inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error occurred while inserting: " + e.getMessage());
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.out.println("Error occurred while closing database resources: " + e.getMessage());
            }
        }
    }

    public void deleteNotification(int id) throws ClassNotFoundException {
        Connection cnt = null;
        PreparedStatement stm = null;
        ResultSet res = null;
        try {
            // delete cái id = id mình đưa vào
            String sql = "  delete from Notification where Reacter = ? and content like '%Like%'";
            cnt = DBUtils.getConnection();
            cnt.setAutoCommit(false);
            stm = cnt.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            cnt.commit();
            System.err.println("Delete!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                cnt.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (cnt != null) {
                    cnt.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        NotificateDAO notify = new NotificateDAO();
        notify.deleteNotification(3);
      
    }

}
