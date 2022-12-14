package group11.EventFiesta.best5;

import java.util.*;

public class OrganizerGroup extends GroupComponent {

    List<GroupComponent> organizerServices = new ArrayList<>();

    private Integer id;

    Double score = 0.0;
    @Override
    public Double calculateScore() {
        System.out.println("Inside OrganizerGroup.calculateScore()");
        if(score == 0.0) {
            Double avgScore = 0.0;
            for (GroupComponent organizerService : organizerServices) {
                avgScore += organizerService.calculateScore();
            }
            score =  avgScore / organizerServices.size();
        }
        return score;
    }

    public void add(GroupComponent service) {
        organizerServices.add(service);
    }

    public void remove(GroupComponent service) {
        organizerServices.remove(service);
    }

    public List<GroupComponent> getOrganizerServices() {
        return organizerServices;
    }

    public Double getScore() {
        return Double.parseDouble(String.format("%.2f", score));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer groupId) {
        this.id = groupId;
    }

    @Override
    public String toString() {
        return "OrganizerGroup{" +
                "organizerServices=" + organizerServices +
                ", score=" + score +
                "}\n";
    }
}
