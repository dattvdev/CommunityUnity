package dao;

import context.DBUtils;
import entity.Account;
import entity.Bank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author twna21
 */
public class AccountDAO {

    private static final String GET_AN_ACCOUNT = "SELECT UserID, username, Password, name, Phone, email, Role,address,birthDay FROM Accounts WHERE Email = ? AND Password = ?;";
    private static final String GET_AN_ACCOUNT1 = "SELECT UserID, username, Password, name, Phone, email,photo,status, Role,address,birthDay FROM Accounts WHERE username = ? AND Password = ?;";
    private static final String GET_USER_ID = "SELECT UserID FROM Accounts WHERE username = ?;";
    private static final String GET_USER_ID_name = "SELECT UserID FROM Accounts WHERE name = ?;";
    private static final String GET_USER_NAME = "SELECT name FROM Accounts WHERE UserID = ?;";
    private static final String INSERT_ACCOUNT = "INSERT INTO Accounts (email, password, username, phone, status, role,photo,name,birthDay) VALUES (?, ?, ?, ?, ?, ?,?,?,getdate())";
    private static final String GET_ACCOUNT_INFO_BY_EMAIL = "SELECT UserID, Email,photo,name, Password, username, Phone, Status, Role,address,birthDay FROM Accounts WHERE Email = ?";
    private static final String GET_AN_ACCOUNT_BY_ID = "SELECT UserID, email,photo,username, password, name, status, phone, role, address,birthDay FROM Accounts WHERE UserID = ?";
    private static final String GET_AN_ACCOUNT_BY_TOKEN = "SELECT UserID, Email, Password, name, Phone, Status, Role,address FROM Accounts WHERE token = ?";
    private static final String GET_ACC = "SELECT UserID,username ,Email, Password, name, Phone, photo,Status, Role,address,birthDay,sex FROM Accounts WHERE username = ?";
    private static final String GET_All = "SELECT UserID,username ,Email, Password, name, Phone, photo,Status, Role,address,birthDay,sex FROM Accounts WHERE Role != 0;";
    private static final String UPDATE_TOKEN = "UPDATE Accounts Set token = ? WHERE email = ?";

    private static final String VALID_TOKEN = "SELECT UserID, Email, Password, name, Phone, Status, Role FROM Accounts WHERE token = ?";
    private static final String VALID_ACCOUNT_USERNAME = "select * from Accounts where username = ?  ";
    private static final String GET_USER_ByName = "SELECT UserID FROM Accounts WHERE name = ?;";
    private static final String GET_ROLE_ACCOUNT_BY_TOKEN = "SELECT role FROM Accounts WHERE token = ?";

    private static final String UPDATE_PASSWORD = "UPDATE Accounts Set password = ? WHERE email = ?";
    private static final String UPDATE_ACC = "UPDATE Accounts "
            + "SET "
            + "[name] = ?, "
            + "[email] = ?, "
            + "[phone] = ?, "
            + "[address] = ?, "
            + "[birthDay] = ?, "
            + "[photo] = ? ,"
            + "[sex] = ? "
            + "WHERE USERID = ?;";

    public List<Account> getAllAcc() {

        List<Account> l = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_All);

            rs = psm.executeQuery();

            while (rs.next()) {
                int userid = rs.getInt("UserID");
                String username = rs.getString(2);
                String email = rs.getString(3);
                String fullname = rs.getString(5);
                String phone = rs.getString(6);
                String photo = rs.getString(7);
                int status = rs.getInt(8);
                int role = rs.getInt(9);
                String address = rs.getString(10);
                Date date = rs.getDate(11);
                int sex = rs.getInt(12);

                l.add(new Account(userid, email, photo, username, photo, fullname, status, phone, role, address, date, sex));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                try {
                    psm.close();
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

        return l;
    }

    public boolean updateACCOUNT(int accId, String phone, String Address, String Fullname, String email, String birthDAY, String photo, int sex) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(birthDAY, formatter);
            String formattedDate = date.format(outputFormatter);
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_ACC);

                psm.setString(1, Fullname);

                psm.setString(2, email);

