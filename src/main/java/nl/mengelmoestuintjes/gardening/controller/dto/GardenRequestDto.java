package nl.mengelmoestuintjes.gardening.controller.dto;

import nl.mengelmoestuintjes.gardening.model.garden.Garden;

public class GardenRequestDto {
    public String name;
    public int x;
    public int y;

    public Garden toGarden() {
        return new Garden(
            name,
            x,
            y
        );
    }
}
