Java Billing System
Project Overview

The Java Billing System is a desktop application built using Java Swing. It provides a simple and interactive way to generate bills for items with features like discounts, GST calculation, and total summaries. This system is ideal for small shops and businesses to manage their billing efficiently.

Features

Add Items: Enter item name, quantity, and price to add products to the bill.

Discount Support: Apply percentage-based discounts per item.

GST Calculation: Option to include 18% GST in the total.

Summary Display: Shows Sub-Total, Discount, GST, and Grand Total.

Clear Fields: Easily reset input fields for the next entry.

Status Updates: Displays messages for successful actions or errors.

User-Friendly GUI: Intuitive interface using Java Swing.

Technologies Used

Java JDK (8 or higher)

Swing (GUI library)

DefaultTableModel (for managing items and totals)

Event Handling (for buttons and checkboxes)

Installation & Usage

Clone the repository (or download the code):

git clone https://github.com/yourusername/java-billing-system.git


Open the project in any Java IDE (Eclipse, IntelliJ IDEA, or NetBeans).

Compile and run the program:

javac bill.java
java bill


Using the application:

Enter item name, quantity, price, and discount (%).

Click Add Item to add it to the bill.

Check the Apply GST checkbox if you want GST included.

View the summary on the right panel (Sub-Total, Discount, GST, Grand Total).

Use Clear Fields to reset input fields for new entries.

Screenshot

(Optional: Add a screenshot of your running GUI here)

Future Enhancements

Save generated bills to files for record-keeping.

Print bills directly from the application.

Support for multiple GST rates.

Add database integration for persistent storage.
