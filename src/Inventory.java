import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class Inventory extends JFrame implements ActionListener
{
    JButton add_item, remove_item, refresh, load_inventory, save_inventory;
    ArrayList<InventoryItem> inventory = new ArrayList<>();
    BackgroundPanel background;

    static class InventoryItem
    {
        String category;
        String name;
        int quantity;

        InventoryItem(String category, String name, int quantity)
        {
            this.category = category;
            this.name = name;
            this.quantity = quantity;
        }

        @Override
        public String toString()
        {
            return category + ", " + name + ", " + quantity;
        }
    }

    public Inventory()
    {
        setTitle("House Inventory System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        background = new BackgroundPanel("C:/Users/44 Octane 44/OneDrive/Desktop/shituri/scoala online/java/images/chel.png");
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel left_Panel = new JPanel(new GridLayout(1, 2, 5, 5));
        left_Panel.setOpaque(false);
        add_item = new JButton("ADD");
        remove_item = new JButton("REMOVE");
        left_Panel.add(add_item);
        left_Panel.add(remove_item);

        JPanel right_panel = new JPanel(new GridLayout(1, 2, 5, 5));
        right_panel.setOpaque(false);
        refresh = new JButton("Refresh");
        load_inventory = new JButton("Show Inventory");
        right_panel.add(refresh);
        right_panel.add(load_inventory);

        JPanel bottom_panel = new JPanel();
        bottom_panel.setOpaque(false);
        save_inventory = new JButton("Save Inventory");
        bottom_panel.add(save_inventory);

        background.add(left_Panel, BorderLayout.WEST);
        background.add(right_panel, BorderLayout.EAST);
        background.add(bottom_panel, BorderLayout.SOUTH);

        add_item.addActionListener(this);
        remove_item.addActionListener(this);
        refresh.addActionListener(this);
        load_inventory.addActionListener(this);
        save_inventory.addActionListener(this);

        setVisible(true);
    }

    class BackgroundPanel extends JPanel
    {
        private Image BackgroundImage;

        public BackgroundPanel(String image_Path)
        {
            BackgroundImage = new ImageIcon("C:\\Users\\44 Octane 44\\OneDrive\\Desktop\\shituri\\scoala online\\java\\images\\chel.png").getImage();
        }

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == add_item)
        {
            String category = JOptionPane.showInputDialog(this, "Enter the object category: ");
            if(category == null || category.trim().isEmpty())
            {
                return;
            }

            String name = JOptionPane.showInputDialog(this, "Enter the object name: ");
            if(name == null || name.trim().isEmpty())
            {
                return;
            }

            String quantity = JOptionPane.showInputDialog(this, "Enter the object quantity: ");
            {
                if(quantity == null || quantity.trim().isEmpty())
                {
                    return;
                }
            }

            int q;
            try
            {
                q = Integer.parseInt(quantity);
            } catch (NumberFormatException err)
            {
                JOptionPane.showMessageDialog(this, "Invalid input! Enter a number for the quantity");
                return;
            }

            inventory.add(new InventoryItem(category, name, q));
            JOptionPane.showMessageDialog(this, "The item has been added to the inventory!");
        }

        if(e.getSource() == remove_item)
        {
            String search_item = JOptionPane.showInputDialog(this, "Enter the object to be removed: ");
            if(search_item == null || search_item.trim().isEmpty())
            {
                return;
            }

            Iterator<InventoryItem> iterator = inventory.iterator();
            boolean found = false;
            while (iterator.hasNext())
            {
                InventoryItem item = iterator.next();
                if (item.name.equalsIgnoreCase(search_item))
                {
                    iterator.remove();
                    found = true;
                    break;
                }
            }

            if(found)
            {
                JOptionPane.showMessageDialog(this, "The object has been removed");
            } else
            {
                JOptionPane.showMessageDialog(this, "The object has not been found");
            }
        }

        if(e.getSource() == refresh)
        {
            StringBuilder sb = new StringBuilder();
            for(InventoryItem item : inventory)
            {
                sb.append(item.toString()).append("\n");
            }
        }

        if(e.getSource() == load_inventory)
        {
            File file = new File("INVENTAR");
            if(!file.exists())
            {
                return;
            }
            try(BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                inventory.clear();
                while((line = br.readLine()) != null)
                {
                    line = line.trim();
                    if(line.isEmpty())
                    {
                        continue;
                    }
                    if(line.endsWith("."))
                    {
                        line = line.substring(0, line.length() - 1);
                    }
                    String [] parts = line.split(", ");
                    if(parts.length >= 3);
                    {
                        String category = parts[0].trim();
                        String name = parts[1].trim();
                        int quantity = Integer.parseInt(parts[2].trim());
                        inventory.add(new InventoryItem(category, name, quantity));
                    }
                }
            } catch(IOException err)
            {
                JOptionPane.showMessageDialog(this, "Error reading the file: " + err.getMessage() + " Error");
            }
        }

        if(e.getSource() == save_inventory)
        {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("INVENTAR SALVAT")))
            {
                for(InventoryItem item : inventory)
                {
                    bw.write(item.toString());
                    bw.newLine();
                }
            } catch(IOException err)
            {
                JOptionPane.showMessageDialog(this, "Error saving the file: " + err.getMessage() + " Error");
            }
        }
    }



}
