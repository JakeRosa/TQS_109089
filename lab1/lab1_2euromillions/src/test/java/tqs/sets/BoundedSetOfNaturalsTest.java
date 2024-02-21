/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[] { 10, 20, 30, 40, 50, 60 });
        setC = BoundedSetOfNaturals.fromArray(new int[] { 50, 60 });
        setD = new BoundedSetOfNaturals(5);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @DisplayName("Test add element")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        // setB.add(11);
        // assertTrue(setB.contains(11), "add: added element not found in set.");
        // assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @DisplayName("Test add when set is full")
    @Test
    public void testAddElementToFullSet() {
        setA.add(99);
        assertThrows(IllegalArgumentException.class, () -> setA.add(100));
    }

    @DisplayName("Test add same element")
    @Test
    public void testAddSameElement() {
        setD.add(2);
        assertThrows(IllegalArgumentException.class, () -> setD.add(2));
    }

    @DisplayName("Test add a negative element")
    @Test
    public void testAddNegativeElement() {
        assertThrows(IllegalArgumentException.class, () -> setA.add(-1));
    }

    // @Disabled("TODO revise to test the construction from invalid arrays")
    @DisplayName("Test create set from invalid array")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[] { 10, -20, -30 };

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems));
    }

    @DisplayName("Test intersection between sets")
    @Test
    public void testIntersect() {
        BoundedSetOfNaturals setE = BoundedSetOfNaturals.fromArray(new int[] { 20, 30 });
        assertTrue(setB.intersects(setC));
        assertFalse(setE.intersects(setC));
    }

    @DisplayName("Test equals with same set")
    @Test
    public void testEqualsWithSameSet() {
        setA.add(1);
        assertTrue(setA.equals(setA));
    }

    @DisplayName("Test equals with null")
    @Test
    public void testEqualsWithNull() {
        setA.add(1);
        assertFalse(setA.equals(null));
    }

    @DisplayName("Test equals with different class")
    @Test
    public void testEqualsWithDifferentClass() {
        setA.add(1);
        assertFalse(setA.equals("setA"));
    }

    @DisplayName("Test hashcode with equal sets")
    @Test
    public void testHashCodeWithEqualSets() {
        setA.add(1);
        setD.add(1);

        assertEquals(setA, setD);
        assertEquals(setA.hashCode(), setD.hashCode());
    }
}
