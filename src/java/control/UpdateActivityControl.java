/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.ActivityDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "UpdateActivityControl", urlPatterns = {"/UpdateActivityControl"})
public class UpdateActivityControl extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateActivityControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateActivityControl at " + request.getContextPath() + "</h1>");
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            int activityId = Integer.parseInt(request.getParameter("activityId"));
            String activityName = request.getParameter("activityName");
            String description = request.getParameter("description");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String province = request.getParameter("province");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");

            String location = ward + "-" + district + "-" + province;
            int memberLimit = Integer.parseInt(request.getParameter("memberLimit"));

            // Xử lý tải lên hình ảnh (nếu có)
            // Xử lý ngày bắt đầu và ngày kết thúc (chuyển từ String sang Date)
            Date startDate = null;
            Date endDate = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                startDate = dateFormat.parse(startDateStr);
                endDate = dateFormat.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ActivityDAO activityDAO = new ActivityDAO();
            activityDAO.UpdateActivity(activityName, description, startDate, endDate, location, memberLimit,activityId);

        } catch (SQLException ex) {
            Logger.getLogger(UpdateActivityControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateActivityControl.class.getName()).log(Level.SEVERE, null, ex);
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
