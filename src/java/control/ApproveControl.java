/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.ActivityDAO;
import dao.NotificateDAO;
import entity.Account;
import entity.VolunteerActivity;
import java.io.IOException;

import java.sql.SQLException;
import java.util.Date;
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
@WebServlet(name = "ApproveControl", urlPatterns = {"/ApproveControl"})
public class ApproveControl extends HttpServlet {

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
            ActivityDAO actDAO = new ActivityDAO();
            request.setAttribute("pendingEvents", actDAO.getPendingActivity());

        } catch (SQLException ex) {
            Logger.getLogger(ApproveControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("Approve.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        int eId = Integer.parseInt(request.getParameter("eId"));
        int check = Integer.parseInt(request.getParameter("check"));
        session.setAttribute("a", eId);
        session.setAttribute("b", check);
 session.setAttribute("c", "null");
        ActivityDAO acDAO = new ActivityDAO();
        if (check == 1) {
            acDAO.CreateActivity(acDAO.getPendingActivityById(eId));
            // ADD NOTIFICATION 
            int organizerId=acDAO.getPendingActivityById(eId).getOrganizerId();
        
            NotificateDAO noti = new NotificateDAO();
            Date date = new Date();
            try {
                noti.addNotification(organizerId, "Your Event is already approved by Admin ", date, "EventDetailControl?id="+acDAO.getNewActivity(), organizerId);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ApproveControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                acDAO.removePendingActivity(eId);
            } catch (SQLException ex) {
                Logger.getLogger(ApproveControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (check == 2) {
            try {
                VolunteerActivity acti = acDAO.getActivityById(eId);
                NotificateDAO noti = new NotificateDAO();
                int organizerId=acDAO.getPendingActivityById(eId).getOrganizerId();
                Date date = new Date();
                try {
                    noti.addNotification(organizerId, "Your Event is already refused by Admin ", date, null, organizerId);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ApproveControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                acDAO.removePendingActivity(eId);
            } catch (SQLException ex) {
                Logger.getLogger(ApproveControl.class.getName()).log(Level.SEVERE, null, ex);
            }
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
