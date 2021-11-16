package server;

public class ServerStarter {
    public static void main(String[] args) {
        int port;
        try {
            port = Integer.parseInt(args[0]);
            if (port < 1 || port > (1 << 16) - 1) {
                port = 8123;
            }
        } catch (RuntimeException e) {
            port = 8123;
        }

        RedisServer server = new RedisServer(port);
        server.start();
    }
}
