package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.exceptions.RecordNotFoundException;
import nl.mengelmoestuintjes.gardening.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PlantsController {
    private PlantService plantService;

    @Autowired
    public PlantsController(PlantService plantService) {
        this.plantService = plantService;
    }

    /** GET requests **/

    // ALL
    @GetMapping("/plants")
    public ResponseEntity<Object> getAllPlants() {
        return ResponseEntity.ok().body(plantService.getPlants());
    }

    // ONE (by id)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPlantById(@PathVariable("id") long id) {
        long toFind = 1;
        if (id == toFind) { // TODO determine if id exists
            throw new RecordNotFoundException("ID cannot be found");
        }
        return ResponseEntity.ok().body(plantService.getPlantById((int) id));
    }

    // ALL that meet a specified criterion
    @GetMapping("/plants?category={category}")
    public ResponseEntity<Object> getAllPlantsFromCategory(@RequestParam String category) {
        String toFind = "flowers";
        if (toFind.equalsIgnoreCase(category)) { // TODO determine if category exists
            throw new RecordNotFoundException("Category cannot be found");
        }
        return ResponseEntity.ok(200);
    }

    /** POST request **/
    @PostMapping("/plants")
    public ResponseEntity<Object> newPlant(@RequestBody String name) {
//        return ResponseEntity.created().body(plantService.addPlant(plant)); TODO
        return ResponseEntity.ok(201);
    }

    /** DELETE request **/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePlantById(@PathVariable("id") int id) {
        int toFind = 1;
        if (id == toFind) { // TODO determine if id exists
            throw new RecordNotFoundException("ID cannot be found");
        }
//        return ResponseEntity.noContent().body(plantService.getPlantById((int) id))
        return ResponseEntity.ok(204);
    }

    // PUT request
    @PutMapping("/plants/{id}")
    public ResponseEntity<Object> updatePlantById(@PathVariable int id, @RequestBody String name) {
        int toFind = 1;
        if (toFind == id) { // TODO determine if id exists
            throw new RecordNotFoundException("ID cannot be found");
        }
        // 204 NO CONTENT = gelukt
//        return ResponseEntity.noContent();
        return ResponseEntity.ok(204); // TODO of 201?
    }

    // PATCH request
    @PatchMapping("/books/{id}")
    public ResponseEntity<Object> modifyPartOfPlant(@PathVariable int id, @RequestBody String name) {
        int toFind = 1;
        if (toFind == id) { // determine if id exists
            throw new RecordNotFoundException("ID cannot be found");
        }
        // 204 NO CONTENT = gelukt
//        return ResponseEntity.noContent();
        return ResponseEntity.ok(204);
    }
}
