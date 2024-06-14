import java.io.*;
import java.net.*;

    public class ChatClient {
        private static final String SERVER_IP = "127.0.0.1";
        private static final int SERVER_PORT = 1234;

        public static void main(String[] args) {
            try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Connected to chat server");
                System.out.println(reader.readLine()); // Read and print the server's welcome message
                String username = reader.readLine(); // Read and store the assigned username
                System.out.println(username);

                Thread messageThread = new Thread(() -> {
                    String message;
                    try {
                        while ((message = reader.readLine()) != null) {
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                messageThread.start();

                String userInputMessage;
                while ((userInputMessage = userInput.readLine()) != null) {
                    writer.println(userInputMessage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



