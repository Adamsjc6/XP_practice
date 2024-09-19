package FoodApp.src.main;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;

/**
 * sets up the basic app
 * 
 */
public class app 
{
    private JFrame frame;
    private JPanel menuPanel;
    private JPanel cartPanel;
    private JPanel checkoutPanel;
    private CardLayout cd1;
    private JPanel cardPanel;
    
    public String[][] items = {{"CheeseBurger with Mushrooms", "Cheeseburger with Pimento Cheese","Crinkle Cut Fries"}, 
    {"Mighty Meaty","Kosmic Karma","Pacific Rim"},{"Sirloin","filet mignon","baked potato side"} };

    public double[][] prices = {{14.99,15.99,1.00},{17.99,17.99,17.99},{21.99, 31.99, 1.00}};

    public String[] names = {"Burger Restaurant","Mellow Mushroom","The Peddler"};

    public ArrayList<String> cart;
    public ArrayList<Integer> pricesAR;
    public double total;

    private JTextArea cartDisplay;

    public app()
    {
        cart = new  ArrayList<>();
        
        createFrame();
        displayFrame();
    }

    /**
     * creates the Jframe
     */
    private void createFrame()
    {
        frame = new JFrame("Food Ordering App");
        frame.setSize(600, 400);
        frame.setResizable(true);

        cd1 = new CardLayout();
        cardPanel = new JPanel(cd1);

        menuPanel = new JPanel(new GridLayout(0, 2, 1, 4));
        createMenuPanel(menuPanel);

        cartPanel = new JPanel();
        cartDisplay = new JTextArea();
        createCartPanel(cartPanel);

        checkoutPanel = new JPanel();
        createCheckoutPanel(checkoutPanel);

        cardPanel.add(menuPanel, "MenuPanel");
        cardPanel.add(cartPanel, "CartPanel");
        cardPanel.add(checkoutPanel, "Checkout");
        frame.add(cardPanel, BorderLayout.CENTER);
    }

    private void createMenuPanel(JPanel menuPanel)
    {
        for (int i = 0; i < items.length; i++)
        {
            JLabel restaurantLabel = new JLabel(names[i]);
            menuPanel.add(restaurantLabel, BorderLayout.CENTER);
            menuPanel.add(new JLabel(""));

            for (int j = 0; j < items[i].length; j++)
            {
                JLabel itemLabel = new JLabel(items[i][j] + "\n $" + prices[i][j]);
                itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
                menuPanel.add(itemLabel);

                JButton addButton = new JButton("Add to Cart");
                
                int index1 = i;
                int index2 = j;

                addButton.addActionListener(e -> updateCartAdd(items[index1][index2], prices[index1][index2]));
                menuPanel.add(addButton);
            }
        }

        JButton toCart = new JButton("goToCart");
        toCart.addActionListener(e -> cd1.show(cardPanel, "CartPanel"));
        menuPanel.add(toCart);
    }

    private void createCartPanel(JPanel cart)
    {
        cartDisplay.setEditable(false);
        cart.setLayout(new BorderLayout());

        cart.add(new JScrollPane(cartDisplay), BorderLayout.NORTH);
        
        JTextField remove = new JTextField();

        JButton backToMenu = new JButton("Back to Menu");
        backToMenu.addActionListener(e -> cd1.show(cardPanel, "MenuPanel"));

        JButton removeFromCart = new JButton("removeFromCart");
        removeFromCart.addActionListener(e -> updateCartSubtract(remove.getText()));

        JPanel buttonPanel = new JPanel(new GridLayout(1,3, 1, 1));
        buttonPanel.add(backToMenu);
        buttonPanel.add(removeFromCart);

        JLabel totalLabel = new JLabel("Total: " + Double.toString(this.total));
        cart.add(totalLabel, BorderLayout.CENTER);

        cart.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createCheckoutPanel(JPanel checkout)
    {

        JLabel total = new JLabel(Double.toString(this.total));
        checkout.add(total, BorderLayout.CENTER);
    }

    private void displayFrame()
    {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * 
     * @param added this adds the items to a string which will display
     * @param price adds 
     */
    private void updateCartAdd(String added, double price)
    {
        cart.add((cart.size() + 1) + " " + added + " $" + price);
        
        total += price;

        cartDisplay.append(cart.get(cart.size() - 1) + "\n");

    }

    private void updateCartSubtract(String jtext)
    {
        int num = Integer.parseInt(jtext);

        for (int i = 0; i < cart.size(); i++)
        {
            if(i == num)
            {
                cart.remove(i);
            }
        }
    }

}
