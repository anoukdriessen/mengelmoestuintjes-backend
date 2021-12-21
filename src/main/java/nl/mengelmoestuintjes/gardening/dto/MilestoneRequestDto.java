package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.model.users.User;

public class MilestoneRequestDto {
    public String title;
    public String urlToBadge;
    public long points;
    public User owner;

    public Milestone toMilestone() {
        Milestone milestone = new Milestone(
                title,
                urlToBadge,
                points
        );
        milestone.setOwner(owner);
        return milestone;
    }
}
