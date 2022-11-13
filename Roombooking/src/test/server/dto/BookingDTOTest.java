package server.dto;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class BookingDTOTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Run @Before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Run @After");
    }

    @Test
    public void test_getAllBooking() {
        System.out.println("Run @Test test_getAllBooking");
        assertThat(BookingDTO.getAllBooking(), not(IsEmptyCollection.empty()));
    }
}