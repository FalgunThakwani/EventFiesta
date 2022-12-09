package group11.EventFiesta.best5;

public class OrganizerService implements GroupComponent {

    private String serviceName;//catering, decoration, auditorium

    private Integer score;
    @Override
    public Integer calculateScore() {
        return score;
    }
}
