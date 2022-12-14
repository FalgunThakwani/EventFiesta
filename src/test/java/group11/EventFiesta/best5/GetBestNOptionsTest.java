package group11.EventFiesta.best5;

import group11.EventFiesta.best5.GetBestNOptions;
import group11.EventFiesta.best5.GroupComponent;
import group11.EventFiesta.best5.OrganizerGroup;
import group11.EventFiesta.best5.OrganizerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class GetBestNOptionsTest {

    private Map<String, List<GroupComponent>> getOrganizerServicesMock() {

        Map<String, List<GroupComponent>> map = new HashMap();

        List<GroupComponent> services = new ArrayList<>();
        services.add(new OrganizerService("catering", 5.0));
        services.add(new OrganizerService("catering", 4.0));
        map.put("catering", services);

        services = new ArrayList<>();
        services.add(new OrganizerService("decoration", 3.0));
        services.add(new OrganizerService("decoration", 5.0));
//        services.add(new OrganizerService("decoration", 7.0));
        map.put("decoration", services);

        services = new ArrayList<>();
        services.add(new OrganizerService("auditorium", 4.0));
        map.put("auditorium", services);

        return map;

    }
    @Test
    public void getBestNGroupsTest() {
        int n = 5;
        Map<String, List<GroupComponent>> serviceProvidersMap = getOrganizerServicesMock();
        System.out.println(serviceProvidersMap);

        GetBestNOptions getBestNOptions = new GetBestNOptions();
        List<GroupComponent> bestNGroups = getBestNOptions.getBestNGroups(serviceProvidersMap, 5);
        Assertions.assertEquals(bestNGroups.size(), 4);
    }
}
