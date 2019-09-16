package IA;

public class Main
{

    public static void main(String[] args) throws Exception {

        //creating test catalog
        /*ArrayList<Item> items = new ArrayList<>();
        Item beaker = new Item("Beaker", 10, "Science");
        Item book = new Item("Book", 15, "English");
        Item headphones = new Item("Headphones", 5, "Misc");
        items.add(beaker);
        items.add(book);
        items.add(headphones);
        */

        Catalog catalog = new Catalog();
        catalog.getCatalog();


        //creating test user
        User user = new User("Ethan Li");
        user.addToOrder(catalog.getItems().get(1), 32);
        user.confirmOrder(catalog);


        System.out.println("User: ");
        //user.toString();
        System.out.println();
        System.out.println();
        System.out.println("Catalog: ");
        catalog.toString();

    }

}
