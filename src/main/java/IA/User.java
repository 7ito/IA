package IA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User
{

    private String name;
    private ArrayList<Item> order;
    private Date date;

    public User(String name)
    {
        this.name = name;
        order = new ArrayList<>();
        date = Calendar.getInstance().getTime();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOrder(ArrayList<Item> order)
    {
        this.order = order;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Item> getOrder()
    {
        return order;
    }

    public void clearOrder()
    {
        order.clear();
    }

    public String getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        return dateFormat.format(date);
    }

    public void addToOrder(Item item, int amount)
    {
        boolean check = false;
        int index = 0;
        for (Item piece : order)
        {
            if(item.getName().equals(piece.getName()))
            {
                check = true;
                index = order.indexOf(piece);
            }
        }
        if (check)
        {
            order.get(index).setAmount(order.get(index).getAmount() + amount);
        }
        else
        {
            Item add = new Item(item.getName(), amount, item.getType());
            order.add(add);
        }
    }

    public void confirmOrder(Catalog catalog) throws Exception
    {

        for(Item item : order)
        {
            IA.Database.Main.update(item.getName(), IA.Database.Main.getAmount(item.getName()) - item.getAmount());
            catalog.editItem(item);
            String[] order = {getName(), getDate(), item.getName(), "" + item.getAmount(), "11/11/2019"};
            Communicator.uploadOrder(order);

        }
    }


    @Override
    public String toString()
    {
        for(Item item : order)
        {
            System.out.println(item.getName() + " , amount:  " + item.getAmount());
        }
        return "test";
    }

}
