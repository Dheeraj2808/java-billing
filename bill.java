import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class bill extends JFrame {

    private JTextField nameField, qtyField, priceField, discountField;
    private JLabel subTotalLabel, gstLabel, discountLabel, grandTotalLabel, statusLabel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JCheckBox gstCheckBox;

    public bill() {
        setTitle("Billing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== Input Panel =====
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Item"));

        inputPanel.add(new JLabel("Item Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        inputPanel.add(qtyField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Discount (%):"));
        discountField = new JTextField();
        inputPanel.add(discountField);

        JButton addButton = new JButton("Add Item");
        inputPanel.add(addButton);

        JButton clearButton = new JButton("Clear Fields");
        inputPanel.add(clearButton);

        // ===== Table =====
        String[] columnNames = {"Item", "Quantity", "Price", "Discount %", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // ===== Summary Panel =====
        JPanel summaryPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));

        subTotalLabel = new JLabel("Sub-Total: ₹0.00");
        gstLabel = new JLabel("GST (18%): ₹0.00");
        discountLabel = new JLabel("Discount: ₹0.00");
        grandTotalLabel = new JLabel("Grand Total: ₹0.00");
        gstCheckBox = new JCheckBox("Apply GST (18%)");

        summaryPanel.add(subTotalLabel);
        summaryPanel.add(gstLabel);
        summaryPanel.add(discountLabel);
        summaryPanel.add(grandTotalLabel);
        summaryPanel.add(gstCheckBox);

        // ===== Status Bar =====
        statusLabel = new JLabel("Welcome to Billing System");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());

        // ===== Layout =====
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.EAST);
        add(statusLabel, BorderLayout.SOUTH);

        // ===== Actions =====
        addButton.addActionListener(e -> {
            try {
                String itemName = nameField.getText().trim();
                int quantity = Integer.parseInt(qtyField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());
                double discountPercent = 0;

                if (!discountField.getText().trim().isEmpty()) {
                    discountPercent = Double.parseDouble(discountField.getText().trim());
                }

                double total = quantity * price;
                double discountAmount = total * discountPercent / 100;
                total -= discountAmount;

                tableModel.addRow(new Object[]{itemName, quantity, price, discountPercent, total});

                // ✅ Refresh totals
                updateTotal();

                statusLabel.setText("Item added successfully");
            } catch (Exception ex) {
                statusLabel.setText("Invalid input! Please check fields.");
            }
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            qtyField.setText("");
            priceField.setText("");
            discountField.setText("");
        });

        gstCheckBox.addActionListener(e -> updateTotal());
    }

    // ===== Total Calculation =====
    private void updateTotal() {
        double grossTotal = 0;   // without discount
        double subTotal = 0;     // with discount applied
        double totalDiscount = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int qty = (int) tableModel.getValueAt(i, 1);
            double price = (double) tableModel.getValueAt(i, 2);
            double discountPercent = (double) tableModel.getValueAt(i, 3);
            double total = (double) tableModel.getValueAt(i, 4);

            grossTotal += qty * price;
            subTotal += total;
            totalDiscount += (qty * price) - total;
        }

        double gst = gstCheckBox.isSelected() ? subTotal * 0.18 : 0;
        double grandTotal = subTotal + gst;

        subTotalLabel.setText(String.format("Sub-Total: ₹%.2f", grossTotal));
        gstLabel.setText(String.format("GST (18%%): ₹%.2f", gst));
        discountLabel.setText(String.format("Discount: ₹%.2f", totalDiscount));
        grandTotalLabel.setText(String.format("Grand Total: ₹%.2f", grandTotal));
    }

    // ===== Main =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new bill().setVisible(true));
    }
}
