package client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CredentialsTest {
    private Credentials string_1; //Text fetures

    @Before
    public void setUp() throws Exception {
        System.out.println("Run @Before");
        string_1 = new Credentials("peter", "1234");
    }

    @Test
    public void getUsername() {
        System.out.println("Run @Test getUsername");
        assertEquals("username is a match", "peter", string_1.getUsername());
    }

    @Test
    public void getPassword() {
        System.out.println("Run @Test getPassword");
        assertEquals("username is a match", "1234", string_1.getPassword());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Run @After");
    }
}