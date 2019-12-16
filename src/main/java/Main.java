public class Main
{

     public static void main(String[] args) throws Exception {


        Catalog catalog = new Catalog();
        catalog.getCatalog();




        //creating test user
         /*
         User user = new User("Ethan Li");
        user.addToOrder(catalog.getItems().get(1), 32);
        user.confirmOrder(catalog, "1/1/1");
        */

        System.out.println("Sorted for Redstone" + catalog.sorted("Redstone"));

        System.out.println("User: ");
        //user.toString();
        System.out.println();
        System.out.println();
        System.out.println("Catalog: ");
        catalog.toString();

    }

     //gui main
    /*public static void main(String[] args) {
        Name name = new Name();

    }*/

}
