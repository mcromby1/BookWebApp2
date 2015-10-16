package edu.wctc.mrc.bookwebapp2.controller;

import edu.wctc.mrc.bookwebapp2.entity.*;
import edu.wctc.mrc.bookwebapp2.service.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The main controller for author-related activities
 *
 * @author Matthew Cromby
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    // NO MAGIC NUMBERS!
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String MOD_CREATE_PAGE = "/modifycreate.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String CREATE_ACTION = "create";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
    private static final String MODIFY_ACTION = "modify";

    @Inject
    private AuthorFacade authService;

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
        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);
        try {
            String pk = null;
            String authorName = null;
            String createDate = null;
            Author author = null;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            switch (action) {
                case LIST_ACTION:
                    refreshList(request, authService);
                    destination = LIST_PAGE;
                    break;
                case ADD_ACTION:
                    destination = MOD_CREATE_PAGE;
                    break;
                case UPDATE_ACTION:
                    pk = request.getParameter("authorId");
                    author = authService.find(new Integer(pk));
                    authorName = request.getParameter("authorName");
                    createDate = request.getParameter("dateCreated");

                    if (!(authorName.isEmpty())) {
                        author.setAuthorName(authorName);
                    }
                    if (!(createDate.isEmpty())) {
                        Date newDate = format.parse(createDate);
                        author.setDateCreated(newDate);
                    }
                    authService.edit(author);
                    refreshList(request, authService);
                    destination = LIST_PAGE;
                    break;
                case DELETE_ACTION:
                    pk = request.getParameter("authorId");
                    author = authService.find(new Integer(pk));
                    authService.remove(author);
                    refreshList(request, authService);
                    destination = LIST_PAGE;
                    break;
                case CREATE_ACTION:
                    authorName = request.getParameter("authorName");
                    createDate = request.getParameter("dateCreated");
                    author = new Author(0);
                    if (!(authorName.isEmpty())) {
                        author.setAuthorName(authorName);
                    }
                    if (!(createDate.isEmpty())) {
                        Date newDate = format.parse(createDate);
                        author.setDateCreated(newDate);
                    } else {
                        Date newDate = new Date();
                        author.setDateCreated(newDate);
                    }
                    authService.create(author);
                    refreshList(request, authService);
                    destination = LIST_PAGE;
                    break;
                case MODIFY_ACTION:
                    pk = request.getParameter("authorId");

                    Author modAuthor = authService.find(new Integer(pk));
                    request.setAttribute("author", modAuthor);
                    destination = MOD_CREATE_PAGE;
                    break;
                default:
                    // no param identified in request, must be an error
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = LIST_PAGE;
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();

    }

    private void refreshList(HttpServletRequest request, AuthorFacade authService) throws SQLException, ClassNotFoundException, Exception {
        List<Author> authors = authService.findAll();
        request.setAttribute("authors", authors);
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
