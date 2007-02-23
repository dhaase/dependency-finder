package com.jeantessier.commandline;

import junit.framework.*;

public class TestOptionalValueSwitch extends TestCase {
    private static final String DEFAULT_VALUE = "default value";

    private OptionalValueSwitch commandLineSwitch;
    private static final String EXPECTED_VALUE = "expected value";

    protected void setUp() throws Exception {
        super.setUp();

        commandLineSwitch = new OptionalValueSwitch("switch", DEFAULT_VALUE);
    }

    public void testSetToNull() {
        assertFalse(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
        commandLineSwitch.setValue(null);
        assertTrue(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
    }

    public void testSetToObject() {
        assertFalse(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
        commandLineSwitch.setValue(EXPECTED_VALUE);
        assertTrue(commandLineSwitch.isPresent());
        assertEquals(EXPECTED_VALUE, commandLineSwitch.getValue());
    }

    public void testParseNull() throws CommandLineException {
        assertFalse(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
        commandLineSwitch.parse(null);
        assertTrue(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
    }

    public void testParseEmptyString() throws CommandLineException {
        assertFalse(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
        commandLineSwitch.parse("");
        assertTrue(commandLineSwitch.isPresent());
        assertEquals("", commandLineSwitch.getValue());
    }

    public void testParseString() throws CommandLineException {
        assertFalse(commandLineSwitch.isPresent());
        assertEquals(DEFAULT_VALUE, commandLineSwitch.getValue());
        commandLineSwitch.parse(EXPECTED_VALUE);
        assertTrue(commandLineSwitch.isPresent());
        assertEquals(EXPECTED_VALUE, commandLineSwitch.getValue());
    }

    public void testValidateWhenNotMandatory() throws CommandLineException {
        commandLineSwitch.validate();
        commandLineSwitch.parse(EXPECTED_VALUE);
        commandLineSwitch.validate();
    }

    public void testValidateWhenMandatory() throws CommandLineException {
        commandLineSwitch = new OptionalValueSwitch("switch", "default", true);
        try {
            commandLineSwitch.validate();
            fail("Missing mandatory switch should not validate.");
        } catch (CommandLineException e) {
            // Expected
        }
        commandLineSwitch.parse(EXPECTED_VALUE);
        commandLineSwitch.validate();
    }
}
