public class BaggageSize {
    private Integer weight;
    private Integer volume;

    public BaggageSize(Integer weight, Integer volume) {
        this.weight = weight;
        this.volume = volume;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Boolean isOverWeight(BaggageSize refBaggage) {
        return refBaggage.getWeight() < this.weight;
    }

    public Boolean isOverVolume(BaggageSize refBaggage) {
        return refBaggage.getVolume() < this.volume;
    }

    public Boolean isOverCapacity(BaggageSize refBaggage) {
        return this.isOverWeight(refBaggage) || this.isOverVolume(refBaggage);
    }
}
