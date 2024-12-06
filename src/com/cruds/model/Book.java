package com.cruds.model;

public class Book {

	private String bookTitle;
	private String authorName;
	private String publisher;
	private String publicationDate;
	private String purchaseDate;
	private String bookId;
	private String category;
	private boolean isDomestic;

	// Constructor
	public Book(String bookTitle, String authorName, String publisher, String publicationDate, String purchaseDate, String bookId, String category, boolean isDomestic) {
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.purchaseDate = purchaseDate;
		this.bookId = bookId;
		this.category = category;
		this.isDomestic = isDomestic;
	}

	// Getters and Setters
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getDomestic() {
		return isDomestic;
	}
	public void setDomestic(boolean domestic) {
		this.isDomestic = domestic;
	}
}
