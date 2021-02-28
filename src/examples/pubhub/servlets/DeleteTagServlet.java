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
@WebServlet("/DeleteTag")
public class DeleteTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= false;
		String isbn13 = request.getParameter("isbn13");

		TagDAO dao = DAOUtilities.getTagDAO();
		isSuccess = dao.deleteTagByISBN(isbn13);
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Tag successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("AllTags");
		}else {
			request.getSession().setAttribute("message", "There was a problem removing this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("viewAllTags.jsp").forward(request, response);
		}
	}

}
