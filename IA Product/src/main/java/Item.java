public class Item
{

    private String name;
    private int amount;
    private String type;

    public Item()
    {
        name = "";
        amount = 0;
        type = "";
    }

    public Item(String name)
    {
        this.name = name;
        amount = 0;
        type = "";
    }

    public Item(String name, int amount)
    {
        this.name = name;
        this.amount = amount;
        type = "";
    }
    public Item(String name, int amount, String type)
    {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getType()
    {
        return type;
    }

}
