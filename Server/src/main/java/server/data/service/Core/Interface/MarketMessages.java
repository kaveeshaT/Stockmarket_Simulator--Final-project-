package server.data.service.Core.Interface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface MarketMessages {
    public class GetMarket implements Serializable {
        private int Id;
        private String Name;
        private String DisplayName;
        private int SectorId;
        private float Price;

        public GetMarket(int id, String name, String displayName, int sectorId, float price) {
            Id = id;
            Name = name;
            DisplayName = displayName;
            SectorId = sectorId;
            Price = price;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getDisplayName() {
            return DisplayName;
        }

        public void setDisplayName(String displayName) {
            DisplayName = displayName;
        }

        public int getSectorId() {
            return SectorId;
        }

        public void setSectorId(int sectorId) {
            SectorId = sectorId;
        }

        public float getPrice() {
            return Price;
        }

        public void setPrice(float price) {
            Price = price;
        }
    }
    class GetTurn implements Serializable {
        private final String turn;

        public GetTurn(String turn) {
            this.turn = turn;
        }

        public String getTurn() {
            return turn;
        }
    }


}
