package com.szczepix.credentials.services.accountService;

import com.szczepix.credentials.dao.IAccountRepository;
import com.szczepix.credentials.entities.AccountEntity;
import com.szczepix.credentials.entities.AccountEntityTest;
import com.szczepix.credentials.events.AccountsChangeEvent;
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

@ContextConfiguration(classes = AccountServiceTest.AccountServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IEventSerivce eventService;

    @Autowired
    private IAccountRepository repository;

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void getEntities() {
        assertThat(accountService.getEntities()).isNotNull();
        assertThat(accountService.getEntities().size()).isEqualTo(0);
    }

    @Test
    public void save() {
        AccountEntity entity = new AccountEntityTest.AccountEntityMock(1);
        accountService.save(entity);
        verify(repository, times(1)).save(eq(entity));
        verify(eventService, times(1)).dispatch(any(AccountsChangeEvent.class));
    }

    @Configuration
    static class AccountServiceTestConfiguration {

        @Bean
        public IAccountRepository repository() {
            IAccountRepository repository = mock(IAccountRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        @Qualifier("eventServiceMock")
        public IEventSerivce eventSerivce(){
            return mock(IEventSerivce.class);
        }

        @Bean
        public IAccountService getAccountService() {
            return new AccountService(repository(), eventSerivce());
        }
    }
}