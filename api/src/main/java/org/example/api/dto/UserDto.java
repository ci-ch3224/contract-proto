package org.example.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.api.global.model.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserDto {
    private String userId;
    private String userName;

    public UserDto(User entity) {
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
    }

    public static UserDto of(User user) {
        return new UserDto(user);
    }
}
