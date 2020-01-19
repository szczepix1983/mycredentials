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
        assertThat(profileEntity.getLogin()).isEqualTo("login");
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
        anotherEntity.setLogin("login");
        anotherEntity.setGroups(Lists.emptyList());
        assertThat(anotherEntity.equals(profileEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        LoginEntity anotherEntity = new LoginEntity();
        anotherEntity.setId(1);
        anotherEntity.setLogin("login");
        anotherEntity.setGroups(Lists.emptyList());
        assertThat(anotherEntity.equals(profileEntity)).isFalse();
    }

    public static class LoginEntityMock extends LoginEntity {

        public LoginEntityMock(final int id) {
            setId(id);
            setLogin("login");
            setGroups(Lists.emptyList());
        }
    }
}