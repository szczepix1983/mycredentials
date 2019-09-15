package com.szczepix.credentials.entities;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LoginEntityTest {

    private LoginEntityMock profileEntity;

    @Before
    public void onInit() {
        profileEntity = new LoginEntityMock(1);
    }

    @Test
    public void getId() {
        assertThat(profileEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getEmail() {
        assertThat(profileEntity.getEmail()).isEqualTo("email");
    }

    @Test
    public void getGroups() {
        assertThat(profileEntity.getGroups()).isNotNull();
    }

    @Test
    public void checkToString() {
        assertThat(profileEntity.toString()).isEqualTo("[LoginEntityMock]:[1][email]");
    }

    @Test
    public void checkIsEqual() {
        LoginEntity anotherEntity = new LoginEntity();
        anotherEntity.setId(1);
        anotherEntity.setEmail("email");
        anotherEntity.setGroups(Lists.emptyList());
        assertThat(anotherEntity.equals(profileEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        LoginEntity anotherEntity = new LoginEntity();
        anotherEntity.setId(1);
        anotherEntity.setEmail("email2");
        anotherEntity.setGroups(Lists.emptyList());
        assertThat(anotherEntity.equals(profileEntity)).isFalse();
    }

    public static class LoginEntityMock extends LoginEntity {

        public LoginEntityMock(final int id) {
            setId(id);
            setEmail("email");
            setGroups(Lists.emptyList());
        }
    }
}