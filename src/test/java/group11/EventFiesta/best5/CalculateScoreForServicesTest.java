package group11.EventFiesta.best5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculateScoreForServicesTest {

    @Test
    public void calculateScoreForService() {
        Double rs = 4.5;
        Double es = 7.5;
        Double bs = 6.5;
        Double totalScore = (rs + es + bs) / 3;
        totalScore = totalScore < 4.0 ? 4.0 : totalScore;
        if (totalScore > 4.0 && totalScore > 10) {
            totalScore = totalScore - (totalScore / 4);
        }
        Assertions.assertEquals(totalScore, 6.166666666666667);
    }
}
