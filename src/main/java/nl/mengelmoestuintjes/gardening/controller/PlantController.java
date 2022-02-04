package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.model.garden.plants.Category;
import nl.mengelmoestuintjes.gardening.model.garden.plants.Plant;
import nl.mengelmoestuintjes.gardening.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( value = "api/planten" )
public class PlantController {
    private final PlantService service;
    @Autowired
    public PlantController( PlantService service ) {
        this.service = service;
    }

    @PostMapping
    public Plant newPlant(
            @RequestBody Plant toAdd
    ) {
        return service.newPlant( toAdd );
    }

    @PostMapping(value = "/flowers")
    public Plant newFlower(
            @RequestBody Plant toAdd
    ) {
        return service.newFlower( toAdd );
    }

    @PostMapping(value = "/fruits")
    public Plant newFruit(
            @RequestBody Plant toAdd
    ) {
        return service.newFruit( toAdd );
    }

    @PostMapping(value = "/herbs")
    public Plant newHerb(
            @RequestBody Plant toAdd
    ) {
        return service.newHerb( toAdd );
    }

    @PostMapping(value = "/vegetables")
    public Plant newVegetable(
            @RequestBody Plant toAdd
    ) {
        return service.newVegetable( toAdd );
    }

//    @GetMapping
//    public List<Plant> getAllPlants(
//            @RequestParam(name = "name", defaultValue = "") String name,
//            @RequestParam(name = "category", defaultValue = "") Category category,
//            @RequestParam(name = "location", defaultValue = "") Location location,
//            @RequestParam(name = "outdoors", defaultValue = "") String outdoors,
//            @RequestParam(name = "indoors", defaultValue = "") String indoors,
//            @RequestParam(name = "harvest", defaultValue = "") String harvest
//    ) {
    @GetMapping
    public Iterable<Plant> getAllPlants(
            @RequestParam(name = "category", defaultValue = "") Category category
    ) {
        return service.getAll( category );
    }

    @GetMapping(value = "/{id}")
    public Plant getPlantById(
            @PathVariable("id") int id
    ) {
        return service.getById( id );
    }

    @GetMapping(value = "plant/{name}")
    public Plant getPlantByName(
            @PathVariable("name") String name
    ) {
        return service.findPlantByName( name );
    }

    @PutMapping(value = "/{id}")
    public Plant updatePlant(
            @PathVariable( "id" ) int id,
            @RequestBody Plant modified
    ) {
        return service.update(id, modified);
    }

    @DeleteMapping(value = "/{id}")
    public Plant delete(
            @PathVariable( "id" ) int id
    ) {
        return service.delete( id );
    }

}
