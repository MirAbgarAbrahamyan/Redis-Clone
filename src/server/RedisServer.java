package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisServer {
    private final int port;

    public RedisServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(port)) {
            ExecutorService threadPool = Executors.newFixedThreadPool(100);
            while (true) {
                Socket clientConnection = server.accept();
                System.out.println("Someone is connected");
                ServerTask task = new ServerTask(clientConnection);
                threadPool.submit(task);
            }
        } catch(IOException e) {
            System.err.println("Cannot start the server");
        }
    }
}
