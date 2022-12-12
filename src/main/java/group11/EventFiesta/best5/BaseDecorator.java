package group11.EventFiesta.best5;

public abstract class BaseDecorator extends OrganizerService {
    public GroupComponent organizerService;

    public BaseDecorator(GroupComponent organizerService) {
        super();
        this.organizerService = organizerService;
    }

    @Override
    public Double calculateScore() {
        return organizerService.calculateScore();
    }

    @Override
    public void add(GroupComponent child) {
        organizerService.add(child);
    }

}
