/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.AccountDAO;
import dao.ActivityDAO;
import entity.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author datka
 */
public class EventDetailControl extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        try {
            HttpSession session = request.getSession();
            int eid = Integer.parseInt(request.getParameter("id"));
            ActivityDAO aDAO = new ActivityDAO();
            String name = ((Account) session.getAttribute("LOGIN_USER")).getUserName();
            AccountDAO dao = new AccountDAO();
            int check = 0;

            if (aDAO.isPendingUserExists(dao.GetUSERID(name), eid)) {
                check = 1;
            } else if (aDAO.isParticipationExist(dao.GetUSERID(name), eid)) {
                check = 2;
            }
            List<Integer> pendinglistid = new ArrayList<>();
            List<UserPending> pendinglist = new ArrayList<>();
            List<UserPending> memberlist = new ArrayList<>();
            List<Integer> memberListid = new ArrayList<>();
            pendinglistid = aDAO.getPendingUserByActivity(eid);
            memberListid = aDAO.getParticipatingUsersByActivity(eid);
            for (int a : pendinglistid) {
                Account x = dao.getAnAccountById(a);
                if (x != null) {
                    pendinglist.add(new UserPending(x.getUserName(), x.getPhoto(), x.getAccId()));

                }
            }
            for (int a : memberListid) {
                Account x = dao.getAnAccountById(a);
                if (x != null) {
                    memberlist.add(new UserPending(x.getUserName(), x.getPhoto(), x.getAccId()));

                }
            }
            String status = "Sắp diễn ra";
            VolunteerActivity detail = aDAO.getActivityById(eid);
            List<Thu> donateThu = aDAO.getDonateActivityById(eid);
            double tongThu=0;
            for (Thu a : donateThu){
                tongThu +=a.getSoTien();
            }
            Date currentDate = new Date();
            if (currentDate.after(detail.getStartDate()) && currentDate.before(detail.getEndDate())) {
                status = "Đang diễn ra";
            } else if (currentDate.after(detail.getEndDate())) {
                status = "Đã kết thúc";
            }
            List<Chi> donateChi= aDAO.getUsePaymentById(eid);
            double tongChi= aDAO.getTotalAmountUsePayment(eid);
            request.setAttribute("donateThu", donateThu);
            request.setAttribute("tongThu",tongThu);
            request.setAttribute("donateChi", donateChi);
            request.setAttribute("tongChi",tongChi);
            request.setAttribute("oname",dao.GetUserName(detail.getOrganizerId()));
            request.setAttribute("pendinglist", pendinglist);
            request.setAttribute("memberlist", memberlist);
            request.setAttribute("member",memberlist.size());
            request.setAttribute("userID", dao.GetUSERID(name));
            request.setAttribute("check", check);
            request.setAttribute("status",status);
            request.setAttribute("detail",detail );
            request.getRequestDispatcher("EventDetail.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventDetailControl.class.getName()).log(Level.SEVERE, null, ex);
        }
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