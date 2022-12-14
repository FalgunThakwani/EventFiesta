package group11.EventFiesta.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import group11.EventFiesta.EInvite.EInviteHandler;
import group11.EventFiesta.EInvite.EInviteModel;
import group11.EventFiesta.model.UserEvent;

@SessionAttributes({ "userEvent" })
@Controller
public class EInviteController {

    @GetMapping("/einvite")
    public String getEInvite(Model model) {
        model.addAttribute("eInviteModel", new EInviteModel());
        return "Einvite";
    }

    @PostMapping("/handleInvite")
    public String handleInvite(@ModelAttribute EInviteModel eInviteModel, @ModelAttribute UserEvent userEvent) {
        eInviteModel.setVenue(userEvent.getEventVenue());
        EInviteHandler handle = new EInviteHandler(eInviteModel);
        handle.AddTextInImage();
        return "DownloadEInvite";
    }

    @GetMapping("/einviteTest")
    public String handleInviteTest() {
        EInviteModel model = new EInviteModel();
        long dateTime = System.currentTimeMillis();
        model.setCeremonyName("wedding");
        model.setEventHost1("falgun");
        model.setEventHost2("zulekha");
        model.setTimeOfEvent(new Date(dateTime).toString());
        model.setVenue("Halifax");
        EInviteHandler handle = new EInviteHandler(model);
        handle.AddTextInImage();
        return "DownloadEInvite";
    }

}
