/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.ActivityDAO;
import dao.BlogsDAO;
import entity.Blogs;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ytbhe
 */
public class SearchBlog extends HttpServlet {

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

        String text = request.getParameter("text");

        BlogsDAO a = new BlogsDAO();

        int spage = 1, spageSize = 6;
        int stotalPage = a.getSearchTotalRow(text);
        if (request.getParameter("spage") != null) { // check param page
            spage = Integer.parseInt(request.getParameter("spage"));
        }

        if (stotalPage % spageSize == 0) { // calculator total page to showinformation
            stotalPage = stotalPage / spageSize;
        } else {
            stotalPage = stotalPage / spageSize + 1;
        }
        if (spage > stotalPage) {
            request.setAttribute("noContent", "No article here!");
        } else {
            request.setAttribute("blogs", a.getSearchBlogFromTo(spage, spageSize, text));

        }
       
        request.setAttribute("spage", spage);
        request.setAttribute("stotalPage", stotalPage);
        request.setAttribute("scurrentPage", spage);
        request.setAttribute("text", text);
        HttpSession session = request.getSession();
        session.setAttribute("urlHistory", "searchblog");

        request.getRequestDispatcher("searchblogs.jsp").forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
