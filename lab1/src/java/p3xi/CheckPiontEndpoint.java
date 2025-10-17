package p3xi;

import java.math.BigDecimal;

public class CheckPiontEndpoint extends Endpoint {
    public CheckPiontEndpoint() {
        super("POST", "/check");
    }

    private BigDecimal[] parseJson(String json) {
        BigDecimal x = null, y = null, r = null;
        json = json.replaceAll("[{}\\s]", "");

        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].replaceAll("\"", "");
                String value = keyValue[1].replaceAll("\"", "");

                try {
                    switch (key) {
                        case "x":
                            x = new BigDecimal(value);
                            break;
                        case "y":
                            y = new BigDecimal(value);
                            break;
                        case "r":
                            r = new BigDecimal(value);
                            break;
                    }
                } catch (Exception e) {}
            }
        }

        return new BigDecimal[] { x, y, r };
    }

    public void process(String query, String body) {
        BigDecimal x = null, y = null, r = null;
        BigDecimal[] params = parseJson(body);
        x = params[0];
        y = params[1];
        r = params[2];
        
        if (x == null || x.compareTo(new BigDecimal(-5)) < 0 || x.compareTo(new BigDecimal(3)) > 0) {
            Responce.sendResponse(Responce.generateResponse(400,
                    new String[] { "Content-Type: application/json" },
                    "{\"status\": \"Missing or wrong X\"}"));
            return;
        }
        if (y == null || y.compareTo(new BigDecimal(-3)) < 0 || y.compareTo(new BigDecimal(5)) > 0) {
            Responce.sendResponse(Responce.generateResponse(400,
                    new String[] { "Content-Type: application/json" },
                    "{\"status\": \"Missing or wrong Y\"}"));
            return;
        }
        if (r == null || r.compareTo(new BigDecimal(0)) < 0 || r.compareTo(new BigDecimal(5)) > 0) {
            Responce.sendResponse(Responce.generateResponse(400,
                    new String[] { "Content-Type: application/json" },
                    "{\"status\": \"Missing or wrong R\"}"));
            return;
        }

        Boolean responce;
        if (x.compareTo(new BigDecimal(0)) >= 0) {
            if (y.compareTo(new BigDecimal(0)) >= 0)
                responce = (x.pow(2).add(y.pow(2)).compareTo(r.pow(2)) <= 0);
            else
                responce = false;
        } else {
            if (y.compareTo(new BigDecimal(0)) >= 0){
                responce = y.compareTo(x.divide(new BigDecimal(2)).add(r.divide(new BigDecimal(2)))) <= 0;
            }
            else
                responce = (x.compareTo(r.negate()) >= 0 && y.compareTo(r.negate()) >= 0);
        }

        Responce.sendResponse(Responce.generateResponse(200,
                new String[] { "Content-Type: application/json" },
                String.format("{\"status\": %s}", responce.toString())));
        return;
    }
}
