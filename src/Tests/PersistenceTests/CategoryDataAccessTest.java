package Tests.PersistenceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Persistence.CategoryDataAccess;
import Persistence.ICategoryDataAccess;

class CategoryDataAccessTest 
{
	ICategoryDataAccess cda1,cda2;

	@Test
	void TestgetInstance()
	{
		cda1 = CategoryDataAccess.getInstance();
		cda2 = CategoryDataAccess.getInstance();
		
		assertEquals(cda1, cda2);
	}
	
	@Test
	void testLoadData()
	{
		
	}

}
