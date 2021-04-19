
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;






/**
 * This class demonstrate the application of ObjectInputStream and 
 * ObjectOutputStream at the server-side application.
 * 
 * How to run this program?
 * 
 * 1. Open Terminal
 * 2. Change the directory to /workspace-dad/demoObjectStream/bin
 * 3. Run -> java console.server.ServerSideApp
 * 
 * @author emalianakasmuri
 *
 */

public class ServerSideApp {

	/**
	 * Main entry point of application
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			// Port to receive and respond to request
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			System.out.println("Ready for request");
			
			// Server need to be alive forever thus the while(true)
			while (true) {
				
				// Accept client request for connection
				Socket socket = serverSocket.accept();
				
				// Create input stream to read object
				ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
				
				// Read object from stream and cast to itemProduct
				
				   Random rand = new Random();
			      
			      List<ItemProduct> itemProduct =(List<ItemProduct>) objectIS.readObject();
			      
          
               for(ItemProduct d: itemProduct)
				   
               {
            	   if (d.getName().contains(" ") ) {
            		   
            		   d.setName(d.getName()+"--This is an Alphanumeric String with  White Spaces ");
            		   d.setItemProductID(rand.nextInt(1000));
            	   }
            	   
            	   
            	   
            	   
            	   
            	   
            	   else if (d.getName().matches( "^[a-zA-Z]*$") ) {
            		   
            		   d.setName(d.getName()+"--This is Non hanumeric String without string White Spaces ");
            		   d.setItemProductID(rand.nextInt(1000));
            	   }
            	   
            	   
            	   
                 else if(d.getName().matches( "[*0-9]")) {
            		   
            		   d.setName(d.getName()+"--This is Non-Alphanumeric String with string White Spaces ");
            		   d.setItemProductID(rand.nextInt(1000));
            	   }
            	   
                 else {
                	 
                	 
                	 d.setName(d.getName()+"--This is Alphanumeric String without string White Spaces ");
          		   d.setItemProductID(rand.nextInt(1000));
                 }
            	   
            	   
            	  
         
            	  
               }			   
				
				// Process object
			
				
				// Create output stream to send object
				ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
				objectOS.writeObject(itemProduct);
				
				
			
				System.out.println("Ready for next request");
				
				// Close all streams
				objectIS.close();
				objectOS.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
