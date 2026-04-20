package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Business.Equipment;
import Business.EquipmentManager;
import Business.Interfaces.IEquipment;

public class EquipmentTest {

	private EquipmentManager manager;

	@BeforeEach
	void setUp() {
	     manager = new EquipmentManager();
	}




	    @Test
	    void testSearchEquipment_notFound() {
	        Equipment result = (Equipment) manager.searchEquipment(999);

	        assertNull(result);
	    }
}
