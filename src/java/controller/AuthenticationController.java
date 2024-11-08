/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class AuthenticationController extends HttpServlet {

    AccountDAO dao = new AccountDAO();

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
            out.println("<title>Servlet AuthenticationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenticationController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action") != null
                ? request.getParameter("action")
                : "";
        String url;

        switch (action) {
            case "login":
                url = "view/authen/login.jsp";
                break;
            case "sign-up":
                url = "view/authen/register.jsp";
                break;
            case "signout":

                url = "view/authen/login.jsp";
                break;

            default:
                throw new AssertionError();
        }

        request.getRequestDispatcher(url).forward(request, response);
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
        String action = request.getParameter("action") != null
                ? request.getParameter("action")
                : "";
        if (action.equals("login")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Kiểm tra tài khoản đăng nhập từ cơ sở dữ liệu
            Account account = dao.login(username, email, password);

            if (account != null) {

                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                session.setAttribute("roleId", account.getRoleId());
                response.sendRedirect("view/translateWeb.jsp");
            } else {

                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("view/authen/login.jsp").forward(request, response);
            }
        }
        if (action.equals("signout")) {
            HttpSession session = request.getSession(false); // Get the existing session, if any
            if (session != null) {
                session.invalidate(); // Invalidate the session to log out the user
            }
            request.getRequestDispatcher("view/authen/login.jsp").forward(request, response);
        }
        if (action.equals("signup")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Kiểm tra xem tài khoản đã tồn tại chưa
            if (dao.accountExists(username, email)) {
                request.setAttribute("error", "Username or email already exists.");
                request.getRequestDispatcher("view/authen/register.jsp").forward(request, response);
            } else {
                // Tạo tài khoản mới
                Account newAccount = new Account();
                newAccount.setUsername(username);
                newAccount.setPassword(password);
                newAccount.setEmail(email);
                dao.addAccount(newAccount);

                // Chuyển hướng về trang đăng nhập
                request.getRequestDispatcher("view/authen/login.jsp").forward(request, response);
            }
        }
        if (action.equals("logout")) {
          
            request.getRequestDispatcher("view/translateWeb.jsp").forward(request, response);
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
