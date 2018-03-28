package daos;

import java.sql.Connection;
import java.util.ArrayList;

import Entities.ReimbursementRequest;

public interface ReimbursementRequestDao {
	public void createReimbursementRequest(ReimbursementRequest req, Connection conn);
	public ReimbursementRequest retrieveReimbRequestById(int id, Connection conn);
	public ArrayList<ReimbursementRequest> retrieveReimbRequestsByEmpId(int id, Connection conn);
	public ArrayList<ReimbursementRequest> retrieveReimbRequestByIssuer(String issuer, Connection conn);
	public void updateReimbursementRequest(ReimbursementRequest req, Connection conn);
	public void deleteReimbursementRequest(int id, Connection conn);
}
