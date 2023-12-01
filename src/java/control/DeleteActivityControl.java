/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.AccountDAO;
import dao.ActivityDAO;
import dao.ReportDAO;
import entity.Account;
import entity.UserPending;
import entity.VolunteerActivity;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "DeleteActivityControl", urlPatterns = {"/DeleteActivityControl"})
public class DeleteActivityControl extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            int eid = Integer.parseInt(request.getParameter("id"));
            ActivityDAO aDAO = new ActivityDAO();
            ReportDAO rDAO = new ReportDAO();
            aDAO.removeParticipationByActivityID(eid);
            aDAO.removePaymentByActivityID(eid);
            aDAO.removePendingUserByActivityID(eid);
            aDAO.removeUsePaymentByActivityID(eid);
            rDAO.deleteReport(eid);
            aDAO.removeActivity(eid);

            request.getRequestDispatcher("home").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EventDetailControl.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            int eid = Integer.parseInt(request.getParameter("id"));
            ActivityDAO aDAO = new ActivityDAO();
            ReportDAO rDAO = new ReportDAO();
            aDAO.removeParticipationByActivityID(eid);
            aDAO.removePaymentByActivityID(eid);
            aDAO.removePendingUserByActivityID(eid);
            aDAO.removeUsePaymentByActivityID(eid);
            rDAO.deleteReport(eid);
            aDAO.removeActivity(eid);

            request.getRequestDispatcher("home").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EventDetailControl.class.getName()).log(Level.SEVERE, null, ex);
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