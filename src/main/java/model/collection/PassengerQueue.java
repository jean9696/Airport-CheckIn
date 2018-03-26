package model.collection;

import model.entity.Passenger;

import java.util.*;

public class PassengerQueue extends Observable implements Queue<Passenger> {

    private LinkedList<Passenger> passengers;

    public PassengerQueue(LinkedList<Passenger> passengers) {
        super();
        this.passengers = passengers;
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
        boolean reply = passengers.add(passenger);
        setChanged();
        notifyObservers();
        return reply;
    }

    @Override
    public boolean remove(Object o) {
        boolean reply = passengers.remove(o);
        setChanged();
        notifyObservers();
        return reply;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return passengers.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Passenger> c) {
        //notifyObservers(c);
        return passengers.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        //notifyObservers(c);
        return passengers.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //notifyObservers(c);
        return passengers.retainAll(c);
    }

    @Override
    public void clear() {
        passengers.clear();
    }

    @Override
    public boolean offer(Passenger passenger) {
        //notifyObservers(passenger);
        return passengers.offer(passenger);
    }

    @Override
    public Passenger remove() {
        //notifyObservers();
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

    public LinkedList<Passenger> getPassengers() {
        return this.passengers;
    }
}
