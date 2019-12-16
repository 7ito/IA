import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public void addToOrder(Item item, int amount)
    {
        Item add = item;
        add.setAmount(amount);
        order.add(add);
    }

    public void confirmOrder(Catalog catalog, String returnDate) throws Exception
    {
        for(Item item : order)
        {
            Database.Main.update(item.getName(), Database.Main.getAmount(item.getName()) - item.getAmount());
            catalog.editItem(item);
            String[] order = {getName(), getDate(), item.getName(), "" + item.getAmount(), returnDate};
            Communicator.uploadOrder(order);

        }
    }

    public String toString()
    {
        for(Item item : order)
        {
            System.out.println(item.getName() + " , amount:  " + item.getAmount());
        }
        return "test";
    }

}
