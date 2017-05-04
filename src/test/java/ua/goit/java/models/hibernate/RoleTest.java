package ua.goit.java.models.hibernate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoleTest {
    @Test
    public void build_AllInformationGiven_ShouldCreateNewObjectAndSetAllInformation() {
        Role role = new Role();
        role.setName("name");

        assertNull(role.getId());
        assertEquals("name", role.getName());
    }
}