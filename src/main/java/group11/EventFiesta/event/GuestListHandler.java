package group11.EventFiesta.event;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.model.Guest;
import group11.EventFiesta.model.UserGuestList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuestListHandler implements IGuestList{
    private IDBPersistence connection;

    public GuestListHandler(IDBPersistence connection) {
        this.connection = connection;
    }

    @Override
    public void storeGuestList(UserGuestList guestList) throws Exception {
        ArrayList<Guest> guests= guestList.getGuests();
        for(int i=0; i<guests.size(); i++) {
            Guest guest = guests.get(i);
            Object[] params = createParams(guestList.getEventId(), guest);
            List<Map<String, Object>> resultSet = connection.loadData("sp_storeUserGuestList", params);
        }
    }

    @Override
    public UserGuestList loadGuestList(int eventId) throws Exception {
        Object[] param = {eventId};
        List<Map<String, Object>> resultSet = connection.loadData("sp_getUserGuestList", param);
        UserGuestList ugl = new UserGuestList();
        for(int i=0;i<resultSet.size(); i++){
            Map<String, Object> row = resultSet.get(i);
            if(i==0){
                ugl.setEventId((int)row.get("event_id"));
            }
            Guest guest = new Guest();
            guest.setGuestId((int)row.get("user_guest_id"));
            guest.setGuestName(row.get("user_guest_name").toString());
            guest.setContactNo((int)row.get("user_guest_contact"));
            guest.setInvited(((int)row.get("invited") == 1) ? true : false);
//            guest.setInvited((int)row.get("invited"));
            guest.setRsvp(((int)row.get("rsvp") == 1) ? true : false);
//            guest.setRsvp((int)row.get("rsvp"));
            ugl.addGuest(guest);
        }
        return ugl;
    }
    private Object[] createParams(int eventId, Guest guest) {
        Object[] params = {eventId, guest.getGuestName(), guest.getContactNo(), guest.getInvited(), guest.getRsvp()};
        System.out.println(params.length);
        return params;
    }

}
