package server.dto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoomTypeDTOTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Run @Before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Run @After");
    }

    @Test
    public void test_getAllRoomTypes() {
        System.out.println("Run @Test test_getAllRoomTypes");
        //check empty list
        assertThat(RoomTypeDTO.getAllRoomTypes(), not(IsEmptyCollection.empty()));
    }
}