package com.szczepix.credentials.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GroupEntityTest {

    private GroupEntityMock profileEntity;

    @Before
    public void onInit() {
        profileEntity = new GroupEntityMock(1);
    }

    @Test
    public void getId() {
        assertThat(profileEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getColor() {
        assertThat(profileEntity.getColor()).isEqualTo("Oxffffff");
    }

    @Test
    public void getName() {
        assertThat(profileEntity.getName()).isEqualTo("name");
    }

    @Test
    public void checkToString() {
        assertThat(profileEntity.toString()).isEqualTo("[GroupEntityMock]:[1][name][Oxffffff]");
    }

    @Test
    public void checkIsEqual() {
        GroupEntity anotherEntity = new GroupEntity();
        anotherEntity.setId(1);
        anotherEntity.setName("name");
        anotherEntity.setColor("Oxffffff");
        assertThat(anotherEntity.equals(profileEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        GroupEntity anotherEntity = new GroupEntity();
        anotherEntity.setId(1);
        anotherEntity.setName("firstName");
        anotherEntity.setColor("0xooooo");
        assertThat(anotherEntity.equals(profileEntity)).isFalse();
    }

    public static class GroupEntityMock extends GroupEntity {

        public GroupEntityMock(final int id) {
            setId(id);
            setColor("Oxffffff");
            setName("name");
        }
    }
}