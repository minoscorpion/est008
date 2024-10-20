/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

import java.sql.*;

/**
 *
 * @author rafae
 */

public class SequenceManager {

    public static long getValue(String sequenceName) throws SQLException {
        long sequenceValue = 0;
        String sql = "SELECT next value for " + sequenceName;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            stmt = ConectorBD.getPrepared(conn, sql);
            rs = ConectorBD.getSelect(stmt);
            if (rs.next()) {
                sequenceValue = rs.getLong(1);
            }
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }

        return sequenceValue;
    }
}
