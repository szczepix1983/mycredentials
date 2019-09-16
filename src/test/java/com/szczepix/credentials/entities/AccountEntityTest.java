package com.szczepix.credentials.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AccountEntityTest {

    private AccountEntityMock profileEntity;

    @Before
    public void onInit() {
        profileEntity = new AccountEntityMock(1);
    }

    @Test
    public void getId() {
        assertThat(profileEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getName() {
        assertThat(profileEntity.getName()).isEqualTo("name");
    }

    @Test
    public void getWebsite() {
        assertThat(profileEntity.getWebsite()).isEqualTo("website");
    }

    @Test
    public void getPassword() {
        assertThat(profileEntity.getPassword()).isEqualTo("password");
    }

    @Test
    public void getLogin() {
        assertThat(profileEntity.getLogin()).isNotNull();
    }

    @Test
    public void checkToString() {
        assertThat(profileEntity.toString()).isEqualTo("[AccountEntityMock]:[1]");
    }

    @Test
    public void checkIsEqual() {
        AccountEntity anotherEntity = new AccountEntity();
        anotherEntity.setId(1);
        anotherEntity.setPassword("password");
        assertThat(anotherEntity.equals(profileEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        AccountEntity anotherEntity = new AccountEntity();
        anotherEntity.setId(2);
        anotherEntity.setPassword("password");
        assertThat(anotherEntity.equals(profileEntity)).isFalse();
    }

    public static class AccountEntityMock extends AccountEntity {

        public AccountEntityMock(final int id) {
            setId(id);
            setPassword("password");
            setName("name");
            setWebsite("website");
            setLogin(new LoginEntityTest.LoginEntityMock(1));
        }
    }
}