package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.List;

public class OrganizerGroup implements GroupComponent {

    List<OrganizerService> organizerServices = new ArrayList();

    @Override
    public Integer calculateScore() {
        Integer score = 0;
        for (OrganizerService organizerService : organizerServices) {
            score += organizerService.calculateScore();
        }
        return score / organizerServices.size();
    }

}
