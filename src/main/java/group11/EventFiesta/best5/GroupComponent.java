package group11.EventFiesta.best5;

public interface GroupComponent {

    Integer calculateScore();
    public abstract void Add(GroupComponent child);
	public abstract void Remove(GroupComponent child);
}
