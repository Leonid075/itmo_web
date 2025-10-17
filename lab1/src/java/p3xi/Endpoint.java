package p3xi;


public abstract class Endpoint {
    private final String method;
    private final String path;

    public Endpoint(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public abstract void process(String query, String body);

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
