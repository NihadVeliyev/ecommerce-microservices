package msuser.mapper;

import msuser.dto.UserReq;
import msuser.dto.UserRes;
import msuser.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
      @Mapping(target = "id",ignore = true)
      @Mapping(target = "updatedAt",ignore = true)
      @Mapping(target = "createdAt",ignore = true)
      User toEntity(UserReq userReq);
      UserRes toResponse(User user);

}
