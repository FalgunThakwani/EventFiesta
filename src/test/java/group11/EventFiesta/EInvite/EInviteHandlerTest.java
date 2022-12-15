package group11.EventFiesta.EInvite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class EInviteHandlerTest {
    @Test
    public void AddTextInImage(){
        EInviteModel eObj = new EInviteModel();
        eObj.setCeremonyName("Wedding");
        eObj.setVenue("Halifax");
        eObj.setEventHost1("Falgun");
        eObj.setEventHost2("Thakwani");
        eObj.setTimeOfEvent("2022-12-4T07:48");

        IEInviteHandler ieInviteHandler = new EInviteHandler(eObj);
        ieInviteHandler.AddTextInImage();
        File file = new File ("src/main/resources/static/images/generatedEInvite.jpg");
        Assertions.assertTrue(file.exists());
    }
}
