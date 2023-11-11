package pipeandfilter.Impl;

import pipeandfilter.Filter;
import java.util.Arrays;

public class PhoneNumbersFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        String[] modifiedData = Arrays.stream(input.split("\n")).map(row -> {
            String[] data = row.split(",");

            if (data.length > 0) {
                String phoneNumbers = data[2];

                if (!phoneNumbers.startsWith("+")){
                    data[2] = "+389" + phoneNumbers.substring(1);
                }

            }
            return String.join(",", data);
        }).toArray(String[]::new);

        return String.join("\n", modifiedData);
    }
}
