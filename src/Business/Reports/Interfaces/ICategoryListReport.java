package Business.Reports.Interfaces;

import Persistence.ICategoryDataAccess;

public interface ICategoryListReport
{
	public String generateReport(ICategoryDataAccess dataAccess);
}
