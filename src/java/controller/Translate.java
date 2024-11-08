/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;
import dal.VocabularyDAO;
import entity.Vocabulary;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Translate extends HttpServlet {
     private VocabularyDAO vocabularyDAO = new VocabularyDAO(); 
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet Translate</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Translate at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        request.getRequestDispatcher("view/translateWeb.jsp").forward(request, response);
    }
    

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String englishWord = request.getParameter("englishWord");
        String vietnameseWord=request.getParameter("vietnameseWord");

        // Kiểm tra xem từ có rỗng hay không
        if (englishWord == null || englishWord.isEmpty()) {
            request.setAttribute("vietnameseMeaning", "Vui lòng nhập một từ hợp lệ.");
        } else {
            // Tìm từ vựng theo từ tiếng Anh
            Vocabulary vocabulary = vocabularyDAO.findByEnglishWord(englishWord);
            if (vocabulary != null) {
                // Gán nghĩa tiếng Việt cho attribute
                request.setAttribute("vietnameseMeaning", vocabulary.getVietnameseWord());
                request.setAttribute("type", vocabulary.getType());
            } else {
                request.setAttribute("vietnameseMeaning", "Từ không tồn tại trong từ điển.");
            }
        }

        
        request.getRequestDispatcher("view/translateWeb.jsp").forward(request, response);
    
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
