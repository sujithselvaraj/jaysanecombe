package in.jaysan.service;

import in.jaysan.dto.UserRequest;
import in.jaysan.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);

    Long findByUserId();
}
