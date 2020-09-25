import org.json.simple.JSONObject;

public class json2 {

    /**
     * Generando un objeto JSON
     *
     * @param args
     */

    public static void main(String[] args) {
        JSONObject myObject = new JSONObject();

        // Cadenas de texto básicas
        myObject.put("name", "Carlos");
        myObject.put("last_name", "Carlos");

        // Valores primitivos
        myObject.put("age", new Integer(21));
        myObject.put("bank_account_balance", new Double(20.2));
        myObject.put("is_developer", new Boolean(true));

        // Matrices
        double[] myList = {1.9, 2.9, 3.4, 3.5};
        myObject.put("number_list", myList);

        // Objeto dentro de objeto
        JSONObject subdata = new JSONObject();
        myObject.put("extra_data", subdata);

        // Generar cadena de texto JSON
        //System.out.print(myObject);

        String puerto = new String();
        puerto = "9090";
        String message = new String();
        message = "Hola adjfhalsdf aldfgalsfd aldkjfhald sfh";


        JSONObject test = new JSONObject();

        /*test.put("puerto", puerto);
        test.put("user", "Lucia");
        test.put("message", message);
        test.put(1245, "Hola");



        System.out.println(test);

        String puerto2, message2, user2;

        puerto2 = (String) test.get("puerto");
        message2 = (String) test.get("message");
        user2 = (String) test.get("user");*/

        if (test.containsKey(1245)){
            System.out.println(test.get(1245));
            //System.out.println("De: " + user2 + " desde: " + puerto2 + " " + message2);
        }




    }
}
