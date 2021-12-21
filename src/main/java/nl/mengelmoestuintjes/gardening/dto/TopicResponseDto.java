package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.academy.Category;
import nl.mengelmoestuintjes.gardening.model.academy.Topic;

import java.time.LocalDate;

public class TopicResponseDto {
    public long id;
    public Category category;
    public String title;
    public String description;
    public LocalDate createdAt;
    public LocalDate modifiedAt;

    public static TopicResponseDto fromTopic( Topic topic ) {
        TopicResponseDto dto = new TopicResponseDto();

        dto.id = topic.getId();
        dto.category = topic.getCategory();
        dto.title = topic.getTitle();
        dto.description = topic.getDescription();
        dto.createdAt = topic.getCreatedAt();
        dto.modifiedAt = topic.getModifiedAt();

        return dto;
    }
}
