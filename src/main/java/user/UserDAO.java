package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "Hyeon0204.";
			Class.forName("com.mysql.jdbc.cj.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;
				}
				else {
					return 0;  
				}
			}
			return -1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2;		
	}

	public int join(User user) throws SQLException {
	    String SQL ="INSERT INTO USER VALUES(?, ?, ?, ?, ?)";
	    pstmt = conn.prepareStatement(SQL);
	    pstmt.setString(1, user.getUserID());
	    pstmt.setString(2, user.getUserPassword());
	    pstmt.setString(3, user.getUserName());
	    pstmt.setString(4, user.getUserGender());
	    pstmt.setString(5, user.getUserEmail());
	    return pstmt.executeUpdate();
	}

}
