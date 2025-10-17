import p3xi.*;

public class Main {
    public static void main(String[] args) {
        WebServer server = new WebServer();
        
        server.addEndpoint(new CheckPiontEndpoint());
        
        server.start();
    }
}
