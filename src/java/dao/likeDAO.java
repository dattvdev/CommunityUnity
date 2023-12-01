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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ytbhe
 */
public class likeDAO {

    private String GETTOTAl_LIKE = "SELECT COUNT(*) FROM LikesBlogs WHERE id_post = ?";
    private String INSERT = "insert into LikesBlogs values(?,?)";
    private String DELETE = "DELETE FROM LikesBlogs WHERE  id_clicker = ? AND  id_post = ?";
    private String CHECKVALID = "select * FROM LikesBlogs WHERE  id_clicker = ? AND  id_post = ?";

    public boolean insertAccount(int id_UserClick, int id_Post) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(INSERT);

                stm.setInt(1, id_UserClick);
                stm.setInt(2, id_Post);

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
    public boolean checkValid(int id_UserClick, int id_Post) {
     
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
          
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(CHECKVALID);
            ps.setInt(1, id_UserClick);
            ps.setInt(2, id_Post);
   
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }
    public boolean deleteLikePost(int id_UserClick, int id_Post) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE);

                stm.setInt(1, id_UserClick);
                stm.setInt(2, id_Post);

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

    public int getToTalLike(int idPost) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GETTOTAl_LIKE);
                stm.setInt(1, idPost);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(likeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(likeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(likeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        likeDAO like = new likeDAO();


        
        System.out.println(""+like.checkValid(4, 1));
    }

}
