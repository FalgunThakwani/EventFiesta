package group11.EventFiesta.UserMyEventFeedbackTest;

import group11.EventFiesta.UserEventChecklist.UserEventChecklistHandler;
import group11.EventFiesta.UserEventChecklistTest.MockUserEventChecklistPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.UserMyEventFeedback;
import group11.EventFiesta.model.Reviews;
import group11.EventFiesta.model.TodoChecklist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class UserMyEventFeedbackTest {

    @Test
    public void submitUserMyEventFeedbackTest() throws Exception {
        MockUserMyEventFeedback mockDb = new MockUserMyEventFeedback();
        UserMyEventFeedback userMyEventFeedback = new UserMyEventFeedback(mockDb);

        boolean result = userMyEventFeedback.submitUserMyEventFeedback(3, 1, 1, "worst");

        List<Map<String, Object>> reviewList = mockDb.loadData("");
        Assertions.assertEquals(1, reviewList.size());
    }
    
}
