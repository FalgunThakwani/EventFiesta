package group11.EventFiesta.best5;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

public class GroupComponentMock {

    public Map<String, List<GroupComponent>> getOrganizerServicesMoreThanFiveGroupsMock() {

        Map<String, List<GroupComponent>> map = new HashMap();

        List<GroupComponent> services = new ArrayList<>();
        Double[] scores = new Double[]{9.0, 4.0, 7.0, 6.0, 2.0};
        for (Double score : scores) {
            GroupComponent mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            services.add(mockOrganizerService);
        }
        map.put("Catering", services);

        services = new ArrayList<>();
        scores = new Double[]{1.0, 4.0, 2.0};
        for (Double score : scores) {
            GroupComponent mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            services.add(mockOrganizerService);
        }
        map.put("Decoration", services);
        return map;

    }

    public Map<String, List<GroupComponent>> getOrganizerServicesLessThanFiveGroupsMock() {

        Map<String, List<GroupComponent>> map = new HashMap();

        List<GroupComponent> services = new ArrayList<>();
        Double[] scores = new Double[]{9.0, 4.0};
        for (Double score : scores) {
            GroupComponent mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            services.add(mockOrganizerService);
        }
        map.put("Catering", services);

        services = new ArrayList<>();
        scores = new Double[]{1.0, 4.0};
        for (Double score : scores) {
            GroupComponent mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            services.add(mockOrganizerService);
        }
        map.put("Decoration", services);
        return map;

    }

}
