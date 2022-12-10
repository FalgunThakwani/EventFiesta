package group11.EventFiesta.best5;

public abstract class GroupComponent implements Comparable<GroupComponent> {

    public abstract Double calculateScore();

    @Override
    public int compareTo(GroupComponent groupComponent) {
        return this.calculateScore() > groupComponent.calculateScore() ? 1 : (this.calculateScore() < groupComponent.calculateScore() ? -1 : 0);
    }

    public abstract void add(GroupComponent child);
	public abstract void remove(GroupComponent child);
}
