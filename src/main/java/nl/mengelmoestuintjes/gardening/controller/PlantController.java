//package nl.mengelmoestuintjes.gardening.controller;
//
//import nl.mengelmoestuintjes.gardening.dto.PlantRequestDto;
//import nl.mengelmoestuintjes.gardening.dto.PlantResponseDto;
//import nl.mengelmoestuintjes.gardening.model.plants.*;
//import nl.mengelmoestuintjes.gardening.service.PlantService;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping ( value = "/plants" )
//@CrossOrigin
//public class PlantController {
//    private final PlantService service;
//    @Autowired
//    public PlantController( PlantService service ) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public PlantResponseDto newPlant(@Valid @RequestBody PlantRequestDto toAdd ) {
//        Plant plant = service.newPlant( toAdd.toPlant() );
//        return PlantResponseDto.fromPlant( plant );
//    }
//
//    @PostMapping(value = "/flowers")
//    public PlantResponseDto newFlower(@RequestBody PlantRequestDto toAdd ) {
//        Plant plant = service.newFlower( toAdd.toPlant() );
//        return PlantResponseDto.fromPlant( plant );
//    }
//
//    @PostMapping(value = "/fruits")
//    public PlantResponseDto newFruit(@RequestBody PlantRequestDto toAdd ) {
//        Plant plant = service.newFruit( toAdd.toPlant() );
//        return PlantResponseDto.fromPlant( plant );
//    }
//
//    @PostMapping(value = "/herbs")
//    public PlantResponseDto newHerb(@RequestBody PlantRequestDto toAdd ) {
//        Plant plant = service.newHerb( toAdd.toPlant() );
//        return PlantResponseDto.fromPlant( plant );
//    }
//
//    @PostMapping(value = "/vegetables")
//    public PlantResponseDto newVegetable(@RequestBody PlantRequestDto toAdd ) {
//        Plant plant = service.newVegetable( toAdd.toPlant() );
//        return PlantResponseDto.fromPlant( plant );
//    }
//
//    @GetMapping
//    public List<PlantResponseDto> getAllPlants(
//            @RequestParam(name = "name", defaultValue = "") String name,
//            @RequestParam(name = "category", defaultValue = "") Category category,
//            @RequestParam(name = "location", defaultValue = "") Location location,
//            @RequestParam(name = "outdoors", defaultValue = "") String outdoors,
//            @RequestParam(name = "indoors", defaultValue = "") String indoors,
//            @RequestParam(name = "harvest", defaultValue = "") String harvest
//    ) {
//        List<PlantResponseDto> all = new ArrayList<>();
//        Iterable<Plant> plants = service.getAll(
//                name, category, location, outdoors, indoors, harvest );
//
//        for (Plant p : plants) {
//            all.add( PlantResponseDto.fromPlant( p ) );
//        }
//
//        return all;
//    }
//
//    @GetMapping(value = "/{id}")
//    public PlantResponseDto getPlantById(@PathVariable("id") int id) {
//        Plant plant = service.getById( id );
//        return PlantResponseDto.fromPlant( plant );
//    }
//
//    @PutMapping(value = "/{id}")
//    public PlantResponseDto updatePlant(@PathVariable( "id" ) int id, @RequestBody Plant modified) {
//        service.update(id, modified);
//        return PlantResponseDto.fromPlant(modified);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public PlantResponseDto delete(@PathVariable( "id" ) int id) {
//        return PlantResponseDto.fromPlant( service.delete( id ) );
//    }
//
//}
