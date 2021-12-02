package nl.mengelmoestuintjes.library.controller;

import nl.mengelmoestuintjes.library.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class to handle the Requests for plants
 * @author anoukdriessen
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/plants")
public class PlantsController {

    @Autowired
    private PlantService plantService;

    // method to search plants
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<Object> searchPlants(@RequestParam(name="name", defaultValue = "") String name,
                                               @RequestParam(name="category", defaultValue = "") String category) {
        return ResponseEntity.ok().body(plantService.getPlants(name, category));
    }

    // get request to get all plants
    @GetMapping("/plants")
    public ResponseEntity<Object> getAllPlants() {
        return ResponseEntity.ok(...);
    }

    // get request to get one plant with { id }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPlant(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(plantService.getPlantById(id));
    }

    // get request to get all books with specified category
    @GetMapping("/plants?category={category}")
    public ResponseEntity<Object> getAllPlantsWithCategory(@RequestParam String category) {
        return ...;
    }

    // post request to create a new plant
    @PostMapping("/plants")
    public ResponseEntity<Object> addPlant(@RequestBody String name){
        ...
        return ResponseEntity.created(...);
    }

    // delete request to delete a plant from collecion
    @DeleteMapping("/plants/{id}")
    public ResponseEntity<Object> deletePlant(@PathVariable int id) {
        ...
        return ResponseEntity.noContent();
    }

    // put request to replace a plant with a new plant
    @PutMapping("/plants/{id}")
    public ResponseEntity<Object> updatePlant(@PathVariable int id, @RequestBody String name) {
        ...
        return ResponseEntity.noContent();
    }

    // patch request to replace a part of plant
    @PatchMapping("/plants/{id}")
    public ResponseEntity<Object> modifyPlant(@PathVariable int id, @RequestBody String name) {
        ...
        return ResponseEntity.noContent()
    }
}
