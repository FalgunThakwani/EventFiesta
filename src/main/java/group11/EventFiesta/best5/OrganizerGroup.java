package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.List;

public class OrganizerGroup implements GroupComponent {

    List<GroupComponent> organizerServices = new ArrayList<GroupComponent>();

    @Override
    public Integer calculateScore() {
        Integer score = 0;
        for (GroupComponent organizerService : organizerServices) {
            score += organizerService.calculateScore();
        }
        return score / organizerServices.size();
    }

    @Override
    public void Add(GroupComponent child) {
        organizerServices.add(child);
    }

    @Override
    public void Remove(GroupComponent child) {
        organizerServices.remove(child);
    }

}
