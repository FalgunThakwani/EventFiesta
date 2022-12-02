package group11.EventFiesta.organizer;


import group11.EventFiesta.model.Organizer;

import javax.servlet.http.HttpServletRequest;

public interface ILoginHandler {

    void setNextHandler(ILoginHandler handler);

    boolean execute(Organizer organizer, HttpServletRequest request) throws Exception;

}
