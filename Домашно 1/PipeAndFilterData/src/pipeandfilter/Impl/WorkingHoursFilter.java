package pipeandfilter.Impl;

import pipeandfilter.Filter;
import java.util.Arrays;

public class WorkingHoursFilter implements Filter<String> {
    @Override
    public String execute(String inputFile) {

        String[] modifiedData = Arrays.stream(inputFile.split("\n")).map(row -> {
            String[] data = row.split(",");

            if (data.length > 0) {
                String workingHours = data[data.length - 1];

                if (workingHours.matches("\\d{2}:\\d{2}-\\d{2}:\\d{2}")) {
                    String[] hours = workingHours.split("-");
                    data[data.length - 1] = hours[0];
                    data = Arrays.copyOf(data, data.length + 1);
                    data[data.length - 1] = hours[1];
                } else {
                    data[data.length - 1] = "NoInfo";
                    data = Arrays.copyOf(data, data.length + 1);
                    data[data.length - 1] = "NoInfo";
                }
            }
            return String.join(",", data);
        }).toArray(String[]::new);

        return String.join("\n", modifiedData);
    }
}


