package com.cruds.db;

import com.cruds.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief function for login validity
 */
public class AdminDAO {
    
    public boolean isValidAdmin(Admin admin)
    {
        String sql = "select admin_id, password from admin where admin_id = ? and password = ?";
        
        try(Connection conn = DBConnectionManager.getConnection())
	    {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, admin.getId());
            ps.setString(2, admin.getPassword());
            ResultSet rs = ps.executeQuery();
            while(rs != null && rs.next())
            {
                if(rs.getString(1).equals(admin.getId()) && rs.getString(2).equals(admin.getPassword()))
                {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
