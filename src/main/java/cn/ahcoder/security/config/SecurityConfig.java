package cn.ahcoder.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/30
 */
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailRepository userDetailRepository(){
        UserDetailRepository userDetailRepository = new UserDetailRepository();
        userDetailRepository.createUser(User.withUsername("fy").password("{noop}123456").authorities(AuthorityUtils.NO_AUTHORITIES).build());
        return userDetailRepository;
    }

    @Bean
    public UserDetailsManager userDetailsManager(UserDetailRepository userDetailRepository){
        return new UserDetailsManager() {
            @Override
            public void createUser(UserDetails user) {
                userDetailRepository.createUser(user);
            }

            @Override
            public void updateUser(UserDetails user) {
                userDetailRepository.updateUser(user);
            }

            @Override
            public void deleteUser(String username) {
                userDetailRepository.deleteUser(username);
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                userDetailRepository.changePassword(oldPassword,newPassword);
            }

            @Override
            public boolean userExists(String username) {
                return userDetailRepository.userExists(username);
            }

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDetailRepository.loadUserByUsername(username);
            }
        };
    }
}
