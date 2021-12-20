//package nl.mengelmoestuintjes.gardening.controller.plant;
//
//import nl.mengelmoestuintjes.gardening.controller.dto.PlantRequestDto;
//import nl.mengelmoestuintjes.gardening.controller.dto.PlantResponseDto;
//import nl.mengelmoestuintjes.gardening.model.plants.Vegetable;
//import nl.mengelmoestuintjes.gardening.service.plants.VegetableService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("vegetables")
//public class VegetableController {
//    private final VegetableService plantService;
//
//    @Autowired
//    public VegetableController( VegetableService plantService ) {
//        this.plantService = plantService;
//    }
//
//    // Create
//    @PostMapping
//    public PlantResponseDto newPlant(@Valid @RequestBody PlantRequestDto toAdd ) {
//        Vegetable plant = plantService.newPlant( toAdd.toVegetable() );
//        return  PlantResponseDto.fromVegetable( plant );
//    }
//
//    // Read
//    @GetMapping
//    public List<PlantResponseDto> getAllPlants(
//            @RequestParam(name = "name", defaultValue = "") String name
//    ) {
//        List<PlantResponseDto> all = new ArrayList<>();
//        Iterable<Vegetable> vegetables = plantService.getAllPlants( name );
//
//        for (Vegetable v : vegetables) {
//            all.add( PlantResponseDto.fromVegetable( v ) );
//        }
//        return all;
//    }
//    @GetMapping(value = "/{id}")
//    public PlantResponseDto getPostById(@PathVariable( "id" ) int id) {
//        Vegetable plant = plantService.getPlantById( id );
//        return PlantResponseDto.fromVegetable( plant );
//    }
//    // Update
//    @PutMapping(value = "/{id}")
//    public PlantResponseDto updatePlant( @PathVariable( "id" ) int id, @RequestBody Vegetable modifiedPlant) {
//        plantService.updatePlant( id, modifiedPlant );
//        return PlantResponseDto.fromVegetable( modifiedPlant );
//    }
//
//    // Delete
//    @DeleteMapping(value = "/{id}")
//    public void deletePlantById(@PathVariable( "id" ) int id) {
//        plantService.deletePlantById(id);
//    }
//
//}
