package nl.mengelmoestuintjes.gardening.dto;

import nl.mengelmoestuintjes.gardening.model.garden.Garden;

public class GardenResponseDto {
    public long id;
    public String name;
    public int x;
    public int y;

    public static GardenResponseDto fromGarden( Garden garden ) {
        GardenResponseDto dto = new GardenResponseDto();

        dto.id = garden.getId();
        dto.name = garden.getName();
        dto.x = garden.getX();
        dto.y = garden.getY();

        return dto;
    }
}
