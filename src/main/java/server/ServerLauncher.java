package server;

public class ServerLauncher {
    public final static int PORT = 1337;
    public static Server server;

    public static void main(String[] args) {
        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            //server.handleLoadCourses("Hiver");
            server.run();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}