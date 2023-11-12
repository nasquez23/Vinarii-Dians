package pipeandfilter.Impl;

import pipeandfilter.Filter;
import java.util.Arrays;

public class WorkingHoursFilter implements Filter<String> {
    @Override
    public String execute(String inputFile) {

        String[] modifiedData = Arrays.stream(inputFile.split("\n")).map(row -> {
            String[] data = row.split(",");

            if (data.length > 5) {
                String[] parts = data[5].split(" ");
                String workingHours = parts[parts.length - 1];

                if (workingHours.matches("\\d{2}:\\d{2}-\\d{2}:\\d{2}")) {
                    String[] hours = workingHours.split("-");
                    data[5] = hours[0];
                    data = Arrays.copyOf(data, data.length + 1);
                    data[6] = hours[1];
                } else if(workingHours.equals("working-hours")){
                    data[5] = "openingHours,closingHours";
                } else {
                    data[5] = "NoInfo";
                    data = Arrays.copyOf(data, data.length + 1);
                    data[6] = "NoInfo";
                }
            }else {
                data = Arrays.copyOf(data, 7);
                data[5] = "NoInfo";
                data[6] = "NoInfo";
            }
            return String.join(",", data);
        }).toArray(String[]::new);

        return String.join("\n", modifiedData);
    }
}


