package org.mql.java.introspection;

import org.junit.jupiter.api.Test;
import org.mql.java.introspection.models.Field;
import org.mql.java.introspection.models.Interface;
import org.mql.java.introspection.parser.ClassParser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ClassParserTest {
	
	ClassParser parser = new ClassParser("java.io.File");

	@Test
	public List<Field> getFields() {
        return parser.getFields();
    }

    @Test
    public void testGetInterfaces_WithInterfaces() {
        // Test a class that implements interfaces
        class MyClass implements Runnable, Comparable<MyClass> {

			@Override
			public int compareTo(MyClass o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
        }

        
        List<Interface> interfaces = parser.getInterfaces(MyClass.class);

        assertNotNull(interfaces, "Interfaces list should not be null");
        assertEquals(2, interfaces.size(), "The class should have 2 interfaces");
        
        // Check that each interface has the expected name
        assertEquals("Runnable", interfaces.get(0).getName());
        assertEquals("Comparable", interfaces.get(1).getName());
    }

    @Test
    public void testGetInterfaces_NoInterfaces() {
        // Test a class with no interfaces
        class MyClass {
        }
        
        List<Interface> interfaces =  parser.getInterfaces(MyClass.class);

        assertNotNull(interfaces, "Interfaces list should not be null");
        assertTrue(interfaces.isEmpty(), "The class should have no interfaces");
    }

    @Test
    public void testGetInterfaces_EmptyClass() {
        // Test with an empty class (no interfaces)
        class MyClass implements Runnable {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
        }

        List<Interface> interfaces =  parser.getInterfaces(MyClass.class);

        assertNotNull(interfaces, "Interfaces list should not be null");
        assertEquals(1, interfaces.size(), "The class should have 1 interface");
        assertEquals("Runnable", interfaces.get(0).getName());
    }

    @Test
    public void testGetInterfaces_NullClass() {
        // Test null class (optional, depending on your implementation)
        try {
        	 parser.getInterfaces(null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            // Expected behavior
        }
    }

    @Test
    public void testGetInterfaces_Recursion() {
        // Test recursion, i.e., interfaces that extend other interfaces
        interface A extends Runnable {
        }

        class MyClass implements A {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
        }

        List<Interface> interfaces =  parser.getInterfaces(MyClass.class);

        assertNotNull(interfaces, "Interfaces list should not be null");
        assertEquals(1, interfaces.size(), "The class should have 1 interface");
        assertEquals("A", interfaces.get(0).getName());
        assertEquals(1, interfaces.get(0).getExtendedInterfaces().size(), "Sub-interface recursion is not handled properly");
    }
}
