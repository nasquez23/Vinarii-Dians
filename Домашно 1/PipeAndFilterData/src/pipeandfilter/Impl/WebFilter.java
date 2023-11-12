package pipeandfilter.Impl;

import pipeandfilter.Filter;


public class WebFilter implements Filter<String> {

    @Override
    public String execute(String input) {
        String[] rows = input.split("\n");
           StringBuilder result = new StringBuilder();
        for (String row : rows) {
            String[] parts = row.split(",");

            if (parts.length >= 5
                    && !parts[4].equals("website")
                    && !parts[4].trim().startsWith("https://")
                    && !parts[4].contains(".com")
                    && !parts[4].contains(".mk")) {
                parts[4]="NoInfo";
                row=String.join(",",parts);
            }
            result.append(row).append("\n");
        }

      return result.toString();
    }
}
