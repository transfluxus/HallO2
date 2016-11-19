package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import http.DynamicResponseHandler;
import http.ResponseBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Created by smoky on 11/19/16.
 */
public class FbDynamicResponseHandler extends DynamicResponseHandler {
    static String fb_token = "EAAZATxKkSPWEBAE7AvbQ2VUFxyAdT6EKan7Kv33ZCO1n7WeJxhdSogLLOVzOhmOzoS0haLX52v2yZCTzZBgVpzeJ5Jm4QEj9BnD3DpeNMVMqd7HpmLqDcXJTCN7OyW1JaTglHs5TIZBj07pxHpW5bVHCKXoBs9eI6PA2VdDmPbkNGa2ZAYZC0v6";
    String contentType;
    ResponseBuilder responseBuilder;

    public FbDynamicResponseHandler(ResponseBuilder responseBuilder, String s) {
        super(responseBuilder, s);

        contentType = s;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        try {
            this.setHttpExchange(exchange);
            String var2 = this.parseRequestBody(exchange);
            System.out.println(var2);
            Headers var3 = exchange.getResponseHeaders();
            var3.add("Content-Type", this.contentType);

            byte[] var4;
            String hubChallenge;
            System.out.println("fuckit");
            if (!(hubChallenge = getHubChallenge(exchange.getRequestURI().getRawQuery())).equals("")) {
                var4 = hubChallenge.getBytes();
            } else {
                var4 = "all right".getBytes();
            }
            exchange.sendResponseHeaders(200, (long)var4.length);
            OutputStream var5 = exchange.getResponseBody();
            var5.write(var4);
            var5.flush();

//            Runtime.getRuntime().exec("echo fuckit > fuck.out");
            String jsn = "{\\\"recipient\\\":{\\\"id\\\":\\\"897563897011347\\\"},\\\"message\\\":{\\\"text\\\":\\\"fuck it\\\"}}";
//
            //String cUrlCommand= "curl -X POST -H \"Content-Type: application/json\" -d '{\"recipient\":{\"id\":\"897563897011347\" },\"message\":{\"text\":\"hello, world!\" }}' https://graph.facebook.com/v2.6/me/messages?access_token=EAAZATxKkSPWEBAE7AvbQ2VUFxyAdT6EKan7Kv33ZCO1n7WeJxhdSogLLOVzOhmOzoS0haLX52v2yZCTzZBgVpzeJ5Jm4QEj9BnD3DpeNMVMqd7HpmLqDcXJTCN7OyW1JaTglHs5TIZBj07pxHpW5bVHCKXoBs9eI6PA2VdDmPbkNGa2ZAYZC0v6b > ggg";
            //Runtime.getRuntime().exec(cUrlCommand);


            JSONObject json = new JSONObject();
            JSONObject recipient = new JSONObject();

            recipient.put("id", "897563897011347");
            JSONObject message = new JSONObject();

            message.put("text", "fuck it");
            json.put("message", message);

            json.put("recipient",recipient);

            String curl ="curl -X POST -H \"Content-Type:application/json\" -d \\\""+ jsn +"\\\" https://graph.facebook.com/v2.6/me/messages?access_token=EAAZATxKkSPWEBAE7AvbQ2VUFxyAdT6EKan7Kv33ZCO1n7WeJxhdSogLLOVzOhmOzoS0haLX52v2yZCTzZBgVpzeJ5Jm4QEj9BnD3DpeNMVMqd7HpmLqDcXJTCN7OyW1JaTglHs5TIZBj07pxHpW5bVHCKXoBs9eI6PA2VdDmPbkNGa2ZAYZC0v6b";


            System.out.println(HellO2.executeCommand(curl));

            System.out.println(jsn);
            System.out.println(curl);
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    private String getRequestBody(InputStream requestBody) {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try {
            Reader in = new InputStreamReader(requestBody, "UTF-8");
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
        } catch (Exception e) {
            return "";
        }

        return out.toString();
    }

    private String parseRequestBody(HttpExchange var1) throws IOException {
        InputStreamReader var2 = new InputStreamReader(var1.getRequestBody());
        StringBuilder var3 = new StringBuilder();

        while(var2.ready()) {
            var3.append((char)var2.read());
        }

        var2.close();
        return var3.toString();
    }

    private String getHubChallenge (String query) {
        if (query == null)
            return "";

        String[] queries = query.split("&");

        for (String queryPair : queries) {
            String[] pair = queryPair.split("=");

            if(pair[0].equals("hub.challenge")) {
                return pair[1];
            }
        }

        return "";
    }
}
