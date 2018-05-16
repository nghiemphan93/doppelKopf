package Model.ObserverModel;

public interface Observable {
    void register(Observer o);
    void unregister(Observer o);
    void pushNotify();
}
