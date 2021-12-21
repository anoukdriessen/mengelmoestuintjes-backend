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

public class UserRequestDto {
    public String username;
    public String password;
    public boolean enabled;
    public String email;
    public Set<String> authorities;
    public int lvl;
    public long xp;
    public long levelUpLimit;
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

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public String getEmail() {
        return email;
    }
    public int getLvl() {
        return lvl;
    }
    public long getXp() {
        return xp;
    }
    public long getLevelUpLimit() {
        return levelUpLimit;
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public Province getProvince() {
        return province;
    }
    public LocalDateTime getMemberSince() {
        return memberSince;
    }
    public LocalDateTime getLastActivity() {
        return lastActivity;
    }
    public List<Milestone> getMilestones() {
        return milestones;
    }
    public List<Post> getPosts() {
        return posts;
    }
    public List<Post> getFavoritePosts() {
        return favoritePosts;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public List<Garden> getGardens() {
        return gardens;
    }
    public List<Plant> getFavoritePlants() {
        return favoritePlants;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities( Set<String> authorities ) {
        this.authorities = authorities;
    }

    public User toUser() {
        User u = new User ();

        u.setUsername(username);
        u.setPassword(password);
        u.setEnabled(enabled);
        u.setEmail(email);
// TODO       u.setAuthorities(authorities);
        u.setLvl(lvl);
        u.setXp(xp);
        u.setLevelUpLimit(levelUpLimit);
        u.setName(name);
        u.setBirthday(birthday);
        u.setProvince(province);
        u.setMemberSince(memberSince);
        u.setMilestones(milestones);
        u.setPosts(posts);
        u.setFavoritePosts(favoritePosts);
        u.setTasks(tasks);
        u.setGardens(gardens);
        u.setFavoritePlants(favoritePlants);

        return u;
    }
}
