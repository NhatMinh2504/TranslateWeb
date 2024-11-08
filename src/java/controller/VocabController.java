/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.VocabularyDAO;
import entity.Vocabulary;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.IIOException;

/**
 *
 * @author DELL
 */
public class VocabController extends HttpServlet {

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
            out.println("<title>Servlet VocabController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VocabController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    VocabularyDAO vdao = new VocabularyDAO();

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
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    addVocab(request, response);
                    break;
                case "delete":
                    deleteVocab(request, response);
                    break;
                case "edit":
                    editVocab(request, response);
                    break;
                case "accept":
                    acceptVocab(request,response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

   private void addVocab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy dữ liệu từ form
    String englishWord = request.getParameter("englishWord");
    String vietnameseWord = request.getParameter("vietnameseWord");
    String type = request.getParameter("type");
    int roleId = Integer.parseInt(request.getSession().getAttribute("roleId").toString());

    if (englishWord == null || englishWord.isEmpty() || vietnameseWord == null || vietnameseWord.isEmpty() || type == null || type.isEmpty()) {
        request.setAttribute("error", "All fields are required!");
        request.getRequestDispatcher("addVocabModal.jsp").forward(request, response);
        return;
    }

    // Tạo đối tượng từ vựng
    Vocabulary vocab = new Vocabulary();
    vocab.setEnglishWord(englishWord);
    vocab.setVietnameseWord(vietnameseWord);
    vocab.setType(type);
    vocab.setStatus("pending");
    vocab.setDate(new Date()); // Thêm thời gian hiện tại

    // Kiểm tra từ vựng trùng lặp
    if (vdao.isVocabularyDuplicate(englishWord, type)) {
        request.setAttribute("error", "Duplicate word or type!");
        request.getRequestDispatcher("addVocabModal.jsp").forward(request, response);
        return;
    }

    // Thêm từ vựng vào cơ sở dữ liệu
    vdao.addVocabulary(vocab);

    if (roleId == 1) { // Admin
        List<Vocabulary> listV = vdao.findAll();
        request.setAttribute("listV", listV);
        request.setAttribute("message", "Vocabulary added successfully!");
        request.getRequestDispatcher("view/admin/dashboardPending.jsp").forward(request, response);
    } else { // Người dùng
        request.setAttribute("message", "Vocabulary contributed successfully and is awaiting admin approval.");
        request.getRequestDispatcher("view/translateWeb.jsp").forward(request, response);
    }
}

    private void deleteVocab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            vdao.deleteVocabulary(id);

            List<Vocabulary> listV = vdao.findAll();
            request.setAttribute("listV", listV);

            request.getRequestDispatcher("view/admin/dashboard.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());

        }
    }

    private void editVocab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String englishWord = request.getParameter("englishWord");
            String vietnameseWord = request.getParameter("vietnameseWord");
            String type = request.getParameter("type");

            // Tạo đối tượng Vocabulary mới với dữ liệu đã chỉnh sửa
            Vocabulary vocab = new Vocabulary();
            vocab.setId(id); // Đảm bảo rằng ID được thiết lập để cập nhật đúng bản ghi
            vocab.setEnglishWord(englishWord);
            vocab.setVietnameseWord(vietnameseWord);
            vocab.setType(type);

            // Gọi phương thức update từ vdao
            vdao.updateVocabulary(vocab);

            // Chuyển hướng về trang dashboard sau khi cập nhật
            List<Vocabulary> listV = vdao.findAll();
            request.setAttribute("listV", listV);
            request.getRequestDispatcher("view/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để theo dõi
        }

    }

   private void acceptVocab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int id = Integer.parseInt(request.getParameter("id"));
        vdao.UpdateStatus(id, "accepted"); // Truyền trực tiếp giá trị "Accepted"

        // Lấy danh sách từ vựng đã cập nhật và chuyển tiếp tới trang dashboard
        List<Vocabulary> listV = vdao.findAll();
        request.setAttribute("listV", listV);
        request.getRequestDispatcher("view/admin/dashboard.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
}
