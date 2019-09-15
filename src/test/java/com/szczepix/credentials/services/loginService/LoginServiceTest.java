package com.szczepix.credentials.services.loginService;

import com.szczepix.credentials.dao.ILoginRepository;
import com.szczepix.credentials.entities.LoginEntity;
import com.szczepix.credentials.entities.LoginEntityTest;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = LoginServiceTest.LoginServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceTest {

    @Autowired
    private ILoginService groupService;

    @Autowired
    private ILoginRepository repository;

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
        LoginEntity entity = new LoginEntityTest.LoginEntityMock(1);
        groupService.save(entity);
        verify(repository, times(1)).save(eq(entity));
    }

    @Configuration
    static class LoginServiceTestConfiguration {

        @Bean
        public ILoginRepository repository() {
            ILoginRepository repository = mock(ILoginRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        @Qualifier("eventServiceMock")
        public IEventSerivce eventSerivce(){
            return mock(IEventSerivce.class);
        }

        @Bean
        public ILoginService getLoginService() {
            return new LoginService(repository(), eventSerivce());
        }
    }
}