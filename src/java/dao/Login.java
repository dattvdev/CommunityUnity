/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBUtils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ytbhe
 */
public class Login {

    private static String INSERT_OTP = "Insert into UserOTP values (?,?,?)";
    private static String check_OTP = "select * from UserOTP where userEmail = ? and otp= ?";
    private static String delete_OTP = "delete from UserOTP where userEmail = ?";

    public boolean insertOTP(String userEmail, int OTP) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                if (userEmailExists(conn, userEmail)) {
                    deleteOTP(userEmail);

                }

                stm = conn.prepareStatement(INSERT_OTP);
                stm.setString(1, userEmail);
                stm.setInt(2, OTP);
                LocalDateTime expirationTime = now.plusMinutes(2);
                String date = expirationTime.format(formatter);
                stm.setString(3, date);

                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

    public boolean deleteOTP(String userEmail) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(delete_OTP);
                stm.setString(1, userEmail);

                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

    public int getOTP(String userEmail) {
        int otp = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                stm = conn.prepareStatement("select otp from UserOTP where userEmail = ? ");
                stm.setString(1, userEmail);
                rs = stm.executeQuery();
                if (rs.next()) {
                    otp = rs.getInt(1);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return otp;
    }

    public LocalDateTime getTimeOTP(String userEmail) {
        LocalDateTime time = null;
        String tmp = "";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                stm = conn.prepareStatement("SELECT CONVERT(VARCHAR, otpTimestamp, 120) AS otpTimestampWithoutMilliseconds\n"
                        + "FROM [Volunteer].[dbo].[UserOTP]\n"
                        + "WHERE useremail = ?;");
                stm.setString(1, userEmail);
                rs = stm.executeQuery();
                if (rs.next()) {
                    tmp = rs.getString(1);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = LocalDateTime.parse(tmp, formatter);
        return time;
    }

    public boolean validOTP(String userEmail, int otp) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = check_OTP;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userEmail);
            ps.setInt(2, otp);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
                ps.close();
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(Login.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public int generateOTP(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero.");
        }

        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {

            int digit = random.nextInt(10);
            otp.append(digit);
        }

        int tmp = Integer.parseInt(otp.toString());

        return tmp;
    }

    public boolean checkTIME_OTP(String userEmail) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime a = getTimeOTP(userEmail);

        if (now.isBefore(a)) {
            return true;
        }
        return false;
    }

    private boolean userEmailExists(Connection conn, String userEmail) throws SQLException {
        PreparedStatement checkStm = null;
        ResultSet resultSet = null;

        try {
            checkStm = conn.prepareStatement("SELECT COUNT(*) FROM UserOTP WHERE userEmail = ?");
            checkStm.setString(1, userEmail);
            resultSet = checkStm.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // If count > 0, the userEmail already exists
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (checkStm != null) {
                checkStm.close();
            }
        }
        return false; // If something went wrong or no rows found, consider it doesn't exist
    }

    public static void main(String[] args) throws SQLException {

    }

}
