package pipeandfilter.Impl;

import pipeandfilter.Filter;
import java.util.Arrays;
import java.util.Objects;

public class BlankNameFilter implements Filter<String> {

        @Override
        public String execute(String inputFile) {

        String[] modifiedData = Arrays.stream(inputFile.split("/n")).map(row -> {
                if(row.equals("")) return null;

                String[] data = row.split(",");
                if(data.length<=2 || data[2].equals("")) return null;
                else {
                        for (int i = 0; i < data.length; i++) {
                                if(data[i].equals("")) data[i] = "NoInfo";
                        }

                        return String.join(",", data);
                }
        }).filter(Objects::nonNull).toArray(String[]::new);


        return String.join("\n", modifiedData);
        }
}
