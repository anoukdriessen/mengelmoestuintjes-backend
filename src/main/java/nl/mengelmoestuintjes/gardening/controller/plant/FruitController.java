package nl.mengelmoestuintjes.gardening.controller.plant;

import nl.mengelmoestuintjes.gardening.controller.dto.PlantRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.PlantResponseDto;
import nl.mengelmoestuintjes.gardening.model.plants.Fruit;
import nl.mengelmoestuintjes.gardening.service.plants.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("fruits")
public class FruitController {
    private final FruitService plantService;

    @Autowired
    public FruitController( FruitService plantService ) {
        this.plantService = plantService;
    }

    // Create
    @PostMapping
    public PlantResponseDto newPlant(@Valid @RequestBody PlantRequestDto toAdd ) {
        Fruit plant = plantService.newPlant( toAdd.toFruit() );
        return  PlantResponseDto.fromFruit( plant );
    }

    // Read
    @GetMapping
    public List<PlantResponseDto> getAllPlants(
            @RequestParam(name = "name", defaultValue = "") String name
    ) {
        List<PlantResponseDto> all = new ArrayList<>();
        Iterable<Fruit> fruits = plantService.getAllPlants( name );

        for (Fruit f : fruits) {
            all.add( PlantResponseDto.fromFruit( f ) );
        }
        return all;
    }
    @GetMapping(value = "/{id}")
    public PlantResponseDto getPostById(@PathVariable( "id" ) int id) {
        Fruit plant = plantService.getPlantById( id );
        return PlantResponseDto.fromFruit( plant );
    }

    // Update
    @PutMapping(value = "/{id}")
    public PlantResponseDto updatePlant( @PathVariable( "id" ) int id, @RequestBody Fruit modifiedPlant) {
        plantService.updatePlant( id, modifiedPlant );
        return PlantResponseDto.fromFruit( modifiedPlant );
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public void deletePlantById(@PathVariable( "id" ) int id) {
        plantService.deletePlantById(id);
    }

}
