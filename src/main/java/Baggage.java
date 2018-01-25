public class Baggage {
    private Integer weight;
    private Integer volume;

    public Baggage(Integer weight, Integer volume) {
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
}
