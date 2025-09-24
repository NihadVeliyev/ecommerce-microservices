package msuser.service;

import lombok.RequiredArgsConstructor;
import msuser.dto.UserReq;
import msuser.dto.UserRes;
import msuser.mapper.UserMapper;
import msuser.model.User;
import msuser.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    //TODO createUser deleteUser getUserById

    public  UserRes createUser(UserReq userReq){
        User user=userMapper.toEntity(userReq);
        userRepository.save(user);
        UserRes userRes=userMapper.toResponse(user);
        return userRes;

    }







}
