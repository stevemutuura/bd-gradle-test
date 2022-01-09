package com.helloworld;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Main.main(new String[]{});
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void main_says_hello_my_name_is() {
        assertTrue(outContent.toString().startsWith("Hello my name is"));
    }

    @Test
    void main_contains_name() {
        String trimmed = outContent.toString().trim();
        assertTrue(trimmed.length() > "Hello my name is ".length());
    }

    @Test
    void main_contains_enthusiasm() {
        String trimmed = outContent.toString().trim();
        assertTrue(trimmed.endsWith("!"));
    }


}