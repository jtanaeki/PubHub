package examples.pubhub.servlets;

import java.io.IOException;
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

/**
 * Servlet implementation class AddTagServlet
 */
@WebServlet("/AddTag")
public class AddTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addTag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= false;
		String isbn13 = request.getParameter("isbn13");
		String tagName = request.getParameter("tagName");

		BookDAO bDao = DAOUtilities.getBookDAO();
		Book book = bDao.getBookByISBN(isbn13);
		
		if(tagName != null){
			Tag tag = new Tag();
			tag.setIsbn13(isbn13);
			tag.setTagName(tagName);
			request.setAttribute("tag", tag);
			
			TagDAO tDao = DAOUtilities.getTagDAO();
			isSuccess = tDao.addTag(tagName, book);
		}else {
			//ASSERT: couldn't add tag. Add failed.
			isSuccess = false;
		}
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Tag successfully added");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("BookPublishing");
		}else {
			request.getSession().setAttribute("message", "There was a problem adding this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("addTag.jsp").forward(request, response);
		}
	}

}
