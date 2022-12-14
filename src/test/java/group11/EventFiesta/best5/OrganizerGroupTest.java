package group11.EventFiesta.best5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrganizerGroupTest {

    @Test
    public void calculateScoreSuccessTest() {
        OrganizerGroup organizerGroup = new OrganizerGroup();
        Double[] scores = new Double[]{5.0, 4.0, 7.0, 6.0, 2.0};
        Double sum = 0.0;
        for (Double score : scores) {
            sum += score;
            OrganizerService mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            organizerGroup.add(mockOrganizerService);
        }
        Double expectedScore = sum / scores.length;
        Assertions.assertEquals(expectedScore, organizerGroup.calculateScore());
    }

    @Test
    public void calculateScoreFailureTest() {
        OrganizerGroup organizerGroup = new OrganizerGroup();
        Double[] scores = new Double[]{5.0, 4.0, 7.0, 6.0, 2.0};
        Double sum = 0.0;
        for (Double score : scores) {
            sum += score;
            OrganizerService mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            organizerGroup.add(mockOrganizerService);
        }
        Double incorrectScore = sum / scores.length + 1.0;
        Assertions.assertNotEquals(incorrectScore, organizerGroup.calculateScore());
    }

    @Test
    public void addSuccessTest() {
        OrganizerGroup organizerGroup = new OrganizerGroup();
        int countOfService = 5;
        for (int i = 0; i < countOfService; i++) {
            OrganizerService mockOrganizerService = Mockito.mock(OrganizerService.class);
            organizerGroup.add(mockOrganizerService);
        }
        Assertions.assertEquals(countOfService, organizerGroup.getOrganizerServices().size());
    }

    @Test
    public void removeSuccessTest() {
        OrganizerGroup organizerGroup = new OrganizerGroup();
        int countOfService = 2;
        OrganizerService mockOrganizerService = Mockito.mock(OrganizerService.class);
        for (int i = 0; i < countOfService; i++) {
            mockOrganizerService = Mockito.mock(OrganizerService.class);
            organizerGroup.add(mockOrganizerService);
        }
        countOfService--;
        organizerGroup.remove(mockOrganizerService);
        Assertions.assertEquals(countOfService, organizerGroup.getOrganizerServices().size());
    }

    @Test
    public void getScoreTest() {
        OrganizerGroup organizerGroup = new OrganizerGroup();
        Double[] scores = new Double[]{5.0, 4.0};
        for (Double score : scores) {
            OrganizerService mockOrganizerService = Mockito.mock(OrganizerService.class);
            when(mockOrganizerService.calculateScore()).thenReturn(score);
            organizerGroup.add(mockOrganizerService);
        }
        Double score = organizerGroup.calculateScore();
        Assertions.assertEquals(Double.parseDouble(String.format("%.2f", score)), organizerGroup.getScore());
    }
}
