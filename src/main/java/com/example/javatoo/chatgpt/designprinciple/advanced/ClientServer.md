The Client-Server architecture pattern is a widely used architectural pattern in which clients and servers communicate over a network. Clients make requests to servers, and servers process those requests and provide responses back to the clients. Here's an example of implementing the Client-Server pattern in Java:

Let's start with the Server implementation:

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Listening on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String request = in.readLine();
                System.out.println("Received request from client: " + request);

                // Process the request and prepare the response
                String response = "Hello from server!";

                // Send the response back to the client
                out.println(response);

                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

In this example, we have the `Server` class that listens for client connections on port 8080. When a client connects, a new thread is spawned to handle that client's requests. The `ClientHandler` class implements the logic for handling client requests and generating responses. It reads the request from the client, processes it (in this case, it simply sends a fixed response message), and sends the response back to the client.

Now, let's implement the Client:

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            System.out.println("Connected to server: " + clientSocket.getInetAddress());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Send a request to the server
            String request = "Hello from client!";
            out.println(request);
            System.out.println("Sent request to server: " + request);

            // Receive and print the response from the server
            String response = in.readLine();
            System.out.println("Received response from server: " + response);

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

In this example, we have the `Client` class that establishes a connection to the server running on localhost at port 8080. It sends a request to the server and receives the response. In this case, it simply prints the received response.

When you run the server and client code in separate terminals or IDEs, you'll see the following output:

Server:
```
Server started. Listening on port 8080...


Client connected: /127.0.0.1
Received request from client: Hello from client!
Client disconnected: /127.0.0.1
```

Client:
```
Connected to server: /127.0.0.1
Sent request to server: Hello from client!
Received response from server: Hello from server!
```

The Client-Server architecture pattern allows clients and servers to communicate over a network. The server listens for incoming client connections, processes client requests, and sends responses back to the clients. Clients establish connections to the server, send requests, and receive responses. This pattern is useful for distributed systems where clients and servers interact and exchange data.