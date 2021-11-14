package br.edu.ufersa.leon.leon.services;

import br.edu.ufersa.leon.leon.dtos.user.EditUserProfileDTO;
import br.edu.ufersa.leon.leon.entities.Role;
import br.edu.ufersa.leon.leon.entities.RoleType;
import br.edu.ufersa.leon.leon.entities.User;
import br.edu.ufersa.leon.leon.repositories.RoleRepository;
import br.edu.ufersa.leon.leon.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    public static final String DEFAULT_AVATAR_URL = "https://i.imgur.com/QdLOFBP.jpg";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public Optional<User> save(User user) {
        var userAlreadyExists = userRepository.findUserByEmail(user.getEmail()) != null;
        if (userAlreadyExists) {
            return Optional.empty();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatarURL(DEFAULT_AVATAR_URL);
        var role = roleRepository.findByName(RoleType.USER.getName());
        user.setRoles(List.of(role));
        return Optional.of(userRepository.save(user));
    }

    public User findUserByEmail(String email) {
        var user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = findUserByEmail(email);
        var authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User update(String email, EditUserProfileDTO userProfile) {
        var user = findUserByEmail(email);
        user.setName(userProfile.getName());
        user.setAddress(userProfile.getAddress());
        user.setBirthday(userProfile.getBirthday());
        user.setAvatarURL(userProfile.getAvatarURL());
        return userRepository.save(user);
    }
}
