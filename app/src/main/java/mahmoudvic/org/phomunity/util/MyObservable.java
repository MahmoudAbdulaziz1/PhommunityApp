package mahmoudvic.org.phomunity.util;

import java.util.Observable;

public class MyObservable extends Observable {
    private static MyObservable instance = null;
    private MyObservable() {
    }
    public static MyObservable getInstance() {
        if(instance == null) {
            instance = new MyObservable() ;
        }
        return instance;
    }
    public void sendData(Object data) {
        setChanged();
        notifyObservers(data);
    }
}
