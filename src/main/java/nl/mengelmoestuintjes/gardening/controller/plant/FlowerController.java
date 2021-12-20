package nl.mengelmoestuintjes.gardening.controller.plant;

import nl.mengelmoestuintjes.gardening.controller.dto.PlantRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.PlantResponseDto;
import nl.mengelmoestuintjes.gardening.model.plants.Flower;
import nl.mengelmoestuintjes.gardening.service.plants.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("flowers")
public class FlowerController {
    private final FlowerService plantService;

    @Autowired
    public FlowerController( FlowerService plantService ) {
        this.plantService = plantService;
    }

    // Create
    @PostMapping
    public PlantResponseDto newPlant( @Valid @RequestBody PlantRequestDto toAdd ) {
        Flower plant = plantService.newPlant( toAdd.toFlower() );
        return  PlantResponseDto.fromFlower( plant );
    }

    // Read
    @GetMapping
    public List<PlantResponseDto> getAllPlants(
            @RequestParam(name = "name", defaultValue = "") String name
    ) {
        List<PlantResponseDto> all = new ArrayList<>();
        Iterable<Flower> flowers = plantService.getAllPlants( name );

        for (Flower f : flowers) {
            all.add( PlantResponseDto.fromFlower( f ) );
        }
        return all;
    }

    @GetMapping(value = "/{id}")
    public PlantResponseDto getPostById(@PathVariable( "id" ) int id) {
        Flower plant = plantService.getPlantById( id );
        return PlantResponseDto.fromFlower( plant );
    }

    // Update
    @PutMapping(value = "/{id}")
    public PlantResponseDto updatePlant( @PathVariable( "id" ) int id, @RequestBody Flower modifiedPlant) {
        plantService.updatePlant( id, modifiedPlant );
        return PlantResponseDto.fromFlower( modifiedPlant );
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deletePlantById(@PathVariable( "id" ) int id) {
        plantService.deletePlantById(id);
    }

}
