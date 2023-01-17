package mocking;

import java.awt.event.ActionListener;
import java.util.HashMap;

public class ActionListenersForMock {
    private static ActionListenersForMock instance;
    private HashMap<String, ActionListener> listeners;
    private ActionListenersForMock() {
        listeners = new HashMap<>();
    }

    public static ActionListenersForMock getInstance() {
        if(instance == null){
            instance = new ActionListenersForMock();
        }
        return instance;
    }

    public void register(String key, ActionListener a) {
        listeners.put(key, a);
    }

    public ActionListener get(String key) {
        return listeners.get(key);
    }
}
