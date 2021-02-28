package examples.pubhub.model;

import java.time.LocalDate;

public class Book {

	private String isbn13;			// International Standard Book Number, unique
	private String title;
	private String author;
	private LocalDate publishDate;	// Date of publish to the website
	
	private double price;
	
	private byte[] content;
	
	private String tagName;			//Used to return both Book and Tag entries in one table

	// Constructor used when no date is specified
	public Book(String isbn, String title, String author, double price, byte[] content) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishDate = LocalDate.now();
		this.content = content;
	}
	
	// Constructor used when a date is specified
	public Book(String isbn, String title, String author, double price, byte[] content, LocalDate publishDate) {
		this.isbn13 = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishDate = publishDate;
		this.content = content;
	}
	
	// Default constructor
	public Book() {
		this.isbn13 = null;
		this.title = null;
		this.author = null;
		this.price = 0.0;
		this.publishDate = LocalDate.now();
		this.content = null;
	}
	
	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Book [isbn13=" + isbn13 + ", title=" + title + ", author=" + author + 
				", publishDate=" + publishDate + ", price=" + price + ", content=" + content + "]";
	}
	
}
