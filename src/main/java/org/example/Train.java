package org.example;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNumber;
    private List<List<Integer>> trainSeats;
    private Map<String, String> stationTime;
    private List<String> trainStops;

    public Train(String trainId, String trainNumber, Map<String, String> stationTime, List<String> trainStops) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.stationTime = stationTime;
        this.trainStops = trainStops;
    }
    public Train() {}
    public String getTrainId() {
        return trainId;
    }
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
    public String getTrainNumber() {
        return trainNumber;
    }
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
    public List<List<Integer>> getTrainSeats() {
        return trainSeats;
    }
    public void setTrainSeats(List<List<Integer>> trainSeats) {
        this.trainSeats = trainSeats;
    }
    public Map<String, String> getStationTime() {
        return stationTime;
    }
    public void setStationTime(Map<String, String> stationTime) {

        this.stationTime = stationTime;
    }
    public List<String> getTrainStops() {
        return trainStops;
    }
    public void setTrainStops(List<String> trainStops) {
        this.trainStops = trainStops;
    }
}
