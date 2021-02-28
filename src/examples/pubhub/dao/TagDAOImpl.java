package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Implementation for the TagDAO, responsible for querying the database for Tag objects.
 */
public class TagDAOImpl implements TagDAO{
	
	Connection conn = null;	// Our connection to the database
	PreparedStatement pStmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public List<Tag> getAllTags() {
		List<Tag> bookTags = new ArrayList<>();
		
		try{
			conn = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Book_Tags";		// Our SQL query
			pStmt = conn.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = pStmt.executeQuery();	// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Tag object with info for each row from our query result
				Tag bTag = new Tag();

				// Each variable in our Tag object maps to a column in a row from our results.
				bTag.setIsbn13(rs.getString("isbn_13"));
				bTag.setTagName(rs.getString("tag_name"));
				
				// Finally we add it to the list of Tag objects returned by this query.
				bookTags.add(bTag);
				
			}
			rs.close();	 // Closes ResultSet
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Tag objects populated by the DB.
		return bookTags;
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Tag> getTagsByTagName(String tagName) {
		List<Tag> bookTags = new ArrayList<>();
		
		try{
			conn = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Book_Tags WHERE tag_name LIKE ?";	// Note the ? in the query
			pStmt = conn.prepareStatement(sql);	// Creates the prepared statement from the query
			
			//This command populate the 1st '?' with the tag name and wildcards, between ' '
			pStmt.setString(1, "%" + tagName + "%");
			
			ResultSet rs = pStmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Tag object with info for each row from our query result
				Tag bTag = new Tag();

				// Each variable in our Tag object maps to a column in a row from our results.
				bTag.setIsbn13(rs.getString("isbn_13"));
				bTag.setTagName(rs.getString("tag_name"));
				
				// Finally we add it to the list of Tag objects returned by this query.
				bookTags.add(bTag);
				
			}
			rs.close();	 // Closes ResultSet
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Tag objects populated by the DB.
		return bookTags;
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Tag> getTagsByBook(Book book) {
		List<Tag> bookTags = new ArrayList<>();
		
		try{
			conn = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Book_Tags WHERE isbn_13 = ?";	// Note the ? in the query
			pStmt = conn.prepareStatement(sql);	// Creates the prepared statement from the query
			
			//This command populate the '?' with the isbn
			pStmt.setString(1, book.getIsbn13());
			
			ResultSet rs = pStmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Tag object with info for each row from our query result
				Tag bTag = new Tag();

				// Each variable in our Tag object maps to a column in a row from our results.
				bTag.setIsbn13(rs.getString("isbn_13"));
				bTag.setTagName(rs.getString("tag_name"));
				
				// Finally we add it to the list of Tag objects returned by this query.
				bookTags.add(bTag);
				
			}
			rs.close();	 // Closes ResultSet
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Tag objects populated by the DB.
		return bookTags;
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public List<Book> getBooksByTagName(String tagName) {
		List<Book> books = new ArrayList<>();
		
		TagDAO tDao = DAOUtilities.getTagDAO();
		List<Tag> tagList = tDao.getTagsByTagName(tagName);
		
		BookDAO bDao = DAOUtilities.getBookDAO();
		for(Tag tag : tagList) {
			Book bk = bDao.getBookByISBN(tag.getIsbn13());
			books.add(bk);
		}
		
		return books;
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public Tag getTagByISBN(String isbn) {
		Tag bTag = new Tag();
		
		try{
			conn = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Book_Tags WHERE isbn_13 = ?";	// Note the ? in the query
			pStmt = conn.prepareStatement(sql);	// Creates the prepared statement from the query
			
			//This command populate the '?' with the isbn
			pStmt.setString(1, isbn);
			
			ResultSet rs = pStmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// Each variable in our Tag object maps to a column in a row from our results.
				bTag.setIsbn13(rs.getString("isbn_13"));
				bTag.setTagName(rs.getString("tag_name"));				
			}
			rs.close();	 // Closes ResultSet
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the Tag object populated by the DB.
		return bTag;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	
	@Override
	public boolean addTag(String tagName, Book book) {
		try {
			conn = DAOUtilities.getConnection();
			String sql = "INSERT INTO Book_Tags VALUES (?, ?)";
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, book.getIsbn13());
			pStmt.setString(2, tagName);
				
			/* If we were able to add our tag to the DB, we want to return true. 
			   This if statement both executes our query, and looks at the return 
			   value to determine how many rows were changed */
			if(pStmt.executeUpdate() != 0) {
				System.out.println("\'" + tagName + "\'" + " added to \'" + book.getTitle() + "\'");
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean updateTag(Tag tag) {
		try {
			conn = DAOUtilities.getConnection();
			String sql = "UPDATE Book_Tags SET tag_name = ? WHERE isbn_13 = ?";
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, tag.getTagName());
			pStmt.setString(2, tag.getIsbn13());
			
			System.out.println(pStmt);
			
			if(pStmt.executeUpdate() != 0) {
				System.out.println("Updated to \'" + tag.getTagName() + "\'");
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeResources();
		}
		
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteTagByISBN(String isbn) {
		try {
			conn = DAOUtilities.getConnection();
			String sql = "DELETE FROM Book_Tags WHERE isbn_13 = ?";
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, isbn);
			
			if(pStmt.executeUpdate() != 0) {
				System.out.println("Tag removed");
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	@Override
	public boolean deleteTagByName(String tagName, Book book) {
		try {
			conn = DAOUtilities.getConnection();
			String sql = "DELETE FROM Book_Tags WHERE isbn_13 = ?";
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, tagName);
			
			if(pStmt.executeUpdate() != 0) {
				System.out.println("\'" + tagName + "\' removed from \'" + book.getTitle() + "\'");
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeResources();
		}
	}

	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if(pStmt != null) {
				pStmt.close();
			}
		}
		catch(SQLException e) {
			System.out.println("Could not close prepared statement!");
			e.printStackTrace();
		}
		
		try {
			if(conn != null) {
				conn.close();
			}
		}
		catch(SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
