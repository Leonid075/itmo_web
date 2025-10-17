package p3xi;

import com.fastcgi.FCGIInterface;

import java.util.HashMap;
import java.util.Properties;

public class WebServer {
    HashMap<String, Endpoint> endpoints;

    public WebServer() {
        endpoints = new HashMap<>();
    }

    public void addEndpoint(Endpoint endpoint) {
        endpoints.put(String.format("%s:%s", endpoint.getMethod(), endpoint.getPath()), endpoint);
    }

    public void start() {
        FCGIInterface fcgiInterface = new FCGIInterface();

        while (fcgiInterface.FCGIaccept() >= 0) {
            try {
                Properties env = System.getProperties();
                String method = env.getProperty("REQUEST_METHOD", "");
                if (method.equals("OPTIONS")) {
                    Responce.sendResponse(Responce.generateResponse(200,
                        new String[] { },
                        null));
                    continue;
                }
                if (!method.equals("GET") && !method.equals("POST")) {
                    Responce.sendResponse(Responce.generateResponse(405,
                        new String[] { "Content-Type: application/json" },
                        "{\"status\": \"Method not allowed\"}"));
                    continue;
                }

                String path = env.getProperty("PATH_INFO", "");
                Endpoint endpoint = endpoints.get(String.format("%s:%s", method, path));
                if (endpoint == null) {
                    Responce.sendResponse(Responce.generateResponse(404,
                        new String[] { "Content-Type: application/json" },
                        "{\"status\": \"Not found\"}"));
                }
                
                String query = env.getProperty("QUERY_STRING", "");
                String body = "";
                while (true) {
                    int c = System.in.read();
                    if (c==-1) break;
                    body += (char)c;
                }
                endpoint.process(query, body);

            } catch (Exception e) {
                Responce.sendResponse(Responce.generateResponse(500, 
                        new String[] { "Content-Type: application/json" },
                        String.format("{\"status\": \"Internal server error %s\"}", e.toString())));
            }
        }
    }
}
