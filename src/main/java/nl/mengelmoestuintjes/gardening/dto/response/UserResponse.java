package nl.mengelmoestuintjes.gardening.dto.response;

import nl.mengelmoestuintjes.gardening.controller.exceptions.InvalidException;
import nl.mengelmoestuintjes.gardening.dto.request.UserRequest;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    private String username;
    private String password;
    private boolean enabled;
    private List<String> authorities;
    private String email;
    private String level;
    private String xp;
    private String levelUpLimit;
    private String name;
    private LocalDate birthday;
    private Province province;
    private LocalDate memberSince;
    private LocalDateTime lastActivity;
    private List<Post> posts = new ArrayList<>();

    public User toUser(UserRequest request) {
        User user = new User();

        try {
            user.setUsername(request.getUsername());
        } catch (Exception e) {
            throw new InvalidException("username");
        }

        user.setEnabled(true);
        user.addAuthority("ROLE_USER");

        try {
            user.setEmail(request.getEmail());
        } catch (Exception e) {
            throw new InvalidException("email");
        }

        user.setLevel(request.getLevel());
        user.setXp(request.getXp());
        user.setLevelUpLimit(request.getLevelUpLimit());

        user.setName(request.getName());
        user.setBirthday(request.getBirthday());
        user.setProvince(request.getProvince());
        user.setMemberSince(request.getMemberSince());
        user.setLastActivity();
        user.setPosts(request.getPosts());

        return user;
    }
}
