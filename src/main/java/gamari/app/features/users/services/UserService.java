package gamari.app.features.users.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gamari.app.configs.UserDetailsImpl;
import gamari.app.features.users.mappers.UserMapper;
import gamari.app.features.users.models.User;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void register(User user) {
        String uuid = UUID.randomUUID().toString();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setId(uuid);
        user.setPassword(hashedPassword);
        userMapper.insert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            // TODO なぜデッドコード？
            throw new UsernameNotFoundException("Not Found");
        }

        return new UserDetailsImpl(user);
    }
}
