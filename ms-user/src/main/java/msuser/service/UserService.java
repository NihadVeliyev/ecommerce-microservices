package msuser.service;

import lombok.RequiredArgsConstructor;
import msuser.dto.UserReq;
import msuser.dto.UserRes;
import msuser.mapper.UserMapper;
import msuser.model.User;
import msuser.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    public  UserRes createUser(UserReq userReq){
        User user=userMapper.toEntity(userReq);
        userRepository.save(user);
        UserRes userRes=userMapper.toResponse(user);
        return userRes;
    }
    public UserRes getUserById(Long id){
        return userMapper.toResponse(userRepository.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("User not found with the id: "+id)));
    }







}
