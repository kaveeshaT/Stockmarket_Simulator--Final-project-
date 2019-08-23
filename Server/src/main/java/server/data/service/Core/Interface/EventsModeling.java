package server.data.service.Core.Interface;

public class EventsModeling {
    public String EventName;
    public int MinRange;
    public int MaxRange;
    public float Probability;
    public int MinDuration;
    public int MaxDuration;

    public EventsModeling() {
    }

    public EventsModeling(String eventName, int minRange, int maxRange, float probability, int minDuration, int maxDuration) {
        EventName = eventName;
        MinRange = minRange;
        MaxRange = maxRange;
        Probability = probability;
        MinDuration = minDuration;
        MaxDuration = maxDuration;
    }
}