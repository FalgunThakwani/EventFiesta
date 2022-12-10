package group11.EventFiesta.best5;

public abstract class BaseDecorator extends OrganizerService {
    public GroupComponent organizerService;

    public BaseDecorator(GroupComponent organizerService) {
        super();
        this.organizerService = organizerService;
    }

    @Override
    public Integer calculateScore() {
        return organizerService.calculateScore();
    }

    @Override
    public void Add(GroupComponent child) {
        organizerService.Add(child);
    }

}
