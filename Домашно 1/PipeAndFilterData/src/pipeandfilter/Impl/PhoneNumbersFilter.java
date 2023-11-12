package pipeandfilter.Impl;

import pipeandfilter.Filter;
import java.util.Arrays;

public class PhoneNumbersFilter implements Filter<String> {
    @Override
    public String execute(String input) {
        String[] modifiedData = Arrays.stream(input.split("/n")).map(row -> {
            StringBuilder modifiedRow = new StringBuilder();
            String[] data = row.split(",");
            if (data.length > 3 ){
                String phoneNumbers = data[3];

                if(!phoneNumbers.equals("phone") && !phoneNumbers.equals("") && !phoneNumbers.startsWith("+")){
                    data[3] = "+389" + phoneNumbers.substring(1);
                }

                modifiedRow.append(String.join(",", data));
            }else{
                modifiedRow.append(String.join(",", data)).append(",NoInfo");
            }
            return modifiedRow.toString();
        }).toArray(String[]::new);

        return String.join("\n", modifiedData);
    }
}
