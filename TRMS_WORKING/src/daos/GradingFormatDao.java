package daos;

import Entities.GradingFormat;

public interface GradingFormatDao {
	public void createGradingFormat(GradingFormat gradingformat);
	public GradingFormat retrieveGradingFormbyId(int id);
	public void deleteGradingFormat(int id);
}
