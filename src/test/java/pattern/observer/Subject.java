package pattern.observer;

interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
