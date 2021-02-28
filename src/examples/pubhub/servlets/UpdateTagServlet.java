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
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateTag")
public class UpdateTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= false;
		String isbn13 = request.getParameter("isbn13");
		
		TagDAO dao = DAOUtilities.getTagDAO();
		Tag tag = dao.getTagByISBN(isbn13);
		
		if(tag != null){
			// The only fields we want to be updatable is the tag name. A new ISBN has to be applied for
			// And a new tag needs to be re-published.
			tag.setTagName(request.getParameter("tagName"));
			request.setAttribute("tag", tag);
			isSuccess = dao.updateTag(tag);
		}else {
			//ASSERT: couldn't find tag with isbn. Update failed.
			isSuccess = false;
		}
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Tag successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewTagDetails?isbn13=" + isbn13);
		}else {
			request.getSession().setAttribute("message", "There was a problem updating this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("tagDetails.jsp").forward(request, response);
		}
	}

}
