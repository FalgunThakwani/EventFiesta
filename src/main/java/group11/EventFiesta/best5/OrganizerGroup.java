package group11.EventFiesta.best5;

import java.util.*;

public class OrganizerGroup extends GroupComponent {

    List<GroupComponent> organizerServices = new ArrayList();

    @Override
    public Double calculateScore() {
        Double score = 0.0;
        for (GroupComponent organizerService : organizerServices) {
            score += organizerService.calculateScore();
        }
        return score / organizerServices.size();
    }

    public void add(GroupComponent service) {
        organizerServices.add(service);
    }

    @Override
    public String toString() {
        return "OrganizerGroup{" +
                "organizerServices=" + organizerServices +
                "}\n";
    }

    public void remove(GroupComponent service) {
        organizerServices.remove(service);
    }
}
