package group11.EventFiesta.best5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GetBestNOptionsTest {
    @Test
    public void getBestNGroupsMoreThanFiveGroupsTest() {
        int n = 5;
        GroupComponentMock mock = new GroupComponentMock();
        Map<String, List<GroupComponent>> serviceProvidersMap = mock.getOrganizerServicesMoreThanFiveGroupsMock();
        System.out.println(serviceProvidersMap);
        GetBestNOptions getBestNOptions = new GetBestNOptions();
        List<GroupComponent> bestNGroups = getBestNOptions.getBestNGroups(serviceProvidersMap, n);
        System.out.println(bestNGroups);
        Double[] bestScores = new Double[]{6.5, 5.5, 5.5, 5.0, 5.0};
        for (int i = 0; i < n; i++) {
            Assertions.assertEquals(bestScores[i], bestNGroups.get(i).calculateScore());
        }
        Assertions.assertEquals(bestNGroups.size(), n);
    }
    @Test
    public void getBestNGroupsLessThanFiveGroupsTest() {
        int n = 5;
        GroupComponentMock mock = new GroupComponentMock();
        Map<String, List<GroupComponent>> serviceProvidersMap = mock.getOrganizerServicesLessThanFiveGroupsMock();
        System.out.println(serviceProvidersMap);
        GetBestNOptions getBestNOptions = new GetBestNOptions();
        List<GroupComponent> bestNGroups = getBestNOptions.getBestNGroups(serviceProvidersMap, n);
        System.out.println(bestNGroups);
        Double[] bestScores = new Double[]{6.5, 5.0, 4.0, 2.5};
        for (int i = 0; i < bestScores.length; i++) {
            Assertions.assertEquals(bestScores[i], bestNGroups.get(i).calculateScore());
        }
        Assertions.assertEquals(bestNGroups.size(), bestScores.length);
    }
}
