package edu.wctc.mrc.bookwebapp2.controller;

import edu.wctc.mrc.bookwebapp2.entity.Author;
import edu.wctc.mrc.bookwebapp2.entity.Book;
import edu.wctc.mrc.bookwebapp2.service.AuthorFacade;
import edu.wctc.mrc.bookwebapp2.service.BookFacade;
import java.util.*;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user test
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String AUTHOR_LIST_PAGE = "/listAuthors.jsp";
    private static final String BOOK_LIST_PAGE = "/listBooks.jsp";
    private static final String BOOK_MOD_CREATE_PAGE = "/modifycreatebook.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String CREATE_ACTION = "create";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
    private static final String MODIFY_ACTION = "modify";

    @Inject
    private BookFacade bookService;

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

        String destination = BOOK_LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);

        String bookId = null;
        String bookTitle = null;
        String bookISBN = null;
        String authorId = null;
        Book book = null;

        switch (ACTION_PARAM) {
            case LIST_ACTION:
                refreshList(request, bookService);
                destination = BOOK_LIST_PAGE;
                break;
            case ADD_ACTION:
                destination = BOOK_MOD_CREATE_PAGE;
                break;
            case UPDATE_ACTION:
                bookId = request.getParameter("bookID");
                bookTitle = request.getParameter("bookTitle");
                bookISBN = request.getParameter("bookISBN");
                authorId = request.getParameter("authorId");

                book = bookService.find(bookId);

                break;
            case CREATE_ACTION:

                break;
        }

    }

    private void refreshList(HttpServletRequest request, BookFacade bookService) {

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
