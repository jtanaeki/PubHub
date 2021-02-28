package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class ViewBookDetailsServlet
 */

// This is a "View" servlet, and has been named accordingly. All it does is send the user to a new JSP page
// But it also takes the opportunity to populate the session or request with additional data as needed.
@WebServlet("/ViewTagDetails")
public class ViewTagDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// The tagDetails.jsp page needs to have the details of the selected book saved to the request,
		// Otherwise it won't know what details to display. Ergo, we need to fetch those details before we
		// Actually redirect the user.
		String isbn13 = request.getParameter("isbn13");
		
		TagDAO dao = DAOUtilities.getTagDAO();
		Tag tag = dao.getTagByISBN(isbn13);
		
		if(tag.getTagName() == null) {
			
			tag.setIsbn13(isbn13);
			request.setAttribute("tag", tag);
			
			request.getRequestDispatcher("addTag.jsp").forward(request, response);
			
		}
		else {
			
			request.setAttribute("tag", tag);
			
			// We can use a forward here, because if a user wants to refresh their browser on this page,
			// it will just show them the most recent details for their book. There's no risk of data
			// miss-handling here.
			request.getRequestDispatcher("tagDetails.jsp").forward(request, response);
			
		}
		
	}

}
