package examples.pubhub.test;

import java.util.List;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TestBookTagDAO {

	public static void main(String[] args) {
		BookDAO bDao = DAOUtilities.getBookDAO();
		TagDAO tDao = DAOUtilities.getTagDAO();
		
//	    List <Tag> tags = tDao.getTagsByTagName("Example Tag Name");
//	    System.out.println(tags);
//	    
//		List<Tag> tagList = tDao.getTagsByTagName("Fiction");
//	    System.out.println(tagList);
//	    
	    List<Book> books = bDao.getAllBooks();
	    System.out.println(books);
//	    
//	    List<Book> bookList = tDao.getBooksByTagName("Fiction");
//	    System.out.println(bookList);
//		
//		Tag tag = tDao.getTagByISBN("7222");
//		System.out.println(tag);
	}

}
