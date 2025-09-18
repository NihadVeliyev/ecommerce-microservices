package msuser.dto;

import lombok.Builder;
import lombok.Data;
import msuser.enums.Role;

import java.time.LocalDateTime;

@Builder
@Data
public class UserRes {
    private Long id;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Role role;

}
