package server.data.service.Core.Interface;

public class Stock {
    public int Id;
    public String Name;
    public String DisplayName;
    public int SectorId;
    public float Price;

    public Stock() {

    }

    public Stock(int id, String name, String displayName, int sectorId, float price) {
        Id = id;
        Name = name;
        DisplayName = displayName;
        SectorId = sectorId;
        Price = price;
    }
}
