package IA;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Catalog
{

    private ArrayList<Item> items;

    public Catalog()
    {
        items = new ArrayList<>();
    }

    public Catalog(ArrayList<Item> items)
    {
        this.items = items;
    }

    public void setItems(ArrayList<Item> items)
    {
        this.items = items;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void editItem(Item target)
    {
        for(Item item : items)
        {
            if(item.getName().equals(target.getName()))
            {
                item.setAmount(item.getAmount() - target.getAmount());
            }
        }
    }

    public void getCatalog() throws Exception
    {
        try
        {
            ArrayList<String> names = IA.Database.Main.get(1);
            ArrayList<String> amounts = IA.Database.Main.get(2);
            ArrayList<String> types = IA.Database.Main.get(3);

            for (int i = 0; i < names.size(); i++)
            {
                Item item = new Item(names.get(i), Integer.parseInt(amounts.get(i)), types.get(i));
                addItem(item);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public String toString()
    {
        for (Item item : items)
        {
            System.out.println(item.getName() + ", amount:  " + item.getAmount() + ", type: " + item.getType());
        }
        return "test";
    }

}
