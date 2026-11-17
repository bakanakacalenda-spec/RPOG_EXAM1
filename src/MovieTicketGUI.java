import javax.swing.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class MovieTicketGUI extends JFrame {

    private JComboBox<String> cmbMovie;
    private JTextField txtTickets, txtPrice;
    private JTextArea txtReport;

    private MovieTickets mt = new MovieTickets();

    public MovieTicketGUI() {
        setTitle("MOVIE TICKETS");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        JLabel lblMovie = new JLabel("MOVIE:");
        lblMovie.setBounds(20, 30, 120, 25);
        add(lblMovie);

        String[] movies = {"Napoleon", "Oppenheimer", "Damsel"};
        cmbMovie = new JComboBox<>(movies);
        cmbMovie.setBounds(150, 30, 200, 25);
        add(cmbMovie);

        JLabel lblTickets = new JLabel("NUMBER OF TICKETS:");
        lblTickets.setBounds(20, 80, 130, 25);
        add(lblTickets);

        txtTickets = new JTextField();
        txtTickets.setBounds(150, 80, 200, 25);
        add(txtTickets);

        JLabel lblPrice = new JLabel("TICKET PRICE:");
        lblPrice.setBounds(20, 130, 120, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(150, 130, 200, 25);
        add(txtPrice);

        JLabel lblReport = new JLabel("TICKET REPORT:");
        lblReport.setBounds(20, 180, 120, 25);
        add(lblReport);

        txtReport = new JTextArea();
        txtReport.setEditable(false); // Read-only requirement
        JScrollPane scrollPane = new JScrollPane(txtReport);
        scrollPane.setBounds(20, 210, 330, 200);
        add(scrollPane);
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        menuFile.add(itemExit);

        JMenu menuTools = new JMenu("Tools");
        JMenuItem itemProcess = new JMenuItem("Process");
        JMenuItem itemClear = new JMenuItem("Clear");
        menuTools.add(itemProcess);
        menuTools.add(itemClear);

        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);
        itemExit.addActionListener(e -> System.exit(0));

        itemProcess.addActionListener(e -> {
            try {
                String movieName = (String) cmbMovie.getSelectedItem();
                int numTickets = Integer.parseInt(txtTickets.getText().trim());
                double price = Double.parseDouble(txtPrice.getText().trim());
                MovieTicketData data = new MovieTicketData(movieName,price, numTickets);

                if (!mt.ValidatedData(data)) {
                    JOptionPane.showMessageDialog(this, "Invalid data entered. Check your values.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double totalPrice = mt.CalculateTotalTicketPrice(numTickets, price);

                String report = "MOVIE NAME: " + movieName + "\n" +
                        "MOVIE TICKET PRICE: R " + price + "\n" +
                        "NUMBER OF TICKETS: " + numTickets + "\n" +
                        "TOTAL TICKET PRICE: R " + totalPrice;
                txtReport.setText(report);
                try (PrintWriter writer = new PrintWriter(new FileWriter("report.txt"))) {
                    writer.println("MOVIE TICKET REPORT");
                    writer.println("******************************************");
                    writer.println(report);
                    writer.println("******************************************");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for tickets and price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        itemClear.addActionListener(e -> {
            cmbMovie.setSelectedIndex(0);
            txtTickets.setText("");
            txtPrice.setText("");
            txtReport.setText("");
        });
    }

    public static void main(String[] args) {
        new MovieTicketGUI().setVisible(true);
    }
}