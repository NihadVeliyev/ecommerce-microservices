package msuser.controller;

import lombok.RequiredArgsConstructor;
import msuser.dto.UserReq;
import msuser.dto.UserRes;
import msuser.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
     private final UserService userService;
     @PostMapping
     public ResponseEntity<UserRes> createUser(UserReq userReq){
          UserRes userRes1=userService.createUser(userReq);
          return  ResponseEntity.status(HttpStatus.CREATED).body(userRes1);
     }
     @GetMapping("/{id}")
     public ResponseEntity<UserRes> getUserById(Long id){
          return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
     }

}
