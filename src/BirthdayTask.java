import java.util.Date;
import java.util.Objects;

class BirthdayTask extends Task {
    private String celebrantName;

    public BirthdayTask(Date dateTime, Priority priority, String celebrantName) {
        super(dateTime, priority);
        this.celebrantName = celebrantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BirthdayTask that = (BirthdayTask) o;
        return Objects.equals(celebrantName, that.celebrantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), celebrantName);
    }

    @Override
    public String toString() {
        return super.toString() + ", Celebrant: " + celebrantName;
    }
}
