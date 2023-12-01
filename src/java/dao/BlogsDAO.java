/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBUtils;
import entity.Account;
import entity.Blogs;

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
public class BlogsDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public BlogsDAO() {
        try {
            conn = new DBUtils().getConnection();
        } catch (Exception e) {
        }
    }

    public List<Blogs> getAllBlogs() {
        List<Blogs> list = new ArrayList<>();
        String query = "SELECT A. blog_id, A.title, A.content, B.name, A.date,A.category, A.photo,A.shortcontent,A.pending\n"
                + "FROM Blogs A\n"
                + "JOIN Accounts B ON A.author = B.UserID; ";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                int blogsID = rs.getInt(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String author = rs.getString(4);

                Date date = rs.getDate(5);
                String cate = rs.getString(6);
                String photo = rs.getString(7);
                String shortcontent = rs.getString(8);
                int pending = rs.getInt(9);
                Blogs blogs = new Blogs(blogsID, title, content, author, date, cate, photo, shortcontent, pending);
                list.add(blogs);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public int getSearchTotalRow(String keyword) {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        int rowCount = 0;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement("SELECT count(*) FROM Blogs a join Accounts b on a.author=b.UserID WHERE (shortcontent LIKE ? or title like ?  or category like ? or b.name like ? ) and a.pending =1;");
            psm.setString(1, "%" + keyword + "%");
            psm.setString(2, "%" + keyword + "%");
            psm.setString(3, "%" + keyword + "%");
            psm.setString(4, "%" + keyword + "%");
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
            psm = conn.prepareStatement("  SELECT count(*)\n"
                    + "               FROM Blogs A\n"
                    + "                JOIN Accounts B ON A.author = B.UserID where a.pending =1");
    
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

    public Blogs getBlogByID(int id) {

        try {
            String query = "  SELECT A. blog_id, A.title, A.content, B.name, A.date,A.category, A.photo, A.shortcontent, A.pending\n"
                    + "             FROM Blogs A\n"
                    + "                JOIN Accounts B ON A.author = B.UserID\n"
                    + "				where blog_id = ?; ";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int blogsID = rs.getInt(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String author = rs.getString(4);

                Date date = rs.getDate(5);
                String cate = rs.getString(6);
                String photo = rs.getString(7);
                String shortcontent = rs.getString(8);
                int pending = rs.getInt(9);
                Blogs p = new Blogs(blogsID, title, content, author, date, cate, photo, shortcontent, pending);

                return p;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void insertBlogs(String title, String content, String shortContent, int auhorID, String cate, String photo) {
        Connection conn = null;
        PreparedStatement psm = null;

        try {
            try {
                conn = DBUtils.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BlogsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            psm = conn.prepareStatement("INSERT INTO Volunteer.dbo.Blogs VALUES (?,?,?,?,GETDATE(),?,?,0);");
            psm.setString(1, title);
            psm.setString(2, content);
            psm.setString(3, shortContent);
            psm.setInt(4, auhorID);
            psm.setString(5, cate);
            psm.setString(6, photo);
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

    public boolean pendingBlogs(int id_blogs) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(" UPDATE Blogs\n"
                        + "SET pending = 1\n"
                        + "WHERE blog_id = ?;");

                stm.setInt(1, id_blogs);

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

    public boolean deleteBlogs(int id_blogs) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("  delete from LikesBlogs where id_post = ?;   delete from comments where post_id = ?; delete from Blogs where blog_id = ?;");

                stm.setInt(1, id_blogs);
                stm.setInt(2, id_blogs);
                stm.setInt(3, id_blogs);

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

    public int getToTalCate(String category) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("  select COUNT(*)  from [Blogs] where category=? and pending=1;");
                stm.setString(1, category);
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
     public List<Blogs> getBlogFromTo(int page, int pageSize) {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<Blogs> list = new ArrayList<>();
     
        List<Blogs> list2 = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement("  select a.*,b.name from Blogs a join Accounts b on a.author=b.UserID where  a.pending=1");
            rs = psm.executeQuery();

            while (rs.next()) {
                int blogsID = rs.getInt("blog_ID");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("name");

                Date date = rs.getDate("date");
                String cate = rs.getString("category");
                String photo = rs.getString("photo");
                String shortcontent = rs.getString("shortcontent");
                int pending = rs.getInt("pending");
                Blogs blogs = new Blogs(blogsID, title, content, author, date, cate, photo, shortcontent, pending);
                list.add(blogs);
            }
          

            if (to > list.size() + 1) {
                to = list.size() + 1;
            }
            for (int i = from - 1; i < to; i++) {
                list2.add(list.get(i));
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

        return list2;
    }
    public List<Blogs> getSearchBlogFromTo(int page, int pageSize, String keyword) {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<Blogs> list = new ArrayList<>();
        List<Blogs> list1 = new ArrayList<>();
        List<Blogs> list2 = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            psm = conn.prepareStatement("  select a.*,b.name from Blogs a join Accounts b on a.author=b.UserID where  a.pending=1");
            rs = psm.executeQuery();

            while (rs.next()) {
                int blogsID = rs.getInt("blog_ID");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("name");

                Date date = rs.getDate("date");
                String cate = rs.getString("category");
                String photo = rs.getString("photo");
                String shortcontent = rs.getString("shortcontent");
                int pending = rs.getInt("pending");
                Blogs blogs = new Blogs(blogsID, title, content, author, date, cate, photo, shortcontent, pending);
                list.add(blogs);
            }
            AccountDAO accDao = new AccountDAO();

            for (int i = 0; i < list.size(); i++) {
                String tt = list.get(i).getTitle().toLowerCase();
                String cate = list.get(i).getCategory().toLowerCase();
                String shortct = list.get(i).getShortContent().toLowerCase();

                String author = list.get(i).getAuthor().toLowerCase();

                if (tt.indexOf(keyword.toLowerCase()) != -1 || cate.indexOf(keyword.toLowerCase()) != -1 || shortct.indexOf(keyword.toLowerCase()) != -1 || author.indexOf(keyword.toLowerCase()) != -1) {
                    list1.add(list.get(i));

                }
            }
            if (to > list1.size() + 1) {
                to = list1.size() + 1;
            }
            for (int i = from - 1; i < to; i++) {
                list2.add(list1.get(i));
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

        return list2;
    }

    public static void main(String[] args) {
        BlogsDAO a = new BlogsDAO();

      a.deleteBlogs(14);

    }
}
