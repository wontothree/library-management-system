package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

// model
import com.cruds.model.Book;
import com.cruds.model.Member;
import com.cruds.model.Issue;

//import com.cruds.model.Author;
//import com.cruds.model.Student;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class BookDAO {

	// [page] add book

	/**
	 * @brief [page] Add Book
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

	// [page] search book

	/**
	 * @brief [page] search book
	 * @brief [combobox] title
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

	/**
	 * @brief [page] search book
	 * @brief [combobox] author
	 */
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

	/**
	 * @brief [page] search book
	 * @brief [combobox] category
	 */
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

	/**
	 * @brief [page] search page
	 * @brief [combobox] book id
	 */
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

	// [page]  list all books

	/**
	 * @brief [page] list all books
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

	// issue book

	/**
	 * @brief [page] issue book
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

	// [page] issue book

	/**
	 * @brief [page] issue book
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

	// [page] return book

	/**
	 * @brief [page] return book
	 * @brief [button] search
	 */
	public DefaultTableModel listBookByUsn(String usn) {
		// 컬럼 이름 설정
		Vector<String> colNames = new Vector<>();
		colNames.add("Member Name");
		colNames.add("Issue ID");
		colNames.add("Book ID");
		colNames.add("Book Title");
		colNames.add("Issue Date");
		colNames.add("Overdue Fee");

		// 데이터를 저장할 벡터
		Vector<Vector<Object>> data = new Vector<>();

		// SQL 쿼리 (반납일자가 NULL인 조건 추가)
		String sql = "SELECT " +
				"    m.full_name AS member_name, " +
				"    i.issue_id, " +
				"    i.book_id, " +
				"    b.book_title, " +
				"    i.issue_date, " +
				"    CASE " +
				"        WHEN i.return_date IS NULL THEN " +
				"            CASE " +
				"                WHEN DATEDIFF(CURDATE(), i.issue_date) > 30 THEN (DATEDIFF(CURDATE(), i.issue_date) - 30) * 500 " +
				"                ELSE 0 " +
				"            END " +
				"        ELSE 0 " +  // 반납일자가 있는 경우는 연체료가 0으로 처리
				"    END AS overdue_fee " +
				"FROM " +
				"    members m " +
				"JOIN " +
				"    issues i ON m.member_id = i.member_id " +
				"JOIN " +
				"    books b ON i.book_id = b.book_id " +
				"WHERE " +
				"    LOWER(m.member_id) = ? AND i.return_date IS NULL"; // 반납일자가 NULL인 경우만 필터링

		try (Connection conn = DBConnectionManager.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			// 입력된 USN 값을 SQL에 바인딩
			ps.setString(1, usn.toLowerCase());

			// 쿼리 실행
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Vector<Object> row = new Vector<>();
					row.add(rs.getString("member_name")); // 멤버 이름
					row.add(rs.getInt("issue_id")); // issue_id 추가
					row.add(rs.getString("book_id")); // 책 ID
					row.add(rs.getString("book_title")); // 책 제목
					row.add(rs.getDate("issue_date")); // 대출 날짜
					row.add(rs.getInt("overdue_fee")); // 연체료 (반납일자가 NULL인 경우만 연체료 계산)
					data.add(row);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 데이터와 컬럼을 사용하여 DefaultTableModel 반환
		return new DefaultTableModel(data, colNames);
	}

	/**
	 * @brief [page] return book
	 * @brief [button] return
	 */
	public boolean returnBook(int issue_id)
	{
		// SQL 쿼리: 반납일자 업데이트
		String sqlUpdateReturnDate = "UPDATE issues SET return_date = ? WHERE issue_id = ?";

		int rowsUpdated = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			// PreparedStatement로 SQL 쿼리 실행
			PreparedStatement ps = conn.prepareStatement(sqlUpdateReturnDate);
			ps.setDate(1, new java.sql.Date(System.currentTimeMillis())); // 현재 날짜로 return_date 설정
			ps.setInt(2, issue_id);

			// SQL 실행하여 반납일자 갱신
			rowsUpdated = ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// 반납일자 갱신 성공 여부 반환
		return (rowsUpdated > 0);
	}

	// [page] sign up / withdrawal

	/**
	 * @brief [page] sign up / withdrawal
	 * @brief [button] add
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

	/**
	 * @brief [page] sign up / withdrawal
	 * @brief [button] withdrawal
	 */
	public boolean withdrawalMember(Member member)
	{
		String sql = "UPDATE members SET is_withdrawn = ? WHERE member_id = ?";

		int rows = 0;

		try (Connection conn = DBConnectionManager.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			// 탈퇴 여부를 true
			ps.setBoolean(1, true);
			ps.setString(2, member.getMemberId());

			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows > 0; // 정상적으로 업데이트되었으면 true 반환
	}

	/**
	 * @brief [page] sign up / withdrawal
	 * @brief [page] month end settlement
	 */
	public boolean deleteWithdrawnMembers() {
		try (Connection connection = DBConnectionManager.getConnection()) {
			// Begin the transaction
			connection.setAutoCommit(false);

			// Check if there are any entries in the 'issues' table with a NULL return_date
			String checkIssuesQuery = "SELECT COUNT(*) AS count FROM issues WHERE return_date IS NULL";
			try (PreparedStatement checkIssuesStmt = connection.prepareStatement(checkIssuesQuery);
				 ResultSet rs = checkIssuesStmt.executeQuery()) {
				if (rs.next() && rs.getInt("count") > 0) {
					// Display a warning message if there are unreturned issues
					JOptionPane.showMessageDialog(null,
							"Cannot proceed with deletion. There are unreturned items in the 'issues' table.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
					return false; // Return false if unreturned items are found
				}
			}

			// Retrieve the member_id of users with is_withdrawn = true from the 'members' table
			String selectMembersQuery = "SELECT member_id FROM members WHERE is_withdrawn = true";
			try (PreparedStatement selectMembersStmt = connection.prepareStatement(selectMembersQuery);
				 ResultSet rs = selectMembersStmt.executeQuery()) {

				// Delete data from the 'issues' and 'members' tables for each member_id
				while (rs.next()) {
					int memberId = rs.getInt("member_id");

					// Delete entries from the 'issues' table for the current member_id
					String deleteIssuesQuery = "DELETE FROM issues WHERE member_id = ?";
					try (PreparedStatement deleteIssuesStmt = connection.prepareStatement(deleteIssuesQuery)) {
						deleteIssuesStmt.setInt(1, memberId);
						deleteIssuesStmt.executeUpdate();
					}

					// Delete the member from the 'members' table for the current member_id
					String deleteMembersQuery = "DELETE FROM members WHERE member_id = ?";
					try (PreparedStatement deleteMembersStmt = connection.prepareStatement(deleteMembersQuery)) {
						deleteMembersStmt.setInt(1, memberId);
						deleteMembersStmt.executeUpdate();
					}
				}
			}

			// Commit the transaction
			connection.commit();
			// Display an informational message upon successful deletion
			JOptionPane.showMessageDialog(null,
					"All withdrawn members have been successfully deleted.",
					"Information",
					JOptionPane.INFORMATION_MESSAGE);

			return true; // Return true if all operations are successful

		} catch (SQLException e) {
			e.printStackTrace(); // Print exception details
			try (Connection connection = DBConnectionManager.getConnection()) {
				connection.rollback(); // Roll back the transaction in case of an error
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace(); // Log rollback failure
			}
			// Display an error message in case of failure
			JOptionPane.showMessageDialog(null,
					"An error occurred, and the operation was aborted. Please contact the administrator.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return false; // Return false in case of failure
		}
	}

	/**
	 * @brief [page] sign up / withdrawal
	 * @brief [page] month end settlement
	 */
	public boolean upgradeVipMembers()
	{
		//
		return false;
	}











//
//	public boolean studentExist(Student stud)
//	{
//		String sql = "select usn, name from student where usn = ?";
//		boolean flag = false;;
//
//		try(Connection conn = DBConnectionManager.getConnection())
//		{
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, stud.getUsn());
//			ResultSet rs = ps.executeQuery();
//			if(rs != null && rs.next())
//			{
//				flag = true;
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//
//		return flag;
//	}

	// book to be returned today
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
}
