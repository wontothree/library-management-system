package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// model
import com.cruds.model.Book;
import com.cruds.model.Member;

import com.cruds.model.Author;
import com.cruds.model.Issue;
import com.cruds.model.Student;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class BookDAO {

	// add book

	/**
	 * @brief Add Book
	 * @param book
	 * @return
	 */
	public boolean addBook(Book book) {
		// sql insert statement table 'books'
		String sql = "INSERT INTO books (book_id, book_title, author_name, publisher, publication_date, purchase_date, category, is_domestic) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		int rows = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, book.getBookId()); 			// book_id
			ps.setString(2, book.getBookTitle()); 	    // title
			ps.setString(3, book.getAuthorName());    	// author_name
			ps.setString(4, book.getPublisher()); 		// publisher
			ps.setString(5, book.getPublicationDate()); 	// publication_date
			ps.setString(6, book.getPurchaseDate()); 		// purchase_date
			ps.setString(7, book.getCategory()); 			// category
			ps.setBoolean(8, book.getDomestic()); 			// domestic

			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 처리
		}

		// if success to insert
		return rows > 0;
	}

	// list all books

	/**
	 * @brief List all Books
	 * @return DefaultTableModel containing book details
	 */
	public DefaultTableModel getTableBooks() {
		// SQL query: Select book information from the 'books' table
		String sql = "SELECT book_id, book_title, author_name, publisher, publication_date, purchase_date, category, is_domestic FROM books";

		// Column names: Adjusted to match the SQL query results
		Vector<String> colNames = new Vector<>();
		colNames.add("ID");              // book_id
		colNames.add("Title");           // book_title
		colNames.add("Author");               // author_name
		colNames.add("Publisher");            // publisher
		colNames.add("Publication Date");     // publication_date
		colNames.add("Purchase Date");        // purchase_date
		colNames.add("Category");             // category
		colNames.add("Domestic/International"); // is_domestic

		Vector<Vector<String>> data = new Vector<>();

		// Database connection and query execution
		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// Reading data from the ResultSet and adding to the table data
			while (rs != null && rs.next()) {
				Vector<String> row = new Vector<>();
				row.add(rs.getString("book_id"));          // book_id
				row.add(rs.getString("book_title"));       // book_title
				row.add(rs.getString("author_name"));      // author_name
				row.add(rs.getString("publisher"));        // publisher
				row.add(rs.getString("publication_date")); // publication_date
				row.add(rs.getString("purchase_date"));    // purchase_date
				row.add(rs.getString("category"));         // category
				row.add(rs.getString("is_domestic"));      // is_domestic (Domestic/International)
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Create and return DefaultTableModel object
		return new DefaultTableModel(data, colNames);
	}

	/**
	 * @brief Search books by title
	 * @param title The title to search for
	 * @return A DefaultTableModel containing the search results
	 */
	public DefaultTableModel getByTitle(String title) {
		// SQL query: Ensure it matches the intended data and uses the title parameter
		String sql = "SELECT book_id, book_title, author_name, publisher, category " +
				"FROM books " +
				"WHERE LOWER(book_title) LIKE ?";

		// Column names for the table model
		Vector<String> colNames = new Vector<>();
		colNames.add("ID");
		colNames.add("Title");
		colNames.add("Author");
		colNames.add("Publisher");
		colNames.add("Category");

		// Data to hold the rows
		Vector<Vector<String>> data = new Vector<>();

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + title.toLowerCase() + "%");

			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				Vector<String> row = new Vector<>();
				row.add(rs.getString("book_id"));             // Book ID
				row.add(rs.getString("book_title"));          // Book Title
				row.add(rs.getString("author_name"));         // Author
				row.add(rs.getString("publisher"));           // Publisher
				row.add(rs.getString("category"));            // Category
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new DefaultTableModel(data, colNames);
	}

	public DefaultTableModel getByAuthor(String name) {
		String sql = "SELECT book_id, book_title, author_name, publisher, category " +
				"FROM books " +
				"WHERE LOWER(author_name) LIKE ?";

		Vector<String> colNames = new Vector<>();
		colNames.add("ID");
		colNames.add("Title");
		colNames.add("Author");
		colNames.add("Publisher");
		colNames.add("Category");

		Vector<Vector<String>> data = new Vector<>();

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name.toLowerCase() + "%");

			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				Vector<String> row = new Vector<>();
				row.add(rs.getString("book_id"));          // Book ID
				row.add(rs.getString("book_title"));       // Book Title
				row.add(rs.getString("author_name"));      // Author
				row.add(rs.getString("publisher"));        // Publisher
				row.add(rs.getString("category"));         // Category
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new DefaultTableModel(data, colNames);
	}

	public DefaultTableModel getByCategory(String category) {
		String sql = "SELECT book_id, book_title, author_name, publisher, category " +
				"FROM books " +
				"WHERE LOWER(category) LIKE ?";

		Vector<String> colNames = new Vector<>();
		colNames.add("ID");
		colNames.add("Title");
		colNames.add("Author");
		colNames.add("Publisher");
		colNames.add("Category");

		Vector<Vector<String>> data = new Vector<>();

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + category.toLowerCase() + "%");

			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				Vector<String> row = new Vector<>();
				row.add(rs.getString("book_id"));          // Book ID
				row.add(rs.getString("book_title"));       // Book Title
				row.add(rs.getString("author_name"));      // Author
				row.add(rs.getString("publisher"));        // Publisher
				row.add(rs.getString("category"));         // Category
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new DefaultTableModel(data, colNames);
	}

	public DefaultTableModel getByIsbn(String id) {
		// ISBN을 기준으로 검색하는 SQL 쿼리
		String sql = "SELECT book_id, book_title, author_name, publisher, category " +
				"FROM books " +
				"WHERE LOWER(book_id) LIKE ?";

		// 테이블 열 이름 정의
		Vector<String> colNames = new Vector<>();
		colNames.add("ID");
		colNames.add("Title");
		colNames.add("Author");
		colNames.add("Publisher");
		colNames.add("Category");

		// 결과를 저장할 데이터 벡터
		Vector<Vector<String>> data = new Vector<>();

		try (Connection conn = DBConnectionManager.getConnection()) {
			// 쿼리 준비
			PreparedStatement ps = conn.prepareStatement(sql);
			// 입력값으로 받은 ISBN을 소문자로 변환하여 LIKE 검색
			ps.setString(1, "%" + id.toLowerCase() + "%");

			// 쿼리 실행
			ResultSet rs = ps.executeQuery();

			// 결과를 데이터 벡터에 추가
			while (rs != null && rs.next()) {
				Vector<String> row = new Vector<>();
				row.add(rs.getString("book_id"));     // ISBN
				row.add(rs.getString("book_title"));    // Title
				row.add(rs.getString("author_name"));   // Author
				row.add(rs.getString("publisher"));     // Publisher
				row.add(rs.getString("category"));      // Category
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// DefaultTableModel 반환
		return new DefaultTableModel(data, colNames);
	}

	// issue book

	/**
	 * @brief issue book
	 * @param phone
	 * @return
	 */
	public DefaultTableModel getStudentbyUsn(String phone)
	{
		String sql = "select member_id, full_name, phone_number from members where LOWER(phone_number) = ?";
		Vector<String> colNames = new Vector<>();
		colNames.add("ID");
		colNames.add("Full Name");
		colNames.add("Phone");

		Vector<Vector<String>> data = new Vector<>();

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone.toLowerCase());

			ResultSet rs = ps.executeQuery();
			while(rs != null && rs.next())
			{
				Vector<String> row = new Vector<>();
				row.add(rs.getString("member_id"));
				row.add(rs.getString("full_name"));
				row.add(rs.getString("phone_number"));
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new DefaultTableModel(data, colNames);
	}

	/**
	 * @brief Insert issued book record into the database
	 * @param bi Issue object containing book and member information
	 * @return true if the book issue was successful, otherwise false
	 */
	public boolean issueBook(Issue bi) {
		// 먼저 책이 이미 대출되었는지 확인하는 쿼리
		String checkSql = "SELECT COUNT(*) FROM issues WHERE book_id = ? AND return_date IS NULL";

		// 책이 이미 대출되었는지 여부를 나타내는 변수
		boolean isBookAlreadyIssued = false;

		try (Connection conn = DBConnectionManager.getConnection()) {
			// 대출 중인 책이 있는지 확인하는 쿼리 실행
			PreparedStatement checkPs = conn.prepareStatement(checkSql);
			checkPs.setString(1, bi.getBookId());
			ResultSet rs = checkPs.executeQuery();

			// 쿼리 결과 확인
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isBookAlreadyIssued = true;  // 책이 이미 대출된 경우
				}
			}

			// 책이 이미 대출 중이라면, 더 이상 대출할 수 없도록 반환
			if (isBookAlreadyIssued) {
				System.out.println("Book is already issued to someone else.");
				return false;
			}

			// 책이 대출되지 않은 상태라면, 책 대출 기록을 데이터베이스에 삽입
			String sql = "INSERT INTO issues(member_id, book_id) VALUES(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bi.getMemberId());  // Set member_id
			ps.setString(2, bi.getBookId());    // Set book_id

			// 쿼리 실행하여 대출 기록을 데이터베이스에 삽입
			int rows = ps.executeUpdate();

			// 대출 기록이 성공적으로 추가된 경우 출력
			if (rows > 0) {
				System.out.println("Book issued successfully for member: " + bi.getMemberId());
				return true;  // 대출 성공
			} else {
				System.out.println("Failed to issue the book.");
				return false;  // 대출 실패
			}
		} catch (SQLException e) {
			// 예외 처리 및 디버깅 정보 출력
			System.err.println("Error occurred while issuing the book: " + e.getMessage());
			e.printStackTrace();  // Stack trace for debugging
		}

		// 기본적으로 실패한 경우 반환
		return false;
	}

	// return book


	public DefaultTableModel listBookByUsn(String usn)
	{
//		String sql = "select bi.issue_id, b.book_title, bi.usn, s.name, bi.issue_date, bi.return_date, bi.book_isbn  from book b, student s, book_issue bi where b.book_isbn = bi.book_isbn and bi.usn = s.usn and LOWER(bi.usn) = ?";

		Vector<String> colNames = new Vector<>();
		colNames.add("Member Name");
		colNames.add("Book ID");
		colNames.add("Book Title");
		colNames.add("Issue Date");
		colNames.add("Return Date");
		colNames.add("Overdue Fee");

		Vector<Vector<String>> data = new Vector<>();
//
//		try(Connection conn = DBConnectionManager.getConnection())
//		{
//			PreparedStatement ps = conn.prepareStatement(sql);
//                        ps.setString(1, usn);
//                        ResultSet rs = ps.executeQuery();
//
//                        while(rs != null && rs.next())
//			{
//				Vector<String> row = new Vector<>();
//				row.add(String.valueOf(rs.getInt(1)));
//				row.add(rs.getString(2));
//                                row.add(rs.getString(3));
//                                row.add(rs.getString(4));
//                                row.add(String.valueOf(rs.getDate(5)));
//                                row.add(String.valueOf(rs.getDate(6)));
//                                row.add(rs.getString(7));
//				data.add(row);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return new DefaultTableModel(data, colNames);
	}

	// sign up

	/**
	 * @brief sign up
	 * @param
	 * @return
	 */
	public boolean addMember(Member member)
	{
		String sql = "INSERT INTO MEMBERS (member_id, full_name, phone_number, street_address, email_address) values(?, ?, ?, ?, ?)";

		int rows = 0;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, member.getMemberId());
			ps.setString(2, member.getFullName());
			ps.setString(3, member.getPhoneNumber());
			ps.setString(4, member.getStreetAddress());
			ps.setString(5, member.getEmailAddress());

			rows = ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return rows >= 0;
	}

	public boolean addAuthor(Author author)
	{
		String sql = "insert into author(author_name, author_mail_id, book_isbn) values(?, ?, ?)";
		int rows = 0;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, author.getName());
			ps.setString(2,  author.getEmail());
			ps.setString(3, author.getBook_isbn());

			rows = ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return rows > 0;
	}

	public boolean addStudent(Student stud)
	{
		String sql = "insert into student(usn, name) values(?, ?)";
		int rows = 0;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stud.getUsn());
			ps.setString(2,  stud.getName());

			rows = ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return rows >= 0;
	}
	
	public boolean studentExist(Student stud)
	{
		String sql = "select usn, name from student where usn = ?";
		boolean flag = false;;
		
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stud.getUsn());			
			ResultSet rs = ps.executeQuery();
			if(rs != null && rs.next())
			{
				flag = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
        
	public DefaultTableModel getStudentbyName(String name)
	{
		String sql = "select usn, name from student where LOWER(name) = ?";
		Vector<String> colNames = new Vector<>();
		colNames.add("USN");
		colNames.add("Name");
                
                Vector<Vector<String>> data = new Vector<>();
		
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			while(rs != null && rs.next())
			{
				Vector<String> row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
                                data.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new DefaultTableModel(data, colNames);
	}

	// ----
        
	public DefaultTableModel listIssuedBooks()
	{
		String sql = "select bi.issue_id, b.book_title, bi.usn, s.name, bi.issue_date, bi.return_date, bi.book_isbn  from book b, student s, book_issue bi where b.book_isbn = bi.book_isbn and bi.usn = s.usn";
                Vector<String> colNames = new Vector<>();
		colNames.add("Member Name");
		colNames.add("Book ID");
		colNames.add("Book Title");
		colNames.add("Issue Date");
		colNames.add("Return Date");
		colNames.add("Overdue Fee");
		
		Vector<Vector<String>> data = new Vector<>();
		
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                   
			while(rs != null && rs.next())
			{
				Vector<String> row = new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
                                row.add(rs.getString(3));
                                row.add(rs.getString(4));
                                row.add(String.valueOf(rs.getDate(5)));
                                row.add(String.valueOf(rs.getDate(6)));
                                row.add(rs.getString(7));
				data.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return new DefaultTableModel(data, colNames);
	}
	
	public DefaultTableModel getBookToReturn(Date curDate)
	{
		String sql = "select bi.issue_id, b.book_title, bi.usn, s.name, bi.issue_date, bi.return_date, bi.book_isbn  from book b, student s, book_issue bi where b.book_isbn = bi.book_isbn and bi.usn = s.usn and bi.return_date = ?";
                Vector<String> colNames = new Vector<>();
		colNames.add("Member Name");
		colNames.add("Book ID");
		colNames.add("Book Title");
		colNames.add("Issue Date");
		colNames.add("Return Date");
		colNames.add("Overdue Fee");
		
		Vector<Vector<String>> data = new Vector<>();
                java.sql.Date cdate = new java.sql.Date(curDate.getTime());
		
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setDate(1, cdate);
                        ResultSet rs = ps.executeQuery();
                        
                        while(rs != null && rs.next())
			{
				Vector<String> row = new Vector<>();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
                                row.add(rs.getString(3));
                                row.add(rs.getString(4));
                                row.add(String.valueOf(rs.getDate(5)));
                                row.add(String.valueOf(rs.getDate(6)));
                                row.add(rs.getString(7));
				data.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return new DefaultTableModel(data, colNames);
	}

	// ----

	public boolean returnBook(int id, String isbn)
	{
		String sql = "Delete from book_issue where issue_id = ? ";

		String sqlCount = "update book set no_of_books = no_of_books+1 where book_isbn = ? ";

		int rows = 0;
		int rowsCount = 0;

		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rows = ps.executeUpdate();

			PreparedStatement psCount = conn.prepareStatement(sqlCount);
			psCount.setString(1, isbn);
			rowsCount = psCount.executeUpdate();


		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return ((rows > 0) && (rowsCount > 0));
	}

	public String[] getAllCategory()
	{
		String sql = "Select unique(category) from book";
		List<String> list = new ArrayList<>();
		list.add("Select");
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();


			while(rs != null && rs.next())
	{
				list.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		String[] category = new String[list.size()];

		for(int i=0; i<list.size();i++)
		{
			category[i] = list.get(i);
		}

		return category;
	}
}
