package FoodApp.src.main;
import javax.swing.*;
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

    
    public String[][] items = {{"CheeseBurger with Mushrooms", "Cheeseburger with Pimento Cheese","Crinkle Cut Fries"}, 
    {"Mighty Meaty","Kosmic Karma","Pacific Rim"},{"Sirloin","filet mignon","baked potato side"} };

    public double[][] prices = {{14.99,15.99,1.00},{17.99,17.99,17.99},{21.99, 31.99, 1.00}};

    public String[] names = {"Burger Restaurant","Mellow Mushroom","The Peddler"};

    public ArrayList<String> cart;
    public double total;

    private JTextArea cartDisplay;

    public app()
    {
        cart = new  ArrayList<>();
        cd1 = new CardLayout();
        
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
        
        menuPanel = new JPanel(new GridLayout(0, 2, 1, 4));
        createMenuPanel(menuPanel);
        frame.add(menuPanel, BorderLayout.CENTER);

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
                JLabel itemLabel = new JLabel(items[i][j] + "\n$" + prices[i][j]);
                itemLabel.setHorizontalAlignment(SwingConstants.LEFT);
                menuPanel.add(itemLabel);

                //JLabel priceLabel = new JLabel();
                //priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                //menuPanel.add(priceLabel);

                JButton addButton = new JButton("Add to Cart");
                
                int index1 = i;
                int index2 = j;

                addButton.addActionListener(e -> updateCartAdd(items[index1][index2], prices[index1][index2]));
                menuPanel.add(addButton);
            }
        }
    }

    private void createCartPanel()
    {

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
        cart.add(added + " $" + price);
        total += price;
    }

    private void updateCartSubtract()
    {

    }

}
