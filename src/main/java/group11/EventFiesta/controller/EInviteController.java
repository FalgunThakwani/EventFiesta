package group11.EventFiesta.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import group11.EventFiesta.EInvite.EInviteHandler;
import group11.EventFiesta.EInvite.EInviteModel;

@SessionAttributes({ "userEventQuestionnaire" })
@Controller
public class EInviteController {

    @GetMapping("/einvite")
    public String getEInvite(Model model) {
        model.addAttribute("eInviteModel", new EInviteModel());
        return "Einvite";
    }

    @PostMapping("/handleInvite")
    public String handleInvite(@ModelAttribute EInviteModel eInviteModel) {
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
