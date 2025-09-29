package msuser.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import msuser.dto.UserReq;
import msuser.dto.UserRes;
import msuser.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
     private final UserService userService;
     @PostMapping
     public ResponseEntity<UserRes> createUser(@Valid UserReq userReq){
          UserRes userRes1=userService.createUser(userReq);
          return  ResponseEntity.status(HttpStatus.CREATED).body(userRes1);
     }
     @GetMapping("/{id}")
     public ResponseEntity<UserRes> getUserById(@PathVariable Long id){
          return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
     }

}
