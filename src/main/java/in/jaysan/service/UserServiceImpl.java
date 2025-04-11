package in.jaysan.service;

import in.jaysan.dto.UserRequest;
import in.jaysan.dto.UserResponse;
import in.jaysan.entity.UserEntity;
import in.jaysan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;
    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        UserEntity newUser=  convertToEntity(userRequest);
        newUser=userRepository.save(newUser);
        return  convertToResponse(newUser);


    }

    public Long findByUserId() {
        String loggedInUserEmail= authenticationFacade.getAuthentication().getName();
        UserEntity loggedInUser= userRepository.findByEmail(loggedInUserEmail).orElseThrow(()->new UsernameNotFoundException( "User not found"));
        return loggedInUser.getId() ;

    }


    // convert to entity
    private UserEntity convertToEntity(UserRequest request)
    {
        return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
    }


    // convert to response
    private UserResponse convertToResponse(UserEntity registeredUser)
    {
        return  UserResponse.builder()
                .id(registeredUser.getId())
                .name(registeredUser.getName())
                .email(registeredUser.getEmail())
                .build();
    }
}
