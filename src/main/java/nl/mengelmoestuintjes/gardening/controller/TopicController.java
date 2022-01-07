//package nl.mengelmoestuintjes.gardening.controller;
//
//import nl.mengelmoestuintjes.gardening.dto.TopicRequestDto;
//import nl.mengelmoestuintjes.gardening.dto.TopicResponseDto;
//import nl.mengelmoestuintjes.gardening.model.academy.Topic;
//import nl.mengelmoestuintjes.gardening.service.TopicService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping( value = "/topics")
//@CrossOrigin
//public class TopicController {
//    private final TopicService service;
//    @Autowired
//    public TopicController( TopicService service ) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public TopicResponseDto newTopic(@RequestBody TopicRequestDto toAdd ) {
//        Topic topic = service.newTopic( toAdd.toTopic() );
//        return TopicResponseDto.fromTopic( topic );
//    }
//
//    @GetMapping
//    public List<TopicResponseDto> getAll() {
//        List<TopicResponseDto> all = new ArrayList<>();
//        Iterable<Topic> topics = service.getAll();
//
//        for ( Topic t : topics) {
//            all.add(
//                    TopicResponseDto.fromTopic( t )
//            );
//        }
//
//        return all;
//    }
//
//    @GetMapping(value = "/{id}")
//    public TopicResponseDto getById( @PathVariable( "id" ) long id) {
//        Topic topic = service.getById( id );
//        return TopicResponseDto.fromTopic( topic );
//    }
//
//    @PutMapping(value = "/{id}")
//    public TopicResponseDto update( @PathVariable( "id" ) long id, @RequestBody Topic modified ) {
//        service.update( id, modified );
//        return TopicResponseDto.fromTopic( modified );
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public TopicResponseDto delete( @PathVariable( "id" ) long id ) {
//        return TopicResponseDto.fromTopic( service.delete( id ) );
//    }
//
//
//}
