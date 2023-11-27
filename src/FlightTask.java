import java.util.Date;
import java.util.Objects;

class FlightTask extends Task {
    private String departureAirport;
    private String arrivalAirport;
    private String flightTime;

    public FlightTask(Date dateTime, Priority priority, String departureAirport, String arrivalAirport, String flightTime) {
        super(dateTime, priority);
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.flightTime = flightTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FlightTask that = (FlightTask) o;
        return Objects.equals(departureAirport, that.departureAirport) &&
                Objects.equals(arrivalAirport, that.arrivalAirport) &&
                Objects.equals(flightTime, that.flightTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departureAirport, arrivalAirport, flightTime);
    }

    @Override
    public String toString() {
        return super.toString() + ", Departure Airport: " + departureAirport +
                ", Arrival Airport: " + arrivalAirport + ", Flight Time: " + flightTime;
    }
}
