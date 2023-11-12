package input;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OSMDataExtractor {

    public static void main(String[] args) {
        try {

            String overpassEndpoint = "https://overpass-api.de/api/interpreter";
            String overpassQuery = "[out:csv(::lat,::lon,name,phone,website,opening_hours,blank;false;\",\")][timeout:25];" +
                    "area['ISO3166-1'='MK']->.a;" +
                    "(node['craft'='winery'](area.a);" +
                    "way['craft'='winery'](area.a);" +
                    "node['tourism'='wine_cellar'](area.a);" +
                    "way['tourism'='wine_cellar'](area.a);" +
                    "node['shop'='wine'](area.a);" +
                    "way['shop'='wine'](area.a););" +
                    "out;>;out skel qt;";

            String osmData = makeHttpRequest(overpassEndpoint, overpassQuery);
            String csvFilePath = "Домашно 1/PipeAndFilterData/src/input/osm_wineries_data.csv";

            writeCsvToFile(osmData, csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String makeHttpRequest(String endpoint, String query) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        connection.getOutputStream().write(query.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }


    private static void writeCsvToFile(String csvData, String filePath) throws IOException {
        String[] data = csvData.split(",");
        StringBuilder dataReady = new StringBuilder();
        StringBuilder row;
        for (int i=0; i< data.length; i++){
            row = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                if(i>=data.length) break;
                row.append(data[i]).append(",");
                i++;
            }
            i--;
            row.append("\n");
            dataReady.append(row);
        }


        try (FileWriter csvWriter = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            csvWriter.write(dataReady.toString());
        }
    }
}

