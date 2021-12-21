package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.academy.Category;
import nl.mengelmoestuintjes.gardening.model.academy.Topic;

import java.time.LocalDate;

public class TopicRequestDto {
    public Category category;
    public String title;
    public String description;
    public LocalDate createdAt;
    public LocalDate modifiedAt;


    public Topic toTopic() {
        Topic topic = new Topic ();

        topic.setCategory( category );
        topic.setTitle( title );
        topic.setDescription( description );
        topic.setCreatedAt( createdAt );
        topic.setModifiedAt( LocalDate.now() );

        return topic;
    }
}
