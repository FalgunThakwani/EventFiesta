package group11.EventFiesta.event;

import group11.EventFiesta.model.UserEvents;
import group11.EventFiesta.model.UserGuestList;

import java.util.ArrayList;

public interface IGuestList {
    public abstract UserGuestList loadGuestList(int eventId) throws Exception;
    public abstract void storeGuestList(UserGuestList guestList) throws Exception;
}
