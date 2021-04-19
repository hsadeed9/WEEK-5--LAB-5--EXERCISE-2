

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class ClientSideApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		System.out.println("ClientSideApp: Demo to process a list of objects on TCP \n");

		
		ItemProduct obj1 = new ItemProduct();
	    obj1.setName("Product 1");
	    obj1.setPrice(20);
		
	
	    
	    ItemProduct obj2 = new ItemProduct();
	    obj2.setName("Product2");
	    obj2.setPrice(30);
		
	    ItemProduct obj3 = new ItemProduct();
	    obj3.setName("Product");
	    obj3.setPrice(50);
		
		
		// Add into list
		List<ItemProduct> allObjects = new ArrayList<ItemProduct>();
		allObjects.add(obj1);
		allObjects.add(obj2);
		allObjects.add(obj3);

		try {

			// Data to establish connection to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " );
			objectOS.writeObject(allObjects);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream, cast and display details
			allObjects = (List<ItemProduct>) objectIS.readObject();
			for (ItemProduct allObjects1:allObjects)
				System.out.println ("From Server=" + allObjects1.getName() + "and it's price is " + allObjects1.getPrice()+" AND its ID generated from the server ="+allObjects1.getItemProductID());
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nClientSideApp: End of application.\n");

	}

}
