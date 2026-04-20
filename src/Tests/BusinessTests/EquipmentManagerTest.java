package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Business.Equipment;
import Business.EquipmentManager;

public class EquipmentManagerTest {

	private EquipmentManager manager;

	@BeforeEach
	void setUp() {
	     manager = new EquipmentManager();
	}

	@Test
    void testAddEquipment() {
        Equipment e = new Equipment(1, "Drill", null, false, null, 0, null);

		manager.addEquipment(e);
        List<Equipment> list = manager.loadEquipmentList();

        assertEquals(1, list.size());
        assertEquals(e, list.get(0));
    }	
	
	  @Test
	    void testSearchEquipment_found() {
	        Equipment e = new Equipment(1, "Hammer", null, false, null, 0, null);
	        manager.addEquipment(e);
	        
	        Equipment result = manager.searchEquipment(1);

	        assertNotNull(result);
	        assertEquals("Hammer", result.getName());
	    }

	    @Test
	    void testSearchEquipment_notFound() {
	        Equipment result = manager.searchEquipment(999);

	        assertNull(result);
	    }
	    @Test
	    void testRemoveEquipment() {
	        Equipment e = new Equipment(1, "Saw", null, false, null, 0, null);
	        manager.addEquipment(e);

	        manager.removeEquipment(e);

	        assertTrue(manager.loadEquipmentList().isEmpty());
	    } 
	    @Test
	    void testUpdateEquipment() {
	        Equipment original = new Equipment(1, "Old Name", null, false, null, 0, null);
	        manager.addEquipment(original);

	        Equipment updated = new Equipment(1, "New Name", null, false, null, 0, null);
	        manager.updateEquipment(updated);

	        Equipment result = manager.searchEquipment(1);

	        assertEquals("New Name", result.getName());
	    }
	    @Test
	    void testUpdateEquipment_notFound() {
	        Equipment updated = new Equipment(1, "New Name", null, false, null, 0, null);

	        manager.updateEquipment(updated);

	        assertNull(manager.searchEquipment(1));
	    }
}
