package com.playready.PlayReadyBackend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    public void testRoleGettersAndSetters() {
        Role role = new Role();

        role.setRolename("ADMIN");

        assertEquals("ADMIN", role.getRolename());
    }
}
