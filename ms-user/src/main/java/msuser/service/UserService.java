package msuser.service;

import lombok.RequiredArgsConstructor;
import msuser.mapper.UserMapper;
import msuser.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


}
