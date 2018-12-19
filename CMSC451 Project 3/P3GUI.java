import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Filename:    P3GUI
 * Author:      Shafro Batyrov
 * Date:        7/01/2018
 * Description: GUI that takes input list of integers or fractions and builds a Binary Search Tree 
 * that outputs in either ascending or descending order.
 */
public class P3GUI extends JFrame {

    // Declaring variables
    private String list;
    private String result;

    //Constructing the GUI
    private P3GUI() {
        // Setting GUI Title
        super ("Binary Search Tree Sort");

        // Creating JPanels
        JPanel main = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JPanel performPanel = new JPanel();
        JPanel optionsPanel = new JPanel();
        JPanel sortPanel = new JPanel();
        JPanel numericPanel = new JPanel();

        // Setting Layout
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        performPanel.setLayout(new GridBagLayout());
        optionsPanel.setLayout(new GridLayout());
        sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.Y_AXIS));
        numericPanel.setLayout(new BoxLayout(numericPanel, BoxLayout.Y_AXIS));

        // Creating Components
        JLabel inputLabel = new JLabel("Original List");
        JTextField inputTxt = new JTextField(null,20);
        JLabel outputLabel = new JLabel("Sorted List");
        JTextField outputTxt = new JTextField(null,20);
        JButton performButton = new JButton("Perform Sort");
        JRadioButton ascendingButton = new JRadioButton("Ascending");
        JRadioButton descendingButton = new JRadioButton("Descending");
        JRadioButton intButton = new JRadioButton("Integer");
        JRadioButton fractionButton= new JRadioButton("Fraction");

        // Adding Components to GUI
        inputPanel.add(inputLabel);
        inputPanel.add(inputTxt);
        inputPanel.setPreferredSize(new Dimension(500,65));

        outputPanel.add(outputLabel);
        outputPanel.add(outputTxt);
        outputTxt.setEditable(false);

        performPanel.add(performButton);

        sortPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
        sortPanel.add(ascendingButton);
        sortPanel.add(descendingButton);

        // Assigning Group Sort Buttons
        ButtonGroup sortGroup = new ButtonGroup();
        sortGroup.add(ascendingButton);
        sortGroup.add(descendingButton);

        // Add Numeric Components
        numericPanel.setBorder(BorderFactory.createTitledBorder("Numeric Type"));
        numericPanel.add(intButton);
        numericPanel.add(fractionButton);

        // Add Group Numeric Buttons
        ButtonGroup numericGroup = new ButtonGroup();
        numericGroup.add(intButton);
        numericGroup.add(fractionButton);

        // Add Options Components
        optionsPanel.add(sortPanel);
        optionsPanel.add(numericPanel);

        // Add Panels to main
        main.add(inputPanel);
        main.add(outputPanel);
        main.add(performPanel);
        main.add(optionsPanel);

        // Add main to JFrame
        add(main);

        // JFrame Parameters
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(425,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Set Default Radio Buttons
        ascendingButton.setSelected(true);
        intButton.setSelected(true);
        ascendingButton.doClick();
        intButton.doClick();

        // "Perform" Button Handler
        performButton.addActionListener((ActionEvent e) -> {
            list = inputTxt.getText();

            try {
                // Throw exception if nothing is entered
                if (list.isEmpty()) {
                    throw new NullPointerException();
                }

                // Tokenize Input String
                String[] tokens = list.split(" ");

                // Integer BST
                if (intButton.isSelected()) {

                    // Creates Integer BST instance
                    BST<Integer> bst = new BST<>();

                    // Tokenize and add to BST
                    for (String token : tokens) {
                        bst.insertNode(Integer.parseInt(token));
                    }

                    // Outputs integer BST in Ascending/Descending order, depending on which button is clicked
                    if (ascendingButton.isSelected()) {
                        result = bst.getAscending();
                    } else if (descendingButton.isSelected()) {
                        result = bst.getDescending();
                    }
                }

                // Fraction BST
                if (fractionButton.isSelected()) {

                    // Creates String BST instance
                    BST<Fraction> bst = new BST<>();

                    // Tokenize and add to BST
                    for (String token : tokens) {
                        Fraction fraction = new Fraction(token);
                        bst.insertNode(fraction);
                    }

                    // Outputs Fraction BST in either Ascending or Descending Order
                    if (ascendingButton.isSelected()) {
                        result = bst.getAscending();
                    } else if (descendingButton.isSelected()) {
                        result = bst.getDescending();
                    }
                }

                // Outputs result as a String
                outputTxt.setText(result);

            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "Please enter a list.","Error",JOptionPane.ERROR_MESSAGE);
            } catch (MalformedFractionException e1) {
                JOptionPane.showMessageDialog(null, "Invalid Fraction entered: " + e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Non-Numeric Input.","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new P3GUI();
    }
}