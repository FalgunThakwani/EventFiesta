package group11.EventFiesta.best5;

public abstract class BaseDecorator extends OrganizerService {

    public BaseDecorator(GroupComponent organizerService) {
        super();
        OrganizerService org = (OrganizerService) organizerService;
        init(org);
    }

    void init(OrganizerService organizerService) {
        this.budget = organizerService.budget;
        this.headCount = organizerService.headCount;
        this.orgranizerId = organizerService.orgranizerId;
        this.id = organizerService.id;
        this.serviceName = organizerService.serviceName;
    }

    @Override
    public Double calculateScore() {
        return super.calculateScore();
    }

    @Override
    public void add(GroupComponent child) {
        super.add(child);
    }

}
