package group11.EventFiesta.event.status;

public interface IEventStatusWithPreviousStatus extends IEventStatus {
    IEventStatus getPreviousState();
}
