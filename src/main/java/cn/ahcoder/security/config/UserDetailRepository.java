package cn.ahcoder.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：AhHao
 * @date: 2022/6/30
 */
public class UserDetailRepository {

    private Map<String, UserDetails> users = new HashMap<>();

    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            throw new RuntimeException("用户已存在");
        }
        users.putIfAbsent(user.getUsername(),user);
    }

    public void updateUser(UserDetails user) {
        if (!userExists(user.getUsername())) {
            throw new RuntimeException("用户不存在");
        }
        users.put(user.getUsername(),user);
    }

    public void deleteUser(String username) {
        users.remove(username);
    }

    public void changePassword(String oldPassword, String newPassword) {
        //do nothing
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public UserDetails loadUserByUsername(String username) {
        if (!userExists(username)) {
            throw new UsernameNotFoundException(username);
        }
        return users.get(username);
    }
}
