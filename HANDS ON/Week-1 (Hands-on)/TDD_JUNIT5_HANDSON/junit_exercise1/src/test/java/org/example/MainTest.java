package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testAddition() {
        Main main = new Main();
        assertEquals(5, main.add(2, 3));
    }
}