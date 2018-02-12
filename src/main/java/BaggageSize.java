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

    /**
     * Check if the current baggage is over weight compared to the given ref baggage
     * @param refBaggage
     * @return
     */
    public Boolean isOverWeight(BaggageSize refBaggage) {
        return refBaggage.getWeight() < this.weight;
    }

    /**
     * Check if the current baggage is over volume compared to the given ref baggage
     * @param refBaggage
     * @return
     */
    public Boolean isOverVolume(BaggageSize refBaggage) {
        return refBaggage.getVolume() < this.volume;
    }

    /**
     * Check if the current baggage is over capacity of the given ref baggage
     * @param refBaggage
     * @return
     */
    public Boolean isOverCapacity(BaggageSize refBaggage) {
        return this.isOverWeight(refBaggage) || this.isOverVolume(refBaggage);
    }

    /**
     * @param refBaggage
     * @return the price that the passenger has to pay for additional baggage
     */
    public Integer calculateOverCapacityPrice(BaggageSize refBaggage) {
        Integer additionnalPrice = 0;
        if (this.isOverVolume(refBaggage)) {
            additionnalPrice += 2 * (this.volume - refBaggage.getVolume());
        }
        if (this.isOverWeight(refBaggage)) {
            additionnalPrice += 5 * (this.weight - refBaggage.getWeight());
        }
        return additionnalPrice;
    }

    /**
     * Add baggage volume and weight to the current baggage
     * @param newBaggage
     */
    public void addBaggage(BaggageSize newBaggage) {
        this.volume += newBaggage.getVolume();
        this.weight += newBaggage.getWeight();
    }

    /**
     * Check if the size of the baggage is valid
     * @return true if the size is a valid size
     */
    public Boolean isValidSize() {
        return this.volume != null && this.weight != null && this.volume > 0 && this.weight > 0;
    }

}
