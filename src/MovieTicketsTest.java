import org.junit.Test;
import static org.junit.Assert.*;

public class MovieTicketsTest {

    @Test
    public void testCalculateTotalTicketPrice_CalculatedSuccessfully() {
        MovieTickets mt = new MovieTickets();
        double expected = 342.0;
        double actual = mt.CalculateTotalTicketPrice(3, 100.0);
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testValidatedData_InvalidTickets() {
        MovieTickets mt = new MovieTickets();
        // Requires all 3 arguments: movieName, ticketPrice, numberOfTickets
        MovieTicketData invalidData = new MovieTicketData("Napoleon", 100.0, 0);
        assertFalse(mt.ValidatedData(invalidData));
    }
}