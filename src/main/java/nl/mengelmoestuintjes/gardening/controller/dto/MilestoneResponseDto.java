package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.model.users.User;

public class MilestoneResponseDto {
    public long id;
    public String title;
    public String urlToBadge;
    public long points;
    public User owner;

    public static MilestoneResponseDto fromMilestone( Milestone milestone) {
        MilestoneResponseDto dto = new MilestoneResponseDto();

        dto.id = milestone.getId();
        dto.title = milestone.getTitle();
        dto.urlToBadge = milestone.getUrlToBadge();
        dto.points = milestone.getPoints();
        dto.owner = milestone.getOwner();

        return dto;
    }
}
