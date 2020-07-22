package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.List;
import edu.isu.cs2235.structures.impl.DoublyLinkedList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac Griffith
 */
public class DoublyLinkedListTest {
    
    private List<Integer> fixture;
    
    public DoublyLinkedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fixture = new DoublyLinkedList<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class SinglyLinkedList.
     */
    @Test
    public void testAdd() {
        assertEquals(0, fixture.size());
        fixture.addLast(0);
        assertEquals(1, fixture.size());
        assertEquals(new Integer(0), fixture.get(0));
        fixture.addLast(null);
        assertEquals("Size was incorrectly updated", 1, fixture.size());
    }

    /**
     * Test of remove method, of class SinglyLinkedList.
     */
    @Test
    public void testRemove() {
        fixture.addLast(0);
        fixture.addLast(2);

        assertEquals(2, fixture.size());
        assertNull("Tried to remove an item with a negative index.", fixture.remove(-1));
        assertEquals("Size was incorrectly updated", 2, fixture.size());
    }

    /**
     * Test of remove method, of class SinglyLinkedList.
     */
    @Test
    public void testRemove_2() {
        fixture.addLast(0);
        fixture.addLast(2);

        assertEquals(2, fixture.size());
        assertNull("Tried to remove an item with an index == size", fixture.remove(fixture.size()));
        assertEquals("Size was incorrectly updated", 2, fixture.size());
    }

    /**
     * Test of remove method, of class SinglyLinkedList.
     */
    @Test
    public void testRemove_3() {
        fixture.addLast(0);
        fixture.addLast(2);

        assertEquals(2, fixture.size());
        assertNull("Tried to remove item from an index larger than size", fixture.remove(fixture.size() + 1));
        assertEquals("Size was incorrectly updated.", 2, fixture.size());
    }

    /**
     * Test of remove method, of class SinglyLinkedList.
     */
    @Test
    public void testRemove_4() {
        fixture.addLast(0);
        fixture.addLast(2);

        assertEquals(2, fixture.size());
        assertEquals("Removed the wrong element", new Integer(2), fixture.remove(1));
        assertEquals("Size was not correctly updated.", 1, fixture.size());
    }

    /**
     * Test of insert method, of class SinglyLinkedList.
     */
    @Test
    public void testInsert() {
        fixture.addLast(0);
        fixture.addLast(2);

        assertEquals(2, fixture.size());
        fixture.insert(1, 1);
        assertEquals("Inserted element at wrong index", new Integer(1), fixture.get(1));
        assertEquals("Size was not correctly updated", 3, fixture.size());
    }

    /**
     * Test of insert method, of class SinglyLinkedList.
     */
    @Test
    public void testInsert_2() {
        fixture.addLast(0);
        fixture.addLast(2);

        fixture.insert(null, 1);
        assertEquals("Size was incorrectly updated on null insert", 2, fixture.size());
    }

    /**
     * Test of insert method, of class SinglyLinkedList.
     */
    @Test
    public void testInsert_3() {
        fixture.addLast(0);
        fixture.addLast(2);

        fixture.insert(3, -1);
        assertEquals("Size was incorrectly updated on insert at negative index", 2, fixture.size());
    }

    /**
     * Test of insert method, of class SinglyLinkedList.
     */
    @Test
    public void testInsert_4() {
        fixture.addLast(0);
        fixture.addLast(2);

        fixture.insert(3, 5);
        assertEquals("Size was not updated on correct insert", 3, fixture.size());
    }

    @Test
    public void testInsert_5() {
        ArrayList<Integer> otherList = new ArrayList<>();

        otherList.add(0);
        fixture.addFirst(0);

        otherList.add(0);
        fixture.addLast(0);

        Random rand = new Random();
        for (int i = 1; i < 1000; i++) {
            int r = rand.nextInt(1000) + 1;
            otherList.add(i, r);
            fixture.insert(r, i);
        }

        for (int i = 0; i < fixture.size(); i++) {
            assertEquals(otherList.get(i), fixture.get(i));
        }
    }

    /**
     * Test of get method, of class SinglyLinkedList.
     */
    @Test
    public void testGet() {
        assertNull(fixture.get(0));

        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertNull("Did not return null on request for out of range index", fixture.get(5));
    }

    /**
     * Test of get method, of class SinglyLinkedList.
     */
    @Test
    public void testGet_2() {
        assertNull(fixture.get(0));

        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertNull("Did not return null on request for negative index", fixture.get(-1));
    }

    /**
     * Test of get method, of class SinglyLinkedList.
     */
    @Test
    public void testGet_3() {
        assertNull(fixture.get(0));

        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertNull("Did not return null for request of out of range index.", fixture.get(6));
    }

    /**
     * Test of get method, of class SinglyLinkedList.
     */
    @Test
    public void testGet_4() {
        assertNull(fixture.get(0));

        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertEquals("Did not return correct value for provided index", new Integer(4), fixture.get(4));
    }

    /**
     * Test of get method, of class SinglyLinkedList.
     */
    @Test
    public void testGet_5() {
        assertNull(fixture.get(0));

        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertEquals("Did not return correct value of first index", new Integer(0), fixture.get(0));
    }

