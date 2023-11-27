import java.util.Date;
import java.util.Objects;

class BusinessMeetingTask extends Task {
    private String location;
    private String participants;

    public BusinessMeetingTask(Date dateTime, Priority priority, String location, String participants) {
        super(dateTime, priority);
        this.location = location;
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BusinessMeetingTask that = (BusinessMeetingTask) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location, participants);
    }

    @Override
    public String toString() {
        return super.toString() + ", Location: " + location + ", Participants: " + participants;
    }
}
