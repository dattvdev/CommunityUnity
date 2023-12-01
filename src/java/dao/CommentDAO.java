/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBUtils;
import entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ytbhe
 */
public class CommentDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public CommentDAO() {
        try {
            conn = new DBUtils().getConnection();
        } catch (Exception e) {
        }
    }

    public List<Comment> getAllComment(int postid) {
        List<Comment> list = new ArrayList<>();
        String query = "SELECT A. comment_id, A.post_id, A.comment_content,  comment_author_id,B.name, A.comment_date, B.photo FROM comments A JOIN Accounts B ON A.comment_author_id = B.UserID \n"
                + "where post_id = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            while (rs.next()) {

                int commentId = rs.getInt(1);
                int postId = rs.getInt(2);

                String commentContent = rs.getString(3);
                int commentAuthorId = rs.getInt(4);
                String name = rs.getString(5);
                Date commentDate = rs.getDate(6);
                String photo = rs.getString(7);

                Comment comment = new Comment(commentId, postId, commentContent, commentAuthorId, name, commentDate, photo);
                list.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getToTalComment(int idPost) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("	SELECT COUNT(*) FROM comments WHERE post_id = ?");
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

    public void insertComment(int postID, int userID, String content) {

        String query = "insert into comments values (?,?,?,GETDATE())";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, postID);
            ps.setInt(3, userID);

            ps.setString(2, content);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void deleteComment(int id_comment) {

        String query = "  delete from comments where comment_id = ?;";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id_comment);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CommentDAO a = new CommentDAO();

    }

}
