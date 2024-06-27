package site.pixeldetective.swing.HttpConnector;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class HttpConnector {
    private static final String baseURL = "http://localhost:9000/pixel-detective/api";
    public URL u;

    public HttpConnector(String restAddress) {
        try {
            this.u = new URL(baseURL + restAddress);
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String get(Map<String, String> headers) {
        try {
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            if (headers != null) {
                headers.forEach((key, value) -> connection.setRequestProperty(key, value));
            }
            connection.connect();
            System.out.println(connection);
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                throw new RuntimeException("Request failed with code: " + responseCode + " - " + response.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect and get response", e);
        }
    }

    public String post(JSONObject data, Map<String, String> headers) {
        try {
            String jsonData = data.toString();
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("POST");
            if (headers != null) {
                headers.forEach((key, value) -> connection.setRequestProperty(key, value));
            }
            connection.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(jsonData.getBytes());
            }
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                throw new RuntimeException("Request failed with code: " + responseCode + " - " + response.toString());
            }
        } catch (ProtocolException e) {
            System.err.println("Protocol error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String put(JSONObject data, Map<String, String> headers) {
        try {
            String jsonData = data.toString();
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("POST");
            if (headers != null) {
                headers.forEach((key, value) -> connection.setRequestProperty(key, value));
            }
            connection.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(jsonData.getBytes());
            }
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                throw new RuntimeException("Request failed with code: " + responseCode + " - " + response.toString());
            }
        } catch (ProtocolException e) {
            System.err.println("Protocol error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}