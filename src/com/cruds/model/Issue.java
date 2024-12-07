package com.cruds.model;

import java.sql.Date;  // Use java.sql.Date for SQL Date handling

public class Issue {

	private int id;  // Issue ID (auto-generated in DB)
	private String memberId;  // member_id corresponding to the student
	private String bookId;  // book_id corresponding to the ISBN of the book
	private Date issueDate;  // issue_date (auto-generated as CURRENT_TIMESTAMP)
	private Date returnDate;  // return_date (can be NULL initially)

	// Constructor with memberId, bookId, and returnDate (since issueDate is auto-handled by DB)
	public Issue(String memberId, String bookId) {
		this.memberId = memberId;
		this.bookId = bookId;
	}

	// Getters and setters for all fields
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		// Override to return a readable string representation of the issue
		return "Issue ID: " + id + ", Member ID: " + memberId + ", Book ID: " + bookId +
				", Issue Date: " + issueDate + ", Return Date: " + returnDate;
	}
}
