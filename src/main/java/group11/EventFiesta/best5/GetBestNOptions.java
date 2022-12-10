package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetBestNOptions {

    public List<GroupComponent> getBestNGroups(List<GroupComponent> allGroups, Integer n) {
        Collections.sort(allGroups, Collections.reverseOrder());
        System.out.println(allGroups);
        List<GroupComponent> bestFiveGroups = allGroups.stream().limit(n).collect(Collectors.toList());
        return bestFiveGroups;
    }

    public void getAllGroups(List<List<GroupComponent>> servicesList, List<GroupComponent> allGroupCombinations, OrganizerGroup organizerGroup) {
        if (servicesList.isEmpty()) {
            OrganizerGroup group = new OrganizerGroup();
            for (GroupComponent organizerService : organizerGroup.organizerServices) {
                group.add(organizerService);
            }
            allGroupCombinations.add(group);
            return;
        }
        List origList = servicesList.remove(0);
        List<GroupComponent> organizerList = new ArrayList<>(origList);
        while (!organizerList.isEmpty()) {
            GroupComponent organizerService = organizerList.remove(0);
            organizerGroup.add(organizerService);
            getAllGroups(servicesList, allGroupCombinations, organizerGroup);
            organizerGroup.remove(organizerGroup.organizerServices.get(organizerGroup.organizerServices.size() - 1));
        }
        servicesList.add(0, origList);
    }
}
