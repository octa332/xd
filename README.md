## Overview

This project is a Java Swing-based GUI application for managing a simple house inventory. The application allows users to add, remove, view, and save inventory items. Each item consists of a category, name, and quantity. Data persistence is provided through reading and writing to files, and the UI features custom background imagery.

---

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Main Components](#main-components)
  - [Inventory Class](#inventory-class)
  - [InventoryItem Inner Class](#inventoryitem-inner-class)
  - [BackgroundPanel Inner Class](#backgroundpanel-inner-class)
- [User Interface](#user-interface)
- [Inventory Operations](#inventory-operations)
- [File Operations](#file-operations)
- [Code Walkthrough](#code-walkthrough)
- [Extensibility Suggestions](#extensibility-suggestions)

---

## Features

- Add new inventory items (category, name, quantity).
- Remove items by name.
- View the current inventory in the UI.
- Save the inventory to a file ("INVENTAR SALVAT").
- Load inventory from a file ("INVENTAR").
- GUI with custom background image.
- Error handling for invalid input and file I/O.

---

## Getting Started

1. **Build and Run:**  
   - Open the project in your preferred Java IDE.
   - Compile and run `src/Main.java`.
2. **Main GUI:**  
   - The main window titled "House Inventory System" will appear.
   - Use the buttons to manage your inventory.

---

## Main Components

### Inventory Class

Located in `src/Inventory.java`, this class extends `JFrame` and implements `ActionListener`. It contains all the UI logic, inventory management, and file I/O.

#### Main Attributes

- `JButton add_item, remove_item, refresh, load_inventory, save_inventory`: Main control buttons.
- `ArrayList<InventoryItem> inventory`: In-memory inventory list.
- `BackgroundPanel background`: Custom panel for background image.

#### Constructor

- Sets up the main window, creates and arranges panels and buttons, sets the background, and wires up all button events.

### InventoryItem Inner Class

Represents an inventory entry.

- **Fields:**  
  - `String category`  
  - `String name`  
  - `int quantity`
- **Constructor:** Initializes all fields.
- **toString():** Formats as `"category, name, quantity"` for display and file writing.

### BackgroundPanel Inner Class

Handles the custom background image in the main window.

- Loads an image from a hardcoded path and sets it as the panel background.

---

## User Interface

- **Left Panel:**  
  - `ADD`: Add an item (prompts for category, name, quantity).
  - `REMOVE`: Remove an item by name.
- **Right Panel:**  
  - `Refresh`: Display the current inventory.
  - `Show Inventory`: Load inventory from file.
- **Bottom Panel:**  
  - `Save Inventory`: Save the current inventory to a file.

The main content pane uses a custom background image.

---

## Inventory Operations

### Adding Items

- Prompts user for category, name, and quantity via dialogs.
- Validates input (non-empty, quantity is a number).
- Adds a new `InventoryItem` to the list.

### Removing Items

- Prompts for the name of the item to remove.
- Removes the first matching item by name (case-insensitive).
- Notifies user if the item was not found.

### Viewing Inventory

- `Refresh` button displays the current items in the inventory list.

### Saving Inventory

- Writes the inventory to `"INVENTAR SALVAT"`, one item per line.

### Loading Inventory

- Reads from `"INVENTAR"`, parses items, populates the inventory list.

---

## File Operations

- **save_inventory:**  
  - Writes each inventory item using `toString()` to `"INVENTAR SALVAT"`.
  - Handles I/O errors and notifies the user.
- **load_inventory:**  
  - Reads from `"INVENTAR"`, parses lines into items, and populates the inventory list.
  - Handles missing files and I/O errors.

---

## Code Walkthrough

### Entry Point

```java
// src/Main.java
public class Main
{
    public static void main(String[] args)
    {
        Inventory i = new Inventory();
    }
}
```
Running `Main` launches the `Inventory` window.

### Inventory GUI Construction

- Uses `JFrame`, `JPanel`, and `JButton` for layout.
- Background image set via a custom `BackgroundPanel`.
- All button actions are handled by the `actionPerformed` method.

### Example: Adding an Item

```java
if(e.getSource() == add_item)
{
    String category = JOptionPane.showInputDialog(this, "Enter the object category: ");
    // ... (input validation)
    String name = JOptionPane.showInputDialog(this, "Enter the object name: ");
    // ... (input validation)
    String quantity = JOptionPane.showInputDialog(this, "Enter the object quantity: ");
    // ... (input validation and parsing)
    inventory.add(new InventoryItem(category, name, q));
    JOptionPane.showMessageDialog(this, "The item has been added to the inventory!");
}
```

### Example: Saving Inventory

```java
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
        JOptionPane.showMessageDialog(this, "Error saving the file: " + err.getMessage());
    }
}
```

---

## Extensibility Suggestions

- Allow editing of existing items.
- Use a table or list UI component for displaying inventory.
- Refactor hardcoded paths for background images.
- Support saving/loading from user-specified files.
- Add categories or sorting/filtering features.
- Persist data in a more robust format (e.g., JSON, XML).
- Internationalize UI strings.
- Improve error handling for malformed file entries.

---

## Authors and License

- Author: [octa332](https://github.com/octa332)
- License: Not specified.

---

## See Also

- [README.md](https://github.com/octa332/xd/blob/1b0fc65ee86848c30bbad65cb063871316c50434/README.md)
- `src/Inventory.java`
- `src/Main.java`

---