                psm.setString(3, phone);
                psm.setString(4, Address);
                psm.setInt(7, sex);
                psm.setInt(8, accId);
                psm.setString(5, formattedDate);
                psm.setString(6, photo);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                try {
                    psm.close();
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

    public boolean updateAccountPassword(String email, String newPassword) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_PASSWORD);
                psm.setString(1, newPassword);
                psm.setString(2, email);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                try {
                    psm.close();
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

    public Account getAnAccountById(int id) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            stm = conn.prepareStatement(GET_AN_ACCOUNT_BY_ID);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (conn != null) {

                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String photo = rs.getString("photo");

                    String username = rs.getString("username");
                    String Password = rs.getString("Password");
                    String FullName = rs.getString("name");
                    int Status = rs.getInt("Status");
                    String Phone = rs.getString("Phone");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                    Date date = rs.getDate("birthDay");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role, add, date);

                }
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
        return acc;
    }

    public int getRoleAccountByToken(String token) throws SQLException {
        int role = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROLE_ACCOUNT_BY_TOKEN);
                stm.setString(1, token);
                rs = stm.executeQuery();
                if (rs.next()) {
                    role = rs.getInt("role");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return role;
    }

    public boolean validToken(String token) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(VALID_TOKEN);
                psm.setString(1, token);
                rs = psm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateToken(String token, String email) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement psm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_TOKEN);
                psm.setString(1, token);
                psm.setString(2, email);
                check = psm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean getAccountByEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACCOUNT_INFO_BY_EMAIL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public Account getAccount1(String user, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_AN_ACCOUNT1);
                stm.setString(1, user);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                    Date date = rs.getDate("birthDay");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role, add, date);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public Account getAccount(String email, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_AN_ACCOUNT);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                    Date d = rs.getDate("birthDay");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role, add, d);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public Account getAccount(String token) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_AN_ACCOUNT_BY_TOKEN);
                stm.setString(1, token);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role, add);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public Account getAccount_BYUSER(String name) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACC);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                    Date date = rs.getDate("birthDay");
                    int sex = rs.getInt("sex");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role, add, date, sex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return acc;
    }

    public Bank getBank(int or_id) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Bank acc = null;
        String query = "select * from TransOfOrganizer where organizer_id = ?;";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(query);
                stm.setInt(1, or_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int orID = rs.getInt("organizer_id");
                    String numberCard = rs.getString("numberCard");
                    String nameCard = rs.getString("nameCard");
                    String nameBank = rs.getString("nameBank");

                    acc = new Bank(id, or_id, numberCard, nameCard, nameBank);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return acc;
    }

    public Account getAccountInfoByEmail(String email) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ACCOUNT_INFO_BY_EMAIL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int AccId = rs.getInt("UserID");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String photo = rs.getString("photo");
                    String username = rs.getString("username");
                    String FullName = rs.getString("name");
                    String Phone = rs.getString("Phone");
                    int Status = rs.getInt("Status");
                    int Role = rs.getInt("Role");
                    String add = rs.getString("address");
                    Date date = rs.getDate("birthDay");
                    acc = new Account(AccId, Email, photo, username, Password, FullName, Status, Phone, Role, add, date);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return acc;
    }

    public boolean insertAccount(String newEmail, String newPassword, String username, String newPhone, int newStatus, int newRole, String photo, String fullName) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT_ACCOUNT);
                stm.setString(1, newEmail);
                stm.setString(2, newPassword);
                stm.setString(3, username);
                stm.setString(4, newPhone);
                stm.setInt(5, newStatus);
                stm.setInt(6, newRole);
                stm.setString(7, photo);
                stm.setString(8, fullName);
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

    public int GetUSERIDByName(String name) {
        int id = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ByName);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return id;
    }

    public boolean checkEmail(String email) {

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "select * from Accounts \n"
                    + "where Email = ?";
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean checkAccountExits(String user) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = VALID_ACCOUNT_USERNAME;
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public int GetUSERID(String username) {
        int id = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ID);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return id;
    }

    public int GetUSERID_byfullname(String username) {
        int id = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_ID_name);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return id;
    }

    public String GetUserName(int id) {
        String name = "";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_NAME);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
        return name;
    }

    public String getUserName_byEmail(String email) {

        try {
            int index = email.indexOf('@');
            if (index != -1) { // Check if "@" symbol is found
                String username = email.substring(0, index);
                return username;
            } else {
                // Handle the case where "@" symbol is not found
                return "Invalid email format";
            }
        } catch (IndexOutOfBoundsException e) {
            // Handle the exception if it occurs
            return "Invalid email format";
        }
    }

    public boolean deleteAcc(int iduser) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("delete from reports where user_id =  ?\n"
                        + "  delete from Notification where user_id =  ?\n"
                        + "  delete from Notification where Reacter =  ?\n"
                        + "delete from usePayment where paymentID = ?\n"
                        + "delete from Payment where giverID =  ?\n"
                        + "delete from Payment where receiverID =  ?\n"
                        + "delete from UserPending where UserID =  ?\n"
                        + "delete from UserPending where ActivityID =  ?\n"
                        + "delete from volunteer_participation where volunteer_id =  ?\n"
                        + "delete from volunteer_activities where organizer_id =  ?\n"
                        + "delete from comments where comment_author_id =  ?\n"
                        + "delete from LikesBlogs where id_clicker =  ?\n"
                        + "delete from Blogs where author =  ?\n"
                        + "delete from Accounts where userID = ?;");

                stm.setInt(1, iduser);
                stm.setInt(2, iduser);
                stm.setInt(3, iduser);
                stm.setInt(4, iduser);
                stm.setInt(5, iduser);
                stm.setInt(6, iduser);
                stm.setInt(7, iduser);
                stm.setInt(8, iduser);
                stm.setInt(9, iduser);
                stm.setInt(10, iduser);
                stm.setInt(11, iduser);
                stm.setInt(12, iduser);
                stm.setInt(13, iduser);
                stm.setInt(14, iduser);

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

    public static void main(String[] args) throws SQLException {
        AccountDAO dao = new AccountDAO();

        System.out.println("" + dao.deleteAcc(4));

    }
}
