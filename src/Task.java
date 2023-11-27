import java.util.Date;
import java.util.Objects;

abstract class Task implements Comparable<Task> {
    protected Date dateTime;
    protected Priority priority;

    public Task(Date dateTime, Priority priority) {
        this.dateTime = dateTime;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        int dateComparison = this.dateTime.compareTo(other.dateTime);
        if (dateComparison != 0) {
            return dateComparison;
        } else {
            return Integer.compare(this.priority.ordinal(), other.priority.ordinal());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(dateTime, task.dateTime) &&
                priority == task.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, priority);
    }

    @Override
    public String toString() {
        return "Date: " + dateTime + ", Priority: " + priority;
    }
}