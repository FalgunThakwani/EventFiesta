package group11.EventFiesta;

import group11.EventFiesta.best5.GetBestNOptions;
import group11.EventFiesta.best5.GroupComponent;
import group11.EventFiesta.best5.OrganizerGroup;
import group11.EventFiesta.best5.OrganizerService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GetBestFiveOptionsTest {


    private HashMap<String, List<GroupComponent>> getOrganizerServices() {

        HashMap<String, List<GroupComponent>> map = new HashMap();

        List<GroupComponent> services = new ArrayList<>();
        services.add(new OrganizerService("catering",5.0));
        services.add(new OrganizerService("catering",4.0));
        map.put("catering", services);

        services = new ArrayList<>();
        services.add(new OrganizerService("decoration",3.0));
        services.add(new OrganizerService("decoration",5.0));
        services.add(new OrganizerService("decoration",7.0));
        map.put("decoration", services);

        services = new ArrayList<>();
        services.add(new OrganizerService("auditorium",4.0));
        map.put("auditorium", services);

        return map;

    }
    @Test
    public void getBestNGroupsTest() {
        int n = 5;
        HashMap<String, List<GroupComponent>> map = getOrganizerServices();
        System.out.println(map);
        List<List<GroupComponent>> servicesAndProviders = new ArrayList<>();
        for (List<GroupComponent> serviceProviders : map.values()) {
            Collections.sort(serviceProviders, Collections.reverseOrder());
            List<GroupComponent> bestFiveServiceProviders = new ArrayList<>(serviceProviders);
            if (serviceProviders.size() > n) {
                bestFiveServiceProviders = serviceProviders.stream().limit(n).collect(Collectors.toList());
            }
            servicesAndProviders.add(bestFiveServiceProviders);
        }
        List<GroupComponent> allGroups = new ArrayList<>();
        GetBestNOptions getBestNOptions = new GetBestNOptions();
        getBestNOptions.getAllGroups(servicesAndProviders, allGroups, new OrganizerGroup());
        List<GroupComponent> bestNGroups = getBestNOptions.getBestNGroups(allGroups, n);
        System.out.println("best n groups: " + bestNGroups);
    }
}
