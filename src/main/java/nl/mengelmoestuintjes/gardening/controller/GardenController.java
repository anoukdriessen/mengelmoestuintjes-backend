package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.GardenRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.GardenResponseDto;
import nl.mengelmoestuintjes.gardening.model.garden.Garden;
import nl.mengelmoestuintjes.gardening.service.GardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tuintjes")
@CrossOrigin
public class GardenController {
    private final GardenService service;
    @Autowired
    public GardenController( GardenService service ) {
        this.service = service;
    }

    @PostMapping
    public GardenResponseDto newGarden( @RequestBody GardenRequestDto toAdd ) {
        Garden garden = service.newGarden( toAdd.toGarden() );
        return GardenResponseDto.fromGarden( garden );
    }

    @GetMapping
    public List<GardenResponseDto> getAll() {
        List<GardenResponseDto> all = new ArrayList<>();
        Iterable<Garden> gardens = service.getAll();

        for ( Garden g : gardens) {
            all.add(
                    GardenResponseDto.fromGarden( g )
            );
        }

        return all;
    }

    @GetMapping(value = "/{id}")
    public GardenResponseDto getById( @PathVariable( "id" ) long id) {
        Garden garden = service.getById( id );
        return GardenResponseDto.fromGarden( garden );
    }

    @PutMapping(value = "/{id}")
    public GardenResponseDto update( @PathVariable( "id" ) long id, @RequestBody Garden modified ) {
        service.update( id, modified );
        return GardenResponseDto.fromGarden( modified );
    }

    @DeleteMapping(value = "/{id}")
    public void delete( @PathVariable( "id" ) long id ) {
        service.delete( id );
    }

}
