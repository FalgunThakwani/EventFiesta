package group11.EventFiesta.event;

import group11.EventFiesta.model.UserEvents;

import java.util.ArrayList;

public interface IEvents {
    public abstract ArrayList<UserEvents> loadEvents(int userId) throws Exception;
}
