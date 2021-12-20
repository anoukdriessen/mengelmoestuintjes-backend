package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.MilestoneRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.MilestoneResponseDto;
import nl.mengelmoestuintjes.gardening.model.Milestone;
import nl.mengelmoestuintjes.gardening.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/milestones")
@CrossOrigin
public class MilestoneController {
    private final MilestoneService service;
    @Autowired
    public MilestoneController( MilestoneService service ) {
        this.service = service;
    }

    @PostMapping
    public MilestoneResponseDto newMilestone( @RequestBody MilestoneRequestDto toAdd ) {
        Milestone milestone = service.newMilestone( toAdd.toMilestone() );
        return MilestoneResponseDto.fromMilestone( milestone );
    }

    @GetMapping
    public List<MilestoneResponseDto> getAll() {
        List<MilestoneResponseDto> all = new ArrayList<>();
        Iterable<Milestone> milestones = service.getAll();

        for ( Milestone m : milestones) {
            all.add(
                    MilestoneResponseDto.fromMilestone( m )
            );
        }

        return all;
    }

    @GetMapping(value = "/{id}")
    public MilestoneResponseDto getById(@PathVariable( "id" ) long id) {
        Milestone milestone = service.getById( id );
        return MilestoneResponseDto.fromMilestone( milestone );
    }

    @PutMapping(value = "/{id}")
    public MilestoneResponseDto update( @PathVariable( "id" ) long id, @RequestBody Milestone modified ) {
        service.update( id, modified );
        return MilestoneResponseDto.fromMilestone( modified );
    }

    @DeleteMapping(value = "/{id}")
    public void delete( @PathVariable( "id" ) long id ) {
        service.delete( id );
    }

}
