package ru.rbagmet.tests.simpleexapmles;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("empty")
public class SkipEmptyTests {

    @Test
    @Disabled
    void test1() {
        assertTrue(true);
    }

    @Test
    @Disabled
    void test2() {
        assertTrue(true);
    }
}
