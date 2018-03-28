package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import com.revature.util.ConnectionPool;

import Entities.GradingFormat;

public class GradingFormatDaoImpl implements GradingFormatDao {

	public void createGradingFormat(GradingFormat gradingformat) {
		Connection conn = ConnectionPool.getPoolConnection();
		try{
			CallableStatement createGradingStmt = conn.prepareCall("{call INSERTGRADINGFORMAT(?,?,?,?)}");
			createGradingStmt.setString(1, gradingformat.getGrade());
			createGradingStmt.setInt(2, gradingformat.getNumValue());
			createGradingStmt.setInt(3, (gradingformat.getPass()? 0 : 1));
			createGradingStmt.setInt(4, gradingformat.getRequestID());
			createGradingStmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public GradingFormat retrieveGradingFormbyId(int id) {
		GradingFormat gradingformat = null;
		Connection conn = ConnectionPool.getPoolConnection();
		try{
			CallableStatement gradingStmt = conn.prepareCall("{? = call GETGRADINGFORMATBYID(?)}");
			gradingStmt.registerOutParameter(1, OracleTypes.CURSOR);
			gradingStmt.setInt(2,id);
			gradingStmt.execute();
			ResultSet rset = (ResultSet)gradingStmt.getObject(1);
			rset.next();
			gradingformat = new GradingFormat(rset.getString(1), 
					   rset.getInt(2),  (rset.getInt(3) == 0)? true : false, 
					   rset.getInt(4));
			}
		catch(SQLException e){
			e.printStackTrace();
		}
		return gradingformat;
	}

	public void deleteGradingFormat(int id) {
		Connection conn = ConnectionPool.getPoolConnection();
		try{
			CallableStatement deleteGFStmt = conn.prepareCall("{call DELETEGRADINGFORMATBYID(?)}");
			deleteGFStmt.setInt(1, id);
			deleteGFStmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

}
