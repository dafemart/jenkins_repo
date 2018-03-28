package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.revature.util.ConnectionPool;

import Entities.ReimbursementRequest;

public class ReimbursementRequestDaoImpl implements ReimbursementRequestDao {

	public void createReimbursementRequest(ReimbursementRequest req, Connection conn) {
		try{
			CallableStatement createReimbReqStmt = conn.prepareCall("{call INSERTREIMBREQUEST(?,?,?,?,?,?,?,?)}");
			createReimbReqStmt.setInt(1, req.getEmployeeID());
			createReimbReqStmt.setInt(2, req.getEventID());
			createReimbReqStmt.setDate(3, req.getStartingDate());
			createReimbReqStmt.setString(4, req.getCourseLocation());
			createReimbReqStmt.setInt(5, req.getCourseCost());
			createReimbReqStmt.setString(6, req.getJustification());
			createReimbReqStmt.setString(7, req.getCurrentIssuer());
			createReimbReqStmt.setInt(8, req.getStatus());
			createReimbReqStmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public ReimbursementRequest retrieveReimbRequestById(int id, Connection conn) {
		ReimbursementRequest request = null;
		try{
			CallableStatement retrieveReimbReqStmt = conn.prepareCall("{? = call GETREQUESTBYREQUESTID(?)}");
			retrieveReimbReqStmt.registerOutParameter(1, OracleTypes.CURSOR);
			retrieveReimbReqStmt.setInt(2, id);
			retrieveReimbReqStmt.execute();
			ResultSet rset = (ResultSet)retrieveReimbReqStmt.getObject(1);
			if(rset.next()){
			request = new ReimbursementRequest(rset.getInt(1), rset.getInt(2), rset.getInt(3),
					                           rset.getDate(4), rset.getString(5), rset.getInt(6),
					                           rset.getString(7), rset.getString(8), rset.getInt(9));
			}
			rset.close();
			retrieveReimbReqStmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return request;
	}

	public ArrayList<ReimbursementRequest> retrieveReimbRequestsByEmpId(int id, Connection conn) {
		ArrayList<ReimbursementRequest> RequestPool = new ArrayList<ReimbursementRequest>();
		
		try{
			CallableStatement retrieveReimbReqStmt = conn.prepareCall("{? = call GETREQUESTBYEMPLOYEEID(?)}");
			retrieveReimbReqStmt.registerOutParameter(1, OracleTypes.CURSOR);
			retrieveReimbReqStmt.setInt(2, id);
			retrieveReimbReqStmt.execute();
			ResultSet rset = (ResultSet)retrieveReimbReqStmt.getObject(1);
			while(rset.next()){
				RequestPool.add(new ReimbursementRequest(rset.getInt(1), rset.getInt(2), rset.getInt(3),
                               rset.getDate(4), rset.getString(5), rset.getInt(6),
                                rset.getString(7), rset.getString(8), rset.getInt(9)));
			}
			rset.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return RequestPool;
	}

	public ArrayList<ReimbursementRequest> retrieveReimbRequestByIssuer(
			String issuer, Connection conn) {
		
		ArrayList<ReimbursementRequest> RequestPool = new ArrayList<ReimbursementRequest>();
		try{
			CallableStatement retrieveReimbReqStmt = conn.prepareCall("{? = call GETREQUESTBYISSUER(?)}");
			retrieveReimbReqStmt.registerOutParameter(1, OracleTypes.CURSOR);
			retrieveReimbReqStmt.setString(2, issuer);
			retrieveReimbReqStmt.execute();
			ResultSet rset = (ResultSet)retrieveReimbReqStmt.getObject(1);
			while(rset.next()){
				RequestPool.add(new ReimbursementRequest(rset.getInt(1), rset.getInt(2), rset.getInt(3),
                               rset.getDate(4), rset.getString(5), rset.getInt(6),
                                rset.getString(7), rset.getString(8), rset.getInt(9)));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return RequestPool;
	}

	public void updateReimbursementRequest(ReimbursementRequest req, Connection conn) {
		try{
		   CallableStatement updateReimbRequestStmt = conn.prepareCall("{call UPDATEREQUEST(?,?,?)}");
		   updateReimbRequestStmt.setInt(1, req.getRequestID());
		   updateReimbRequestStmt.setString(2,req.getCurrentIssuer());
		   updateReimbRequestStmt.setInt(3, req.getStatus());
		   updateReimbRequestStmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void deleteReimbursementRequest(int id, Connection conn) {
		try{
			CallableStatement deleteReimbRequestStmt = conn.prepareCall("{call DELETEREIMBREQUESTBYID(?)}");
			deleteReimbRequestStmt.setInt(1, id);
			deleteReimbRequestStmt.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}
}
