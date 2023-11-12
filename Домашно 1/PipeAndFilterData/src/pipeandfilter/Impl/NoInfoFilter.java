package pipeandfilter.Impl;

import pipeandfilter.Filter;

import java.util.Arrays;
import java.util.Objects;

public class NoInfoFilter implements Filter<String> {

    @Override
    public String execute(String inputFile) {

        String[] modifiedData = Arrays.stream(inputFile.split("/n")).map(row -> {
            String[] data = row.split(",");

            if(data.length < 3) return null;

            long NoInfoCount = Arrays.stream(data).filter(item -> item.equals("NoInfo")).count();
            if(NoInfoCount > 3) return null;

            return row;

        }).filter(Objects::nonNull).toArray(String[]::new);


        return String.join("\n", modifiedData);
    }


}
