package Business.Reports.Interfaces;

import java.time.LocalDate;


public interface ISalesByDateReport 
{
	public String generateReport( LocalDate date);
}
