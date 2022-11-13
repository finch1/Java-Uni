package server.dto;

import client.Credentials;
import com.User;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class UserDTOTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Run @Before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Run @After");
    }

    @Test
    public void test_getAllUser() {
        System.out.println("Run @Test test_getAllUser");
        assertThat(UserDTO.getAllUser(), not(IsEmptyCollection.empty()));
    }

    @Test (expected = NullPointerException.class)
    public void test_nullReturn() {
        System.out.println("Run @Test test_nullReturn");
        Credentials myCred = new Credentials("noUser", "noPassword");
        UserDTO.getUser_by_Username(myCred);
    }

}