/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author datka
 */
import context.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityDAO {

    //Remove------------------------------------------------
    private static final String REMOVE_PENDING_ACTIVITY = "DELETE FROM Pending_activity WHERE activity_id = ?;";
    private static final String REMOVE_PENDING_USER = "DELETE FROM Userpending WHERE UserID = ? AND ActivityID = ?";
    private static final String REMOVE_ACTIVITY = "DELETE FROM volunteer_activities WHERE activity_id = ?;";
    private static final String REMOVE_PARITICIPATIONACTIVITY = "DELETE FROM volunteer_participation WHERE activity_id = ?;";
    private static final String REMOVE_PAYMENT_BY_ACTIVITY = "DELETE FROM Payment WHERE eventID = ?;";
    private static final String REMOVE_USEPAYMENT_BY_ACTIVITY = "DELETE FROM usePayment WHERE activity_id = ?;";
    private static final String REMOVE_PENDING_USER_BY_ACTIVITY = "DELETE FROM UserPending WHERE ActivityID = ?";
    //GET------------------------------------
    private static final String GET_LIST_EVENT_FROM_TO = "select * from ( select *, ROW_NUMBER() over (order by activity_id) as rownumber from volunteer_activities ) as activity where activity.rownumber >= ? and activity.rownumber <=?";
    private static final String GET_TOTAL_TRANG_THAI_DA_DIEN_RA = "SELECT COUNT(*) FROM volunteer_activities WHERE end_date < GETDATE()";
    private static final String GET_TOTAL_TRANG_THAI_DANG_DIEN_RA = "SELECT COUNT(*) FROM volunteer_activities WHERE start_date <= GETDATE() AND end_date >= GETDATE()";
    private static final String GET_TOTAL_TRANG_THAI_SAP_DIEN_RA = "SELECT COUNT(*) FROM volunteer_activities WHERE start_date > GETDATE()";
    private static final String GET_TRANG_THAI_DA_DIEN_RA_FROM_TO = "SELECT * FROM volunteer_activities WHERE end_date < GETDATE() ORDER BY (SELECT NULL) OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";
    private static final String GET_TRANG_THAI_DANG_DIEN_RA_FROM_TO = "SELECT * FROM volunteer_activities WHERE start_date <= GETDATE() AND end_date >= GETDATE() ORDER BY (SELECT NULL) OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";
    private static final String GET_TRANG_THAI_SAP_DIEN_RA_FROM_TO = "SELECT * FROM volunteer_activities WHERE start_date > GETDATE() ORDER BY (SELECT NULL) OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";
    private static final String GET_USE_PAYMENT = "SELECT * FROM usePayment WHERE activity_id = ?;";
    private static final String GET_TOTAL_AMOUNT_USE_PAYMENT = "SELECT SUM(amount) FROM usePayment WHERE activity_id = ?;";
    private static final String GET_DONATE_BY_EVENT_ID = "SELECT [payment_id],[giverID],[receiverID],[eventID],[transaction_date] ,[text] ,[amount],[status] FROM [Payment] WHERE eventID=?";
    private static final String GET_DONATE_BY_USER_ID = "SELECT [payment_id],[giverID],[receiverID],[eventID],[transaction_date] ,[text] ,[amount],[status] FROM [Payment] WHERE giverID=?";
    private static final String GET_TOTAL_AMOUNT_BY_USER_ID = "SELECT SUM(amount) AS totalAmount FROM Payment WHERE giverID = ?";
    private static final String SEARCH_ACTIVITY = "select * from volunteer_activities";
    private static final String GET_WITH_ID = "SELECT * FROM volunteer_activities WHERE activity_id = ?;";
    private static final String GET_WITH_OID = "SELECT * FROM volunteer_activities WHERE organizer_id = ?;";
    private static final String GET_TOTAL_ROWS = "SELECT COUNT(*) FROM volunteer_activities;";
    private static final String GET_SEARCH_TOTAL_ROWS = "SELECT COUNT(*) FROM volunteer_activities WHERE activity_name LIKE ? OR location LIKE ?;";
    private static final String GET_PENDING_ACTIVITY = "SELECT * FROM Pending_activity;";
    private static final String FIND_PENDING_ACTIVITY = "SELECT * FROM Pending_activity WHERE activity_id = ?;";
    private static final String CHECK_PENDING_USER = "SELECT COUNT(*) FROM Userpending WHERE UserID = ? AND ActivityID = ?";
    private static final String CHECK_PARTICIPATION_EXIST = "SELECT * FROM volunteer_participation WHERE volunteer_id = ? AND activity_id = ?";
    private static final String SELECT_USERPENDING_BY_ACTIVITY = "SELECT UserID FROM UserPending WHERE ActivityID = ?";
    private static final String SELECT_PARTICIPANTS_BY_ACTIVITY = "SELECT volunteer_id FROM volunteer_participation WHERE activity_id = ?";
    private static final String SELECT_ACTIVITIES_BY_USER = "SELECT activity_id, registration_date FROM volunteer_participation WHERE volunteer_id = ?;";
    private static final String GET_NEW_ACTIVITY = "SELECT TOP 1 [activity_id] FROM [Volunteer].[dbo].[volunteer_activities] ORDER BY [activity_id] DESC;";
    //SET----------------------------
    private static final String SET_PENDING_USER = "INSERT INTO UserPending (UserID, ActivityID) VALUES (?, ?);";
    private static final String CREATE_ACTIVITY = "INSERT INTO volunteer_activities (activity_name, description, start_date, end_date, location, organizer_id, numberMember, created_date, updated_date,photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String CREATE_PENDING_ACTIVITY = "INSERT INTO Pending_activity (activity_name, description, start_date, end_date, location, organizer_id, numberMember, created_date, updated_date,photo) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE(), GETDATE(),?)";
    private static final String UPDATE_ACTIVITY = "UPDATE volunteer_activities SET activity_name = ?, description = ?, start_date = ?, end_date = ?, location = ?, numberMember = ?, updated_date = GETDATE() WHERE activity_id = ?";
    private static final String ADD_PARTICIPATION = "INSERT INTO volunteer_participation (volunteer_id, activity_id, registration_date) VALUES (?, ?, GETDATE())";
    private static final String SET_USE_PAYMENT = "INSERT INTO usePayment (content, amount, activity_id) VALUES (?, ?, ?);";
    //--------------------------------

    public List<VolunteerActivity> getActivityFromTo(int page, int pageSize) throws SQLException {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<VolunteerActivity> activities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_LIST_EVENT_FROM_TO);
            psm.setInt(1, from);
            psm.setInt(2, to);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));

                activities.add(activity);
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

        return activities;
    }

    public List<VolunteerActivity> getActivityStatusFromTo(int page, int pageSize, int cate) throws SQLException {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<VolunteerActivity> activities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            switch (cate) {
                case 1: {
                    psm = conn.prepareStatement(GET_TRANG_THAI_SAP_DIEN_RA_FROM_TO);
                    break;
                }
                case 2: {
                    psm = conn.prepareStatement(GET_TRANG_THAI_DANG_DIEN_RA_FROM_TO);
                    break;
                }
                case 3: {
                    psm = conn.prepareStatement(GET_TRANG_THAI_DA_DIEN_RA_FROM_TO);
                    break;
                }
                default: {
                    return null;
                }
            }
            psm.setInt(1, from - 1);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));

                activities.add(activity);
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

        return activities;
    }

    public List<VolunteerActivity> getSearchActivityFromTo(int page, int pageSize, String keyword) throws SQLException {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<VolunteerActivity> activities = new ArrayList<>();
        List<VolunteerActivity> activities1 = new ArrayList<>();
        List<VolunteerActivity> activities2 = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(SEARCH_ACTIVITY);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));
                activities.add(activity);
            }

            for (int i = 0; i < activities.size(); i++) {
                String name = activities.get(i).getActivityName().toLowerCase();
                String location = activities.get(i).getLocation().toLowerCase();
                if (name.indexOf(keyword.toLowerCase()) != -1 || location.indexOf(keyword.toLowerCase()) != -1) {
                    activities1.add(activities.get(i));
                }
            }
            if (to > activities1.size() + 1) {
                to = activities1.size() + 1;
            }
            for (int i = from - 1; i < to; i++) {
                activities2.add(activities1.get(i));
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

        return activities2;
    }

    public List<VolunteerActivity> getPendingActivity() throws SQLException {
        List<VolunteerActivity> activities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_PENDING_ACTIVITY);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));
                activities.add(activity);
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

        return activities;
    }

    public void UpdateActivity(String activityName, String description, Date startDate, Date endDate, String location, int memberLimit, int id) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement psm = conn.prepareStatement(UPDATE_ACTIVITY)) {

            psm.setString(1, activityName);
            psm.setString(2, description);
            psm.setTimestamp(3, new Timestamp(startDate.getTime()));
            psm.setTimestamp(4, new Timestamp(endDate.getTime()));
            psm.setString(5, location);
            psm.setInt(6, memberLimit);
            psm.setInt(7, id);
            psm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CreateActivity(VolunteerActivity ev) {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(CREATE_ACTIVITY);
            psm.setString(1, ev.getActivityName());
            psm.setString(2, ev.getDescription());
            psm.setTimestamp(3, new Timestamp(ev.getStartDate().getTime()));
            psm.setTimestamp(4, new Timestamp(ev.getEndDate().getTime()));
            psm.setString(5, ev.getLocation());
            psm.setInt(6, ev.getOrganizerId());
            psm.setInt(7, ev.getNumberMember());
            // created_date, updated_date
            psm.setTimestamp(8, new Timestamp(ev.getCreatedDate().getTime()));
            psm.setTimestamp(9, new Timestamp(ev.getUpdatedDate().getTime()));
            psm.setString(10, ev.getPhoto());
            psm.executeUpdate();

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

    }

    public void CreatePendingActivity(String activityName, String description, Date startDate, Date endDate, String location, int organizerId, int memberLimit, String photo) {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(CREATE_PENDING_ACTIVITY);
            psm.setString(1, activityName);
            psm.setString(2, description);
            psm.setTimestamp(3, new Timestamp(startDate.getTime()));
            psm.setTimestamp(4, new Timestamp(endDate.getTime()));
            psm.setString(5, location);
            psm.setInt(6, organizerId);
            psm.setInt(7, memberLimit);
            psm.setString(8, photo);
            psm.executeUpdate();

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

    }

    public void setPendingUser(int userid, int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(SET_PENDING_USER);
            psm.setInt(1, userid);
            psm.setInt(2, activityid);
            psm.executeUpdate();

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

    }

    public boolean isPendingUserExists(int userid, int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(CHECK_PENDING_USER);
            psm.setInt(1, userid);
            psm.setInt(2, activityid);
            rs = psm.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exists = true; // Cặp ID đã tồn tại
                }
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

        return exists;
    }

    public void removePendingActivity(int eid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_PENDING_ACTIVITY);
            psm.setInt(1, eid);
            psm.executeUpdate();

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
    }

    public void removeActivity(int eid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_ACTIVITY);
            psm.setInt(1, eid);
            psm.executeUpdate();

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
    }

    public void removeParticipationByActivityID(int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_PARITICIPATIONACTIVITY);
            psm.setInt(1, activityid);
            psm.executeUpdate();

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
    }

    public void removePaymentByActivityID(int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_PAYMENT_BY_ACTIVITY);
            psm.setInt(1, activityid);
            psm.executeUpdate();

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
    }

    public void removeUsePaymentByActivityID(int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_USEPAYMENT_BY_ACTIVITY);
            psm.setInt(1, activityid);
            psm.executeUpdate();

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
    }

    public void removePendingUserByActivityID(int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_PENDING_USER_BY_ACTIVITY);
            psm.setInt(1, activityid);
            psm.executeUpdate();

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
    }

    public void removePendingUser(int userid, int activityid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(REMOVE_PENDING_USER);
            psm.setInt(1, userid);
            psm.setInt(2, activityid);
            psm.executeUpdate();

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
    }

    public boolean isParticipationExist(int volunteerId, int activityId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(CHECK_PARTICIPATION_EXIST);
            psm.setInt(1, volunteerId);
            psm.setInt(2, activityId);
            rs = psm.executeQuery();

            return rs.next(); // Nếu có kết quả, tức là cặp ID tồn tại

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    }

    public List<Integer> getPendingUserByActivity(int activityId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        List<Integer> pendingUser = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();

            psm = conn.prepareStatement(SELECT_USERPENDING_BY_ACTIVITY);
            psm.setInt(1, activityId);
            rs = psm.executeQuery();

            while (rs.next()) {
                int volunteerId = rs.getInt("UserID");
                pendingUser.add(volunteerId);
            }

        } catch (SQLException e) {
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

        return pendingUser;
    }

    public void addParticipation(int volunteerId, int activityId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(ADD_PARTICIPATION);
            psm.setInt(1, volunteerId);
            psm.setInt(2, activityId);
            psm.executeUpdate();

        } catch (SQLException e) {
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
    }

    public List<Integer> getParticipatingUsersByActivity(int activityId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        List<Integer> participatingUsers = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();

            psm = conn.prepareStatement(SELECT_PARTICIPANTS_BY_ACTIVITY);
            psm.setInt(1, activityId);
            rs = psm.executeQuery();

            while (rs.next()) {
                int volunteerId = rs.getInt("volunteer_id");
                participatingUsers.add(volunteerId);
            }

        } catch (SQLException e) {
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

        return participatingUsers;
    }

    public List<VolunteerActivityWithDate> getParticipatedActivitiesByUser(int userId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        AccountDAO dao = new AccountDAO();
        List<VolunteerActivityWithDate> participatedActivities = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();

            psm = conn.prepareStatement(SELECT_ACTIVITIES_BY_USER);
            psm.setInt(1, userId);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivityWithDate activity = new VolunteerActivityWithDate();
                activity.setActivity(getActivityById(rs.getInt("activity_id")));
                activity.setParticipationDate(rs.getTimestamp("registration_date"));
                activity.setoName(dao.GetUserName(activity.getActivity().getOrganizerId()));
                activity.setStatus();
                participatedActivities.add(activity);
            }

        } catch (SQLException e) {
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

        return participatedActivities;
    }

    public List<VolunteerActivity> getParticipatedActivitiesByOgnaizer(int userId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        List<VolunteerActivity> oActivities = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();

            psm = conn.prepareStatement(GET_WITH_OID);
            psm.setInt(1, userId);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));
                oActivities.add(activity);
            }

        } catch (SQLException e) {
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

        return oActivities;
    }

    public int getNewActivity() {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int rowCount = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_NEW_ACTIVITY);
            rs = psm.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
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
        return rowCount;
    }

    public int getTotalStatusRow(int cate) {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int rowCount = 0;
        try {
            conn = DBUtils.getConnection();

            switch (cate) {
                case 1: {
                    psm = conn.prepareStatement(GET_TOTAL_TRANG_THAI_SAP_DIEN_RA);
                    break;
                }
                case 2: {
                    psm = conn.prepareStatement(GET_TOTAL_TRANG_THAI_DANG_DIEN_RA);
                    break;
                }
                case 3: {
                    psm = conn.prepareStatement(GET_TOTAL_TRANG_THAI_DA_DIEN_RA);
                    break;
                }

            }
            rs = psm.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
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
        return rowCount;
    }

    public int getTotalRow() {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int rowCount = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_ROWS);
            rs = psm.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
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
        return rowCount;
    }

    public double getTotalAmountUsePayment(int id) {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        double Amount = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_AMOUNT_USE_PAYMENT);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if (rs.next()) {
                Amount = rs.getInt(1);
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
        return Amount;
    }

    public double getTotalAmountByUserId(int id) {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        double Amount = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_TOTAL_AMOUNT_BY_USER_ID);
            psm.setInt(1, id);
            rs = psm.executeQuery();
            if (rs.next()) {
                Amount = rs.getInt(1);
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
        return Amount;
    }

    public int getSearchTotalRow(String keyword) {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int rowCount = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_SEARCH_TOTAL_ROWS);
            psm.setString(1, "%" + keyword + "%");
            psm.setString(2, "%" + keyword + "%");
            rs = psm.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
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
        return rowCount;
    }

    public VolunteerActivity getActivityById(int eid) {
        VolunteerActivity activities = new VolunteerActivity();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_WITH_ID);
            psm.setInt(1, eid);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));
                activities = activity;
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

        return activities;
    }

    public List<Chi> getUsePaymentById(int eid) {
        List<Chi> tienChi = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        AccountDAO aDAO = new AccountDAO();
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_USE_PAYMENT);
            psm.setInt(1, eid);
            rs = psm.executeQuery();

            while (rs.next()) {

                Chi chi = new Chi();
                chi.setText(rs.getString("content"));
                chi.setMoney(rs.getDouble("amount"));
                tienChi.add(chi);
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

        return tienChi;
    }

    public List<Thu> getDonateActivityById(int eid) {
        List<Thu> tienthu = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        AccountDAO aDAO = new AccountDAO();
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_DONATE_BY_EVENT_ID);
            psm.setInt(1, eid);
            rs = psm.executeQuery();

            while (rs.next()) {

                Thu thu = new Thu();
                thu.setTenNguoiGui(aDAO.GetUserName(rs.getInt("giverID")));
                thu.setNguoiNhan(aDAO.GetUserName(rs.getInt("receiverID")));
                thu.setNoiDung(rs.getString("text"));
                thu.setNgayGui(rs.getTimestamp("transaction_date"));
                thu.setSoTien(rs.getDouble("amount"));
                thu.setHoatdong(getActivityById(rs.getInt("eventID")));
                tienthu.add(thu);
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

        return tienthu;
    }

    public void setUsePayment(String content, double amount, int aid) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(SET_USE_PAYMENT);
            psm.setString(1, content);
            psm.setDouble(2, amount);
            psm.setInt(3, aid);
            psm.executeUpdate();

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

    }

    public List<Thu> getDonateByUserId(int eid) {
        List<Thu> tienthu = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        AccountDAO aDAO = new AccountDAO();
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(GET_DONATE_BY_USER_ID);
            psm.setInt(1, eid);
            rs = psm.executeQuery();

            while (rs.next()) {

                Thu thu = new Thu();
                thu.setTenNguoiGui(aDAO.GetUserName(rs.getInt("giverID")));
                thu.setNguoiNhan(aDAO.GetUserName(rs.getInt("receiverID")));
                thu.setNoiDung(rs.getString("text"));
                thu.setNgayGui(rs.getTimestamp("transaction_date"));
                thu.setSoTien(rs.getDouble("amount"));
                thu.setHoatdong(getActivityById(rs.getInt("eventID")));
                tienthu.add(thu);
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

        return tienthu;
    }

    public VolunteerActivity getPendingActivityById(int eid) {
        VolunteerActivity activities = new VolunteerActivity();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement(FIND_PENDING_ACTIVITY);
            psm.setInt(1, eid);
            rs = psm.executeQuery();

            while (rs.next()) {
                VolunteerActivity activity = new VolunteerActivity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setActivityName(rs.getString("activity_name"));
                activity.setDescription(rs.getString("description"));
                activity.setStartDate(rs.getTimestamp("start_date"));
                activity.setEndDate(rs.getTimestamp("end_date"));
                activity.setLocation(rs.getString("location"));
                activity.setOrganizerId(rs.getInt("organizer_id"));
                activity.setNumberMember(rs.getInt("numberMemBer"));
                activity.setCreatedDate(rs.getTimestamp("created_date"));
                activity.setUpdatedDate(rs.getTimestamp("updated_date"));
                activity.setPhoto(rs.getString("photo"));
                activities = activity;
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

        return activities;
    }

}
