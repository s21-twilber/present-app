package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.RegistrationUserDto;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.repository.UserRepository;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.example.view.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createNewUser(RegistrationUserDto registrationUser)  {
        User user = new User();
        user.setEmail(registrationUser.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        user.setRole(roleService.getRole(RolesEnum.ROLE_USER));
        user.setFullName(registrationUser.getFullName());
        user.setDepartment(registrationUser.getDepartment());
        user.setPosition(registrationUser.getPosition());
        user.setBirthDate(registrationUser.getBirthDate());
        user.setEmployeeDate(registrationUser.getEmployeeDate());
        user.setPhoneNumber(registrationUser.getPhoneNumber());

        log.info("Create new user {}", user.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, RegistrationUserDto registrationUser) {
        User user = new User();
        user.setId(userId);
        user.setEmail(registrationUser.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        user.setFullName(registrationUser.getFullName());
        user.setDepartment(registrationUser.getDepartment());
        user.setPosition(registrationUser.getPosition());
        user.setBirthDate(registrationUser.getBirthDate());
        user.setEmployeeDate(registrationUser.getEmployeeDate());
        user.setPhoneNumber(registrationUser.getPhoneNumber());

        log.info("Update user information {}", user.getEmail());

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> updateRole(Long userId, String roleName)  {
        User user = findById(userId);
        user.setRole(roleService.getRole(RolesEnum.valueOf(roleName)));
        userRepository.save(user);
        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().getName().name()));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
            return user;
    }


    @Override
    public List<User> findByRole(RolesEnum role) {
        List<User> users = userRepository.findUsersByRole(roleService.getRole(role)).orElseThrow(() ->
                new UsernameNotFoundException("Users not found"));
        return users;
    }

    @Override
    public List<User> findByRole(int roleId) {
        List<User> users = userRepository.findUsersByRole(roleService.getRoleById(roleId)).orElseThrow(() ->
                new UsernameNotFoundException("Users not found"));
        return users;
    }


    @Override
    public Boolean existsEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}
