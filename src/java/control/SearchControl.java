/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.ActivityDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author datka
 */
@WebServlet(name = "SearchControl", urlPatterns = {"/SearchControl"})
public class SearchControl extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            String searchTerm = request.getParameter("searchTerm");
            ActivityDAO DAO = new ActivityDAO();
            int spage = 1, spageSize = 6;
            int stotalPage = DAO.getSearchTotalRow(searchTerm);
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
                request.setAttribute("content", DAO.getSearchActivityFromTo(spage,
                        spageSize, searchTerm));

            }
            //  List<VolunteerActivity> activities = DAO.getActivityFromTo(startRecord, recordsPerPage);

            request.setAttribute("spage", spage);
            request.setAttribute("stotalPage", stotalPage);
            request.setAttribute("scurrentPage", spage);
            request.setAttribute("searchTerm", searchTerm);
            HttpSession session = request.getSession();
            session.setAttribute("urlHistory", "SearchControl");
            session.setAttribute("destPage", "SearchPage.jsp");
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {
            request.getRequestDispatcher("SearchPage.jsp").forward(request, response);
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
