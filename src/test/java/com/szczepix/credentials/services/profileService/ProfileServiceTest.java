package com.szczepix.credentials.services.profileService;

import com.szczepix.credentials.dao.IProfileRepository;
import com.szczepix.credentials.entities.ProfileEntity;
import com.szczepix.credentials.entities.ProfileEntityTest;
import com.szczepix.credentials.services.eventService.IEventSerivce;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = ProfileServiceTest.ProfileServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceTest {

    @Autowired
    private IProfileService profileService;

    @Autowired
    private IProfileRepository repository;

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void getEntities() {
        assertThat(profileService.getEntities()).isNotNull();
        assertThat(profileService.getEntities().size()).isEqualTo(0);
    }

    @Test
    public void getProfilesWhenProfilesCreated() {
        when(repository.findAll()).thenReturn(createProfilesMock(3));

        assertThat(profileService.getEntities()).isNotNull();
        assertThat(profileService.getEntities().size()).isEqualTo(3);
    }

    @Test
    public void getProfile() {
        assertThat(profileService.getEntity()).isNotNull();
    }

    @Test
    public void getProfileWhenCreated() {
        List<ProfileEntity> profiles = createProfilesMock(1);
        when(repository.findAll()).thenReturn(profiles);
        assertThat(profileService.getEntity()).isNotNull();
        assertThat(profileService.getEntity()).isEqualTo(profiles.get(0));
    }

    @Test
    public void save() {
        ProfileEntity entity = profileService.getEntity();
        profileService.save(entity);
        verify(repository, times(1)).save(eq(entity));
    }

    @Test
    public void getFindProfileByUserNameAndPasswordWhenWrong() {
        when(repository.findProfileByUserNameAndPassword(eq("szczepix"), eq("password"))).thenReturn(null);
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password")).isNull();
    }

    @Test
    public void getFindProfileByUserNameAndPasswordWhenCorrect() {
        ProfileEntity entity = new ProfileEntity();
        entity.setPassword("password");
        entity.setUserName("szczepix");
        when(repository.findProfileByUserNameAndPassword(eq("szczepix"), eq("password"))).thenReturn(entity);
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password")).isNotNull();
        assertThat(profileService.findProfileByUserNameAndPassword("szczepix", "password")).isEqualTo(entity);
    }

    private List<ProfileEntity> createProfilesMock(int count) {
        List<ProfileEntity> list = new ArrayList<>();
        while (count > 0) {
            list.add(new ProfileEntityTest.ProfileEntityMock(count));
            count--;
        }
        return list;
    }

    @Configuration
    static class ProfileServiceTestConfiguration {

        @Bean
        public IProfileRepository repository() {
            IProfileRepository repository = mock(IProfileRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        public IEventSerivce eventSerivce() {
            return mock(IEventSerivce.class);
        }

        @Bean
        public IProfileService getProfileService() {
            return new ProfileService(repository(), eventSerivce());
        }
    }
}