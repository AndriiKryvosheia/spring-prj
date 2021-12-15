package com.oched.booksprj.services;

import com.oched.booksprj.entities.UserEntity;
import com.oched.booksprj.enumerations.UserRole;
import com.oched.booksprj.repositories.UserRepository;
import com.oched.booksprj.requests.ActionRequest;
import com.oched.booksprj.requests.DeleteUserRequest;
import com.oched.booksprj.requests.NewUserRequest;
import com.oched.booksprj.responses.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoResponse addNewUser(NewUserRequest request) {
        List<UserRole> roleList = new ArrayList<>();
        roleList.add(UserRole.ROLE_USER);

        if(request.getRole().equals("ADMIN") || request.getRole().equals("SUPERADMIN")){
            roleList.add(UserRole.ROLE_ADMIN);
        }
        if(request.getRole().equals("SUPERADMIN")) {
            roleList.add(UserRole.ROLE_SUPERADMIN);
        }

        this.userRepository.save(new UserEntity(
                request.getLogin(),
                request.getEmail(),
                this.passwordEncoder.encode(request.getPassword()),
                roleList
        ));

        return new UserInfoResponse(
                request.getLogin(),
                request.getEmail()
        );
    }

    public List<UserInfoResponse> getUsersList() {
        List<UserEntity> list = this.userRepository.findAll();

        return list.stream().map(
                user -> new UserInfoResponse(
                        user.getLogin(),
                        user.getEmail()
                )
        ).collect(Collectors.toList());
    }

    public void deleteUser(DeleteUserRequest request) {
        Optional<UserEntity> optionalUser = userRepository.findByLogin(request.getLogin());
        if(optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            userRepository.delete(user);
        }
    }
}
