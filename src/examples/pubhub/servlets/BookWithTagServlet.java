package examples.pubhub.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/*
 * This servlet will take you to the page with list of books based on tag search results.
 */
@WebServlet("/BookWithTag")
public class BookWithTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	PreparedStatement pStmt = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tName = request.getParameter("tagName");
		
		TagDAO dao = DAOUtilities.getTagDAO();
		List<Book> bookList = dao.getBooksByTagName(tName);
		
		request.getSession().setAttribute("books", bookList);
		
		request.getRequestDispatcher("bookWithTag.jsp").forward(request, response);
		
	}
	
}
