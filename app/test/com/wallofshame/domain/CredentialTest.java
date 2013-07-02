package com.wallofshame.domain;

import com.wallofshame.repository.peoplesoft.Credential;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Since: 3/15/12
 */
public class CredentialTest {

    @Test
    public void can_save_user_credential() throws Exception {
        Credential credential = Credential.getInstance();
        String username = "username";
        String password = "password";
        credential.save(username, password);
        assertEquals(username, credential.username());
        assertEquals(password, credential.password());
    }
}
