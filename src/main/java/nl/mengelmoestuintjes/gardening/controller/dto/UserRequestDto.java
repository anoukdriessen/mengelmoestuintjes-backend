package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.model.users.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRequestDto {
    public String username;
    public String password;
    public int lvl;
    public long xp;
    public String name;
    public LocalDate birthday;
    public Province province;
    public LocalDateTime memberSince;
    public UserRole role;
    public long levelUpLimit;

    public User toUser() {
        User user = new User ();
        user.setUsername(username);
        user.setPassword(password);
        user.setLvl(lvl);
        user.setXp(xp);
        user.setName(name);
        user.setBirthday(birthday);
        user.setProvince(province);
        user.setMemberSince(memberSince);
        user.setLastActivity(LocalDateTime.now());
        user.setRole(role);
        user.setLevelUpLimit(levelUpLimit);

        return user;
    }
}
