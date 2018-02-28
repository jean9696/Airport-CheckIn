package model;

import java.util.*;

import static model.Passenger.createFakeRandomPassenger;

public class PassengerQueue extends Observable implements Queue<Passenger> {

    private LinkedList<Passenger> passengers;

    public PassengerQueue() {
        super();
        this.passengers = new LinkedList<Passenger>();
    }

    public static PassengerQueue createFakeRandomQueue(Integer size) {
        PassengerQueue passengerQueue = new PassengerQueue();
        for (int i=0; i<size; i++) {
            passengerQueue.addFakeRandomPassenger();
        }
        return passengerQueue;
    }

    public void addFakeRandomPassenger() {
        this.add(createFakeRandomPassenger());
    }

    @Override
    public int size() {
        return passengers.size();
    }

    @Override
    public boolean isEmpty() {
        return passengers.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return passengers.contains(o);
    }

    @Override
    public Iterator<Passenger> iterator() {
        return passengers.iterator();
    }

    @Override
    public Object[] toArray() {
        return passengers.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return passengers.toArray(a);
    }

    @Override
    public boolean add(Passenger passenger) {
        notifyObservers(passenger);
        return passengers.add(passenger);
    }

    @Override
    public boolean remove(Object o) {
        notifyObservers(o);
        return passengers.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return passengers.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Passenger> c) {
        notifyObservers(c);
        return passengers.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        notifyObservers(c);
        return passengers.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        notifyObservers(c);
        return passengers.retainAll(c);
    }

    @Override
    public void clear() {
        passengers.clear();
    }

    @Override
    public boolean offer(Passenger passenger) {
        notifyObservers(passenger);
        return passengers.offer(passenger);
    }

    @Override
    public Passenger remove() {
        notifyObservers();
        return passengers.remove();
    }

    @Override
    public Passenger poll() {
        return passengers.poll();
    }

    @Override
    public Passenger element() {
        return passengers.element();
    }

    @Override
    public Passenger peek() {
        return passengers.peek();
    }
}
