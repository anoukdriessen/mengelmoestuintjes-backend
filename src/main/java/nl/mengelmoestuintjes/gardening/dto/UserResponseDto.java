package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.model.plants.Plant;
import nl.mengelmoestuintjes.gardening.model.posts.Post;
import nl.mengelmoestuintjes.gardening.model.tasks.Task;
import nl.mengelmoestuintjes.gardening.model.users.Province;
import nl.mengelmoestuintjes.gardening.model.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserResponseDto {
    public String username;
    public String password;
    public boolean enabled;
    public String email;
    public Set<String> authorities;
//    public int lvl;
//    public long xp;
//    public long levelUpLimit;
    public String name;
    public LocalDate birthday;
    public Province province;
    // TODO Profile picture
    public LocalDateTime memberSince;
    public LocalDateTime lastActivity;
    public List<Milestone> milestones;
    public List<Post> posts;
    public List<Post> favoritePosts;
    public List<Task> tasks;
    public List<Garden> gardens;
    public List<Plant> favoritePlants;
    // TODO friends

    public static UserResponseDto fromUser(User u ) {
        UserResponseDto dto = new UserResponseDto();

        dto.username = u.getUsername();
        dto.password = u.getPassword();
        dto.enabled = u.isEnabled();
        dto.email = u.getEmail();
// TODO       dto.authorities = u.getAuthorities();
//        dto.lvl = u.getLvl();
//        dto.xp = u.getXp();
//        dto.levelUpLimit = u.getLevelUpLimit();
        dto.name = u.getName();
        dto.birthday = u.getBirthday();
        dto.province = u.getProvince();
        dto.memberSince = u.getMemberSince();
        dto.milestones = u.getMilestones();
        dto.posts = u.getPosts();
        dto.favoritePosts = u.getFavoritePosts();
        dto.tasks = u.getTasks();
        dto.gardens = u.getGardens();
        dto.favoritePlants = u.getFavoritePlants();

        return dto;
    }
}
