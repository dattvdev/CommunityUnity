/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AccountDAO;
import dao.BlogsDAO;
import dao.CommentDAO;
import dao.likeDAO;
import entity.Blogs;
import entity.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.NotificateDAO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ytbhe
 */
public class LikeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LikeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LikeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action (Like or Unlike)

        String email = "";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();

                if (name.equals("email")) {
                    email = cookie.getValue().trim();
                }
            }

            AccountDAO a = new AccountDAO();
            int userIDLG = a.GetUSERID(a.getUserName_byEmail(email));

            String action = request.getParameter("action");
            likeDAO l = new likeDAO();

            // Get the user ID and post ID from the request
            int userId = Integer.parseInt(request.getParameter("userId"));
            int postId = Integer.parseInt(request.getParameter("postId"));
            int receiver = 0;
            BlogsDAO blog = new BlogsDAO();
            for (Blogs b : blog.getAllBlogs()) {
                if (b.getBlogId() == postId) {
                    receiver = a.GetUSERIDByName(b.getAuthor());
                    break;

                }
            }
            NotificateDAO noti = new NotificateDAO();
            Date date = new Date();
            // Your logic to handle liking or unliking the post
            if ("Like".equals(action)) {
                if (l.checkValid(userId, postId)) {

                    try {
                        l.deleteLikePost(userId, postId);
                        noti.deleteNotification(userIDLG);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LikeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    l.insertAccount(userId, postId);

                    try {
                        noti.addNotification(receiver, "You Received 1 Like From  " + a.GetUserName(userId), date, "blogsdetail?id=" + postId, userIDLG);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LikeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            // Redirect back to the post.jsp page after processing
            int count = l.getToTalLike(postId);

            BlogsDAO dao = new BlogsDAO();
            Blogs b = dao.getBlogByID(postId);
            CommentDAO o = new CommentDAO();
            List<Comment> list = o.getAllComment(postId);

            int countcmt = o.getToTalComment(postId);
            request.setAttribute("receiver", receiver);
            request.setAttribute("userIDLG", userIDLG);
            request.setAttribute("countcmt", countcmt);
            request.setAttribute("comments", list);
            request.setAttribute("blogsdetails", b);
            request.setAttribute("blogsdetails", b);
            request.setAttribute("count", count);
            request.setAttribute("id", postId);

            request.getRequestDispatcher("blogsdetails.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
