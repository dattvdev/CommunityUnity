package control;

import dao.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.*;

public class HomeControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            ActivityDAO DAO = new ActivityDAO();
            int page = 1, pageSize = 6;
            int totalPage = DAO.getTotalRow();
            if (request.getParameter("page") != null) { // check param page
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (totalPage % pageSize == 0) { // calculator total page to showinformation
                totalPage = totalPage / pageSize;
            } else {
                totalPage = totalPage / pageSize + 1;
            }

            if (page > totalPage) {
                request.setAttribute("noContent", "No article here!");
            } else {
                request.setAttribute("content", DAO.getActivityFromTo(page,
                        pageSize));

            }
            //  List<VolunteerActivity> activities = DAO.getActivityFromTo(startRecord, recordsPerPage);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", page);

            HttpSession session = request.getSession();
            session.setAttribute("urlHistory", "HomeControl");
            session.setAttribute("destPage", "HomePage.jsp");
        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
        } finally {
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
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
    }

}