    /**
     * Test of get method, of class SinglyLinkedList.
     */
    @Test
    public void testGet_6() {
        assertNull(fixture.get(0));

        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertEquals("Did not return correct value for index", new Integer(2), fixture.get(2));
    }

    /**
     * Test of the printList method, for class SinglyLinkedList
     */
    @Test
    public void testPrintList() {
        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        fixture.printList();

        System.out.flush();
        System.setOut(old);

        String output = baos.toString();
        assertEquals("Did not print correctly", "0" + System.lineSeparator() + "1" + System.lineSeparator() + "2" + System.lineSeparator() + "3" + System.lineSeparator() + "4", output.trim());
    }

    /**
     * Test of first method, of class SinglyLinkedList.
     */
    @Test
    public void testFirst() {
        fixture.addLast(1);
        Object expResult = 1;
        Object result = fixture.first();
        assertEquals("Did not return correct value for first element", expResult, result);
    }

    /**
     * Test of first method, of class SinglyLinkedList.
     */
    @Test
    public void testFirst_2() {
        Object result = fixture.first();
        assertNull("Did not return null for first value of empty list", result);
    }

    /**
     * Test of addFirst method, of class SinglyLinkedList.
     */
    @Test
    public void testAddFirst() {
        System.out.println("addFirst");
        fixture.addFirst(null);

        assertTrue("Empty list does not remain empty on adding null", fixture.isEmpty());
    }

    /**
     * Test of addFirst method, of class SinglyLinkedList.
     */
    @Test
    public void testAddFirst_2() {
        System.out.println("addFirst");
        fixture.addFirst(1);

        assertFalse("did not update list size", fixture.isEmpty());
        assertEquals("Incorrectly added value to list", new Integer(1), fixture.first());
    }

    public void testAddFirst_3() {
        ArrayList<Integer> otherList = new ArrayList<>();

        Random rand = new Random();
        for (int i = 1; i < 1000; i++) {
            int r = rand.nextInt(1000) + 1;
            otherList.add(0, r);
            fixture.addFirst(r);
        }

        for (int i = 0; i < fixture.size(); i++) {
            assertEquals(otherList.get(i), fixture.get(i));
        }
    }

    /**
     * Test of addLast method, of class SinglyLinkedList.
     */
    @Test
    public void testAddLast() {
        System.out.println("addLast");
        Integer element = null;
        fixture.addLast(element);

        assertTrue("Incorrectly updated size for null on addLast", fixture.isEmpty());
    }

    /**
     * Test of addLast method, of class SinglyLinkedList.
     */
    @Test
    public void testAddLast_2() {
        System.out.println("addLast");
        Integer element = 1;
        assertEquals(0, fixture.size());
        fixture.addLast(element);
        assertEquals(1, fixture.size());

        assertFalse(fixture.isEmpty());
        assertEquals(1, fixture.size());
        assertEquals(element, fixture.first());
        assertEquals(element, fixture.last());
    }

    /**
     * Test of addLast method, of class SinglyLinkedList.
     */
    @Test
    public void testAddLast_3() {
        System.out.println("addLast");
        Integer element = 2;
        fixture.addLast(1);
        assertEquals(1, fixture.size());
        fixture.addLast(element);
        assertEquals(2, fixture.size());

        assertFalse(fixture.isEmpty());
        assertEquals(element, fixture.last());
    }

    public void testAddLast_4() {
        ArrayList<Integer> otherList = new ArrayList<>();

        Random rand = new Random();
        for (int i = 1; i < 1000; i++) {
            int r = rand.nextInt(1000) + 1;
            otherList.add(r);
            fixture.addLast(r);
        }

        for (int i = 0; i < fixture.size(); i++) {
            assertEquals(otherList.get(i), fixture.get(i));
        }
    }

    /**
     * Test of removeFirst method, of class SinglyLinkedList.
     */
    @Test
    public void testRemoveFirst() {
        System.out.println("removeFirst");
        assertNull("does not return null for removal from an empty list", fixture.removeFirst());
    }

    /**
     * Test of removeFirst method, of class SinglyLinkedList.
     */
    @Test
    public void testRemoveFirst_2() {
        System.out.println("removeFirst");
        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertEquals(5, fixture.size());
        Integer result = fixture.removeFirst();
        assertEquals("returns incorrect value for removed item", new Integer(0), result);
        assertEquals("does not correctly update size after call to remove", 4, fixture.size());
    }

    /**
     * Test of last method, of class SinglyLinkedList.
     */
    @Test
    public void testLast() {
        System.out.println("last");
        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertEquals("returns incorrect value for call to last()", new Integer(4), fixture.last());
    }

    /**
     * Test of last method, of class SinglyLinkedList.
     */
    @Test
    public void testLast_2() {
        System.out.println("last");
        assertTrue(fixture.isEmpty());
        assertNull("does not correctly return null on empty list last() call.", fixture.last());
    }

    /**
     * Test of removeLast method, of class SinglyLinkedList.
     */
    @Test
    public void testRemoveLast() {
        System.out.println("removeLast");
        for (int i = 0; i < 5; i++) {
            fixture.addLast(i);
        }

        assertEquals(5, fixture.size());
        assertEquals("returned incorrect value of removed item", new Integer(4), fixture.removeLast());
        assertEquals("did not correctly update size upon removal", 4, fixture.size());
    }
}
