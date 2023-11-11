package pipeandfilter.Impl;

import pipeandfilter.Filter;
import java.util.Arrays;

public class WorkingHoursFilter implements Filter<String> {
    @Override
    public String execute(String inputFile) {

        String[] modifiedData = Arrays.stream(inputFile.split("\n")).map(row -> {
            String[] data = row.split(",");

            if (data.length > 0) {
                String workingHours = data[data.length - 1]; // Assuming working hours is the last element

                if (workingHours.matches("\\d{2}:\\d{2}-\\d{2}:\\d{2}")) {
                    String[] hours = workingHours.split("-");
                    data[data.length - 1] = hours[0]; // Replace working hours with opening hours
                    data = Arrays.copyOf(data, data.length + 1); // Extend the array for closing hours
                    data[data.length - 1] = hours[1]; // Add closing hours at the end
                } else {
                    data[data.length - 1] = "NoInfo"; // If format doesn't match, set "NoInfo"
                    data = Arrays.copyOf(data, data.length + 1); // Extend the array for closing hours
                    data[data.length - 1] = "NoInfo"; // Add "NoInfo" for closing hours
                }
            }
            return String.join(",", data);
        }).toArray(String[]::new);

        return String.join("\n", modifiedData);
    }
}


