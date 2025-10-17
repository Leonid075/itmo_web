package p3xi;


public final class Responce {
    private Responce() {}

    public static String getStatusText(int statusCode) {
        switch (statusCode) {
            case 200: return "OK";
            case 400: return "Bad Request";
            case 404: return "Not Found";
            case 405: return "Method Not Allowed";
            case 500: return "Internal Server Error";
            case 422: return "Unprocessable Content";
            default: return "Unknown";
        }
    }

    public static void sendResponse(String responce) {
        System.out.println(responce);
        System.out.flush();
    }

    public static String generateResponse(Integer statusCode, String[] headers, String body) {
        String headersString = "";

        for(String i : headers) {
            headersString += i+"\n";
        }
        if (body == null) {
            return String.format("HTTP/1.1 %d %s\n%s", statusCode, getStatusText(statusCode), headersString);
        }
        else {
            headersString += "Content-Length: " + body.length() + "\n";
            return String.format("HTTP/1.1 %d %s\n%s\n%s\n", statusCode, getStatusText(statusCode), headersString, body);
        }
    }
}
