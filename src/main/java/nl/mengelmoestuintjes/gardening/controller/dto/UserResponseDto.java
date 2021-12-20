package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.model.users.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserResponseDto {
    public String username;
    public String password;
    public int lvl;
    public long xp;
    public String name;
    public LocalDate birthday;
    public Province province;
    public LocalDateTime memberSince;
    public LocalDateTime lastActivity;
    public UserRole role;
    public long levelUpLimit;

    public static UserResponseDto fromUser( User user) {
        UserResponseDto dto = new UserResponseDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.lvl = user.getLvl();
        dto.xp = user.getXp();
        dto.name = user.getName();
        dto.birthday = user.getBirthday();
        dto.province = user.getProvince();
        dto.memberSince = user.getMemberSince();
        dto.lastActivity = user.getLastActivity();
        dto.role = user.getRole();
        dto.levelUpLimit = user.getLevelUpLimit();

        return dto;
    }
}
