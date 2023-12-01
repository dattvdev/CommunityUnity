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
@WebServlet(name = "CatePageControl", urlPatterns = {"/CatePageControl"})
public class CatePageControl extends HttpServlet {

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
            out.println("<title>Servlet CatePageControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CatePageControl at " + request.getContextPath() + "</h1>");
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            ActivityDAO DAO = new ActivityDAO();
            int cate = Integer.parseInt(request.getParameter("cate"));
            int page = 1, pageSize = 6;
            int totalPage = DAO.getTotalStatusRow(cate);
            request.setAttribute("catee", cate);
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
                request.setAttribute("content", DAO.getActivityStatusFromTo(page,
                        pageSize,cate));
                
            }
            //  List<VolunteerActivity> activities = DAO.getActivityFromTo(startRecord, recordsPerPage);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", page);
            session.setAttribute("urlHistory", "CatePageControl");
            session.setAttribute("destPage", "CatePage.jsp");
        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
        } finally {
            request.getRequestDispatcher("CatePage.jsp").forward(request, response);
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
