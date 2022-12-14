package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetBestNOptions {

    public List<GroupComponent> getBestNGroups(Map<String, List<GroupComponent>> serviceProvidersMap, int n) {
        List<List<GroupComponent>> servicesAndProviders = getBestNServiceProvidersList(serviceProvidersMap, n);
        List<GroupComponent> allGroups = new ArrayList<>();
        getAllGroupCombinations(servicesAndProviders, allGroups, new OrganizerGroup());
        System.out.println(allGroups);
        List<GroupComponent> bestNGroups = getBestNGroups(allGroups, n);
        return bestNGroups;
    }

    private List<List<GroupComponent>> getBestNServiceProvidersList(Map<String, List<GroupComponent>> serviceProvidersMap, int n) {
        List<List<GroupComponent>> servicesAndProviders = new ArrayList<>();
        for (List<GroupComponent> serviceProviders : serviceProvidersMap.values()) {
            Collections.sort(serviceProviders, Collections.reverseOrder());
            List<GroupComponent> bestFiveServiceProviders = getFirstNGroups(serviceProviders, n);
            servicesAndProviders.add(bestFiveServiceProviders);
        }
        return servicesAndProviders;
    }

    private List<GroupComponent> getBestNGroups(List<GroupComponent> allGroups, int n) {
        for (GroupComponent component : allGroups) {
            component.calculateScore();
        }
        Collections.sort(allGroups, Collections.reverseOrder());
        System.out.println(allGroups);
        List<GroupComponent> bestNGroups = getFirstNGroups(allGroups, n);
        return bestNGroups;
    }

    private List<GroupComponent> getFirstNGroups(List<GroupComponent> allGroups, int n) {
        List<GroupComponent> firstNGroups = new ArrayList<>(allGroups);
        if (allGroups.size() > n) {
            firstNGroups = allGroups.stream().limit(n).collect(Collectors.toList());
        }
        return firstNGroups;
    }

    private void getAllGroupCombinations(List<List<GroupComponent>> servicesList, List<GroupComponent> allGroupCombinations, OrganizerGroup organizerGroup) {
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
            getAllGroupCombinations(servicesList, allGroupCombinations, organizerGroup);
            organizerGroup.remove(organizerGroup.organizerServices.get(organizerGroup.organizerServices.size() - 1));
        }
        servicesList.add(0, origList);
    }
}
