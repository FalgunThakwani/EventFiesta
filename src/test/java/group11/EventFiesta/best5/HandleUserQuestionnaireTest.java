package group11.EventFiesta.best5;

import group11.EventFiesta.model.UserEventQuestionnaire;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class HandleUserQuestionnaireTest {
     UserEventQuestionnaire questionnaire = new UserEventQuestionnaire();


     @Test
     public void getMapValuePairOfService(){
         questionnaire.setBudget(1500);
         questionnaire.setCity("Halifax");
         questionnaire.setGuestCount(50);
         questionnaire.setService("Catering,Decoration");
         HandleUserQuestionnaire handleUserQuestionnaire = new HandleUserQuestionnaire(questionnaire);
         Map<String, List<GroupComponent>> score =handleUserQuestionnaire.getMapValuePairOfService();
         Assertions.assertTrue(score.size()>0);
     }

}
