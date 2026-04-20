package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Business.Equipment;
import Business.EquipmentManager;
import Business.Interfaces.IEquipment;

public class EquipmentManagerTest {

	private EquipmentManager manager;

	@BeforeEach
	void setUp() {
	     manager = new EquipmentManager();
	}


	
	  @Test
	    void testSearchEquipment_found() {
	        
	        Equipment result = (Equipment) manager.searchEquipment(1);

	        assertNotNull(result);
	        assertEquals("Hammer", result.getName());
	    }

	    @Test
	    void testSearchEquipment_notFound() {
	        Equipment result = (Equipment) manager.searchEquipment(999);

	        assertNull(result);
	    }
	    @Test
	    void testRemoveEquipment() {


	        assertTrue(manager.loadEquipmentList().isEmpty());
	    } 
	    @Test
	    void testUpdateEquipment() {


	        Equipment result = (Equipment) manager.searchEquipment(1);

	        assertEquals("New Name", result.getName());
	    }
	    @Test
	    void testUpdateEquipment_notFound() {


	        assertNull(manager.searchEquipment(1));
	    }
}
