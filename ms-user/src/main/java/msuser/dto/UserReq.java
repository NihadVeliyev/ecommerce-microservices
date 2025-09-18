package msuser.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import msuser.enums.Role;
@Data
@Builder
public class UserReq {
    @NotBlank(message = "Username cannot be empty")
    @Size(min=3,max=45,message = "Username must be between 3 and 45 characters")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @Size(min=7,message = "Password must be at least 7 characters long")
    private String password;
    @Builder.Default
    private Role role=Role.USER;

}
