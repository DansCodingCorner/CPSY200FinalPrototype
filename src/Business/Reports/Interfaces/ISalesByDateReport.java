package Business.Reports.Interfaces;

import java.time.LocalDate;

import Persistence.IRentalDataAccess;

public interface ISalesByDateReport 
{
	public String generateReport(IRentalDataAccess dataAccess, LocalDate date);
}
