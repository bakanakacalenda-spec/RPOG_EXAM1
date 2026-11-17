public class MovieTickets implements IMovieTickets {
    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double subtotal = numberOfTickets * ticketPrice;
        double vatAmount = subtotal * 0.14;
        return subtotal + vatAmount;
    }

    @Override
    public boolean ValidatedData(MovieTicketData movieTicketData) {
        if (movieTicketData.getMovieName() == null || movieTicketData.getMovieName().isEmpty()) {
            return false;
        }
        if (movieTicketData.getTicketPrice() <= 0) {
            return false;
        }
        if (movieTicketData.getNumberOfTickets() <= 0) {
            return false;
        }

        return true;
    }
}


