package pipeandfilter.Impl;

import pipeandfilter.Filter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinatesFilter implements Filter<String> {


    @Override
    public String execute(String inputFile) {

        //inputCoordinates = "POINT (22.233366 41.4140987)"
        //outputCoordinates = "22.233366,41.4140987"
        Pattern pattern = Pattern.compile("POINT \\(([^)]+)");

        String[] modifiedData = Arrays.stream(inputFile.split("/n")).map(row -> {
            String[] data = row.split(",");

            if(row.contains("WKT")){
                String[] items = row.split(",");
                items[0]="latitude,longitude";
                return String.join(",", items);
            }

            Matcher matcher = pattern.matcher(data[0]);

            if (matcher.find()) {
                String[] coordinates = matcher.group(1).split(" ");
                data[0] = coordinates[0] + "," + coordinates[1];
            } else {
                data[0] = "NoInfo,NoInfo";
            }
            return String.join(",", data);
        }).toArray(String[]::new);


        return String.join("\n", modifiedData);
    }
}
