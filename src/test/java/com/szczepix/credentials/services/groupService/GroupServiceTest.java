package com.szczepix.credentials.services.groupService;

import com.szczepix.credentials.dao.IGroupRepository;
import com.szczepix.credentials.entities.GroupEntity;
import com.szczepix.credentials.entities.GroupEntityTest;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = GroupServiceTest.GroupServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupServiceTest {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupRepository repository;

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void getEntities() {
        assertThat(groupService.getEntities()).isNotNull();
        assertThat(groupService.getEntities().size()).isEqualTo(0);
    }

    @Test
    public void save() {
        GroupEntity entity = new GroupEntityTest.GroupEntityMock(1);
        groupService.save(entity);
        verify(repository, times(1)).save(eq(entity));
    }

    @Configuration
    static class GroupServiceTestConfiguration {

        @Bean
        public IGroupRepository repository() {
            IGroupRepository repository = mock(IGroupRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        @Qualifier("eventServiceMock")
        public IEventSerivce eventSerivce(){
            return mock(IEventSerivce.class);
        }

        @Bean
        public IGroupService getGroupService() {
            return new GroupService(repository(), eventSerivce());
        }
    }
}