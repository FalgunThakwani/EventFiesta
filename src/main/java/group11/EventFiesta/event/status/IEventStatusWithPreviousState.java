package group11.EventFiesta.event.status;

public interface IEventStatusWithPreviousState extends IEventStatus {
    IEventStatus getPreviousState();
}
