/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.AccountDAO;
import dao.BlogsDAO;
import dao.CommentDAO;
import dao.NotificateDAO;
import dao.likeDAO;
import entity.Blogs;
import entity.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ytbhe
 */
public class BlogsComment extends HttpServlet {

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
            out.println("<title>Servlet BlogsComment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogsComment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String commentMessage = request.getParameter("cMessage");
        int userId = Integer.parseInt(request.getParameter("userId"));
        PrintWriter out = response.getWriter();
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

            likeDAO l = new likeDAO();
            int id_blogs = Integer.parseInt(request.getParameter("postId"));
            int receiver = 0;
            BlogsDAO blog = new BlogsDAO();
            for(Blogs b : blog.getAllBlogs()){
                if(b.getBlogId() == id_blogs){
                    receiver = a.GetUSERIDByName(b.getAuthor());
                    break;
                    
                }
            }
            
            BlogsDAO dao = new BlogsDAO();
            Blogs b = dao.getBlogByID(id_blogs);
            int count = l.getToTalLike(id_blogs);

            CommentDAO o = new CommentDAO();
            o.insertComment(id_blogs, userId, commentMessage);
            //add notification
            NotificateDAO noti = new NotificateDAO();
            Date date = new Date();
  
            try {
                noti.addNotification(receiver , "You Received 1 Comment From  "+a.GetUserName(userId), date,  "blogsdetail?id="+id_blogs, userIDLG);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LikeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //end 
            
            List<Comment> list = o.getAllComment(id_blogs);
            int countcmt = o.getToTalComment(id_blogs);

            //test
            request.setAttribute("userIDLG", userIDLG);
            request.setAttribute("countcmt", countcmt);
            request.setAttribute("comments", list);
            request.setAttribute("blogsdetails", b);
            request.setAttribute("count", count);
            request.getRequestDispatcher("blogsdetails.jsp").forward(request, response);

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
