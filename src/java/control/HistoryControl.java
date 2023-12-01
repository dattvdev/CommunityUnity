/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.ActivityDAO;
import entity.Account;
import entity.Thu;
import entity.VolunteerActivity;
import entity.VolunteerActivityWithDate;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "HistoryControl", urlPatterns = {"/HistoryControl"})
public class HistoryControl extends HttpServlet {

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
            HttpSession session = request.getSession();
            ActivityDAO actDAO = new ActivityDAO();
            int vid = ((Account) session.getAttribute("LOGIN_USER")).getAccId();
            int role = ((Account) session.getAttribute("LOGIN_USER")).getRole();
            List<VolunteerActivityWithDate> listAct= actDAO.getParticipatedActivitiesByUser(vid);
            List<VolunteerActivity> listOAct= new ArrayList<>();
            if (role==2 || role ==0){
                listOAct = actDAO.getParticipatedActivitiesByOgnaizer(vid);
            }
            double tongDonate = actDAO.getTotalAmountByUserId(vid);
            
            List<Thu> listThu  = actDAO.getDonateByUserId(vid);
            session.setAttribute("tongDonate", tongDonate);
            session.setAttribute("listAct", listAct);
            session.setAttribute("listOAct", listOAct);
            session.setAttribute("listThu", listThu);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistoryControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            request.getRequestDispatcher("History.jsp").forward(request, response);
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
