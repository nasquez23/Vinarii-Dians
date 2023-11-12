import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebFilter implements Filter<String> {

    @Override
    public String execute(String input) {
        String[] rows = input.split("\n");
           String result="";
        for (String row : rows) {
            String[] parts = row.split(",");
            if (parts.length >= 4 && (parts[3].trim().startsWith("https://") || parts[3].trim().contains(".com") || parts[3].trim().contains(".mk"))) {
                 result+=row+"\n";

            } else {
                parts[3]="Не постои оваа страница";
                  row=String.join(",",parts);
                 result+=row+"\n";
            }
        }

      return result.trim();
    }
}
