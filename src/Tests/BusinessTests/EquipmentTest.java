package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Business.Equipment;

class EquipmentTest {

    @Test
    void testEquipmentCreation() {
        Equipment equipment = new Equipment(1, "Drill", null, false, null, 50.0, null);

        assertNotNull(equipment);
        assertEquals(1, equipment.getEquipmentId());
        assertEquals("Drill", equipment.getName());
        assertEquals(50.0, equipment.getPrice(), 0.001);
    }

    @Test
    void testEquipmentSetters() {
        Equipment equipment = new Equipment(2, "Saw", null, false, null, 75.0, null);

        equipment.setName("Circular Saw");
        equipment.setPrice(100.0);

        assertEquals("Circular Saw", equipment.getName());
        assertEquals(100.0, equipment.getPrice(), 0.001);
    }

    @Test
    void testEquipmentIdHandling() {
        Equipment equipment = new Equipment(3, "Hammer", null, false, null, 20.0, null);

        assertEquals(3, equipment.getEquipmentId());

        equipment.setEquipmentId(4, equipment);
        assertEquals(4, equipment.getEquipmentId());
    }

    @Test
    void testEquipmentNameHandling() {
        Equipment equipment = new Equipment(4, "Wrench", null, false, null, 15.0, null);

        assertEquals("Wrench", equipment.getName());

        equipment.setName("Adjustable Wrench");
        assertEquals("Adjustable Wrench", equipment.getName());
    }

    @Test
    void testEquipmentPriceHandling() {
        Equipment equipment = new Equipment(5, "Ladder", null, false, null, 120.0, null);

        assertEquals(120.0, equipment.getPrice(), 0.001);

        equipment.setPrice(150.0);
        assertEquals(150.0, equipment.getPrice(), 0.001);
    }

    @Test
    void testEquipmentToString() {
        Equipment equipment = new Equipment(6, "Drill", null, false, null, 60.0, null);

        String expected = "Equipment [id=6, name=Drill, price=60.0]";
        assertEquals(expected, equipment.toString());
    }

    @Test
    void testEquipmentWithInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Equipment(-1, "", null, false, null, -50.0, null);
        });
    }

    @Test
    void testEquipmentWithNullName() {
        assertThrows(NullPointerException.class, () -> {
            new Equipment(7, null, null, false, null, 30.0, null);
        });
    }
}