package nl.mengelmoestuintjes.gardening.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// stap 1 definieer de Controller
@RestController
public class NameController {

    private List<String> names;

    public NameController() {
        names = new ArrayList<>();
        names.add("Anouk");
        names.add("Kevin");
        names.add("Guus");
        names.add("Zorro");
    }

    @GetMapping(value = "/names")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllNames() {
        return this.names;
    }

    // pathVariable geeft aan Spring boot aan: kijk in het pad naar parameter
    @GetMapping(value = "/names/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getNameById(@PathVariable int id) {
        return this.names.get(id);
    }

    // de data komt binnen in een requestBody
    @PostMapping(value = "/names")
    @ResponseStatus(HttpStatus.CREATED)
    public String newName(@RequestBody String nameToAdd) {
        names.add(nameToAdd);
        // visueel extratje voor de gebruiker
        return "Added " + nameToAdd;
    }

    @DeleteMapping(value = "/names/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteName(@PathVariable int id) {
        String deleted = "nothing";

        if (!this.getNameById(id).isEmpty()) {
            deleted = this.getNameById(id);
            this.names.remove(id);
        }

        return "Deleted " + deleted;
    }

}
