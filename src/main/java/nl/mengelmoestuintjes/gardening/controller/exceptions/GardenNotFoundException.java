package nl.mengelmoestuintjes.gardening.controller.exceptions;

import nl.mengelmoestuintjes.gardening.model.garden.Garden;

public class GardenNotFoundException extends RuntimeException {
    public GardenNotFoundException(long id) {
        super("Garden with id " + id + " not found");
    }
    public GardenNotFoundException(Garden garden) {
        super("Garden " + garden + " not found");
    }
    public GardenNotFoundException() {
        super("Cannot find garden");
    }

}
