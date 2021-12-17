package nl.mengelmoestuintjes.gardening.controller.plant;

import nl.mengelmoestuintjes.gardening.controller.dto.plant.PlantRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.plant.PlantResponseDto;
import nl.mengelmoestuintjes.gardening.model.plants.Flower;
import nl.mengelmoestuintjes.gardening.model.plants.Fruit;
import nl.mengelmoestuintjes.gardening.model.plants.Herb;
import nl.mengelmoestuintjes.gardening.service.plants.FlowerService;
import nl.mengelmoestuintjes.gardening.service.plants.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("herbs")
public class HerbController {
    private final HerbService plantService;

    @Autowired
    public HerbController( HerbService plantService ) {
        this.plantService = plantService;
    }

    // Create
    @PostMapping
    public PlantResponseDto newPlant(@Valid @RequestBody PlantRequestDto toAdd ) {
        Herb plant = plantService.newPlant( toAdd.toHerb() );
        return  PlantResponseDto.fromHerb( plant );
    }

    // Read
    @GetMapping
    public List<PlantResponseDto> getAllPlants(
            @RequestParam(name = "name", defaultValue = "") String name
    ) {
        List<PlantResponseDto> all = new ArrayList<>();
        Iterable<Herb> herbs = plantService.getAllPlants( name );

        for (Herb h : herbs) {
            all.add( PlantResponseDto.fromHerb( h ) );
        }
        return all;
    }
    @GetMapping(value = "/{id}")
    public PlantResponseDto getPostById(@PathVariable( "id" ) int id) {
        Herb plant = plantService.getPlantById( id );
        return PlantResponseDto.fromHerb( plant );
    }

    // Update
    @PutMapping(value = "/{id}")
    public PlantResponseDto updatePlant( @PathVariable( "id" ) int id, @RequestBody Herb modifiedPlant) {
        plantService.updatePlant( id, modifiedPlant );
        return PlantResponseDto.fromHerb( modifiedPlant );
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deletePlantById(@PathVariable( "id" ) int id) {
        plantService.deletePlantById(id);
    }

}
