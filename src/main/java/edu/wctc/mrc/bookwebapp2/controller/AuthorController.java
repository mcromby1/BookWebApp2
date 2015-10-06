package edu.wctc.mrc.bookwebapp2.controller;

import edu.wctc.mrc.bookwebapp2.model.Author;
import edu.wctc.mrc.bookwebapp2.model.*;
import edu.wctc.mrc.bookwebapp2.model.AuthorService;
import edu.wctc.mrc.bookwebapp2.model.DBAccessorPlan;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The main controller for author-related activities
 *
 * @author jlombardo
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

        try {
            String destination = LIST_PAGE;
            String action = request.getParameter(ACTION_PARAM);
            String dbClassName = this.getServletContext().getInitParameter("DBAccessorPlan");
            Class c = Class.forName(dbClassName);
            DBAccessorPlan db = (DBAccessorPlan) c.newInstance();
            AuthorService authService = null;

            String dataSourceParam = this.getServletContext().getInitParameter("dataSource");
            try {
                if (!(dataSourceParam.isEmpty())) {
                    Context ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup(dataSourceParam);
                    AuthorDAOPlan authDao = new AuthorDAO(new MySQLDb(), ds);
                    authService = new AuthorService(authDao);
                } else {

                    String dbDriver = this.getServletContext().getInitParameter("dbDriver");
                    String dbURL = this.getServletContext().getInitParameter("dbURL");
                    String dbUserName = this.getServletContext().getInitParameter("dbUserName");
                    String dbPassword = this.getServletContext().getInitParameter("dbPassword");

                    /*
                     For now we are hard-coding the strategy objects into this
                     controller. In the future we'll auto inject them from a config
                     file. Also, the DAO opens/closes a connection on each method call,
                     which is not very efficient. In the future we'll learn how to use
                     a connection pool to improve this.
                     */
//        DBAccessorPlan db = new MySQLDb();
                    AuthorDAO authDao
                            = new AuthorDAO(db, dbDriver,
                                    dbURL, dbUserName, dbPassword);
                    authService = new AuthorService(authDao);
                }

                /*
                 Here's what the connection pool version looks like.
                 */

                /*
                 Determine what action to take based on a passed in QueryString
                 Parameter
                 */
                String pk = null;
                String authorName = null;
                String createDate = null;
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
                        authorName = request.getParameter("authorName");
                        createDate = request.getParameter("dateCreated");

                        if (!(authorName.isEmpty())) {
                            authService.updateAuthor("author_name", "author_id", authorName, pk);
                        }
                        if (!(createDate.isEmpty())) {
                            authService.updateAuthor("date_created", "author_id", createDate, pk);
                        }
                        refreshList(request, authService);
                        destination = LIST_PAGE;
                        break;
                    case DELETE_ACTION:
                        pk = request.getParameter("authorId");
                        authService.deleteAuthor(Integer.parseInt(pk));
                        refreshList(request, authService);
                        destination = LIST_PAGE;
                        break;
                    case CREATE_ACTION:
                        authorName = request.getParameter("authorName");
                        createDate = request.getParameter("dateCreated");

                        List<String> columns = new ArrayList<>();
                        List<String> colValues = new ArrayList<>();
                        columns.add("author_name");
                        colValues.add(authorName);
                        columns.add("date_created");
                        colValues.add(createDate);

                        authService.addAuthor(columns, colValues);

                        refreshList(request, authService);
                        destination = LIST_PAGE;
                        break;
                    case MODIFY_ACTION:
                        pk = request.getParameter("authorId");
                        Author modAuthor = authService.searchAuthorByID(pk);

                        request.setAttribute("author", modAuthor);
                        destination = MOD_CREATE_PAGE;
                        break;

                    default:
                        // no param identified in request, must be an error
                        request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                        destination = LIST_PAGE;
                        break;
                }
            } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                request.setAttribute("errMsg", e.getCause().getMessage());
            } catch (Exception e) {
                request.setAttribute("errMsg", e.getCause().getMessage());
            }
            // Forward to destination page
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

        }
    }

    private void refreshList(HttpServletRequest request, AuthorService authService) throws SQLException, ClassNotFoundException, Exception {
        List<Author> authors;
        authors = authService.getAllAuthors();
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
