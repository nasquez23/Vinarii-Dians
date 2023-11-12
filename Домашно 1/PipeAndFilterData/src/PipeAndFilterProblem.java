import pipeandfilter.Impl.*;
import pipeandfilter.Pipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PipeAndFilterProblem {
    public static void main(String[] args) throws FileNotFoundException  {

        String pipeGmapsOutput;
        String pipeOSMoutput;

        Pipe<String> pipeGMaps = new Pipe<>();
        Pipe<String> pipeOSM = new Pipe<>();

        CoordinatesFilter coordinatesFilter = new CoordinatesFilter();
        PhoneNumbersFilter phoneNumbersFilter = new PhoneNumbersFilter();
        WebFilter webFilter = new WebFilter();
        WorkingHoursFilter workingHoursFilter = new WorkingHoursFilter();
        BlankNameFilter blankNameFilter = new BlankNameFilter();
        NoInfoFilter noInfoFilter = new NoInfoFilter();


        pipeGMaps.addFilter(coordinatesFilter);
        pipeGMaps.addFilter(blankNameFilter);
        pipeGMaps.addFilter(phoneNumbersFilter);
        pipeGMaps.addFilter(webFilter);
        pipeGMaps.addFilter(workingHoursFilter);
        pipeGMaps.addFilter(noInfoFilter);


        pipeOSM.addFilter(blankNameFilter);
        pipeOSM.addFilter(coordinatesFilter);
        pipeOSM.addFilter(phoneNumbersFilter);
        pipeOSM.addFilter(webFilter);
        pipeOSM.addFilter(workingHoursFilter);
        pipeOSM.addFilter(noInfoFilter);

        Set<String> wineries = new LinkedHashSet<>();

        Scanner scanner = new Scanner(new File("Домашно 1/PipeAndFilterData/src/input/gmaps_wineries_data.csv"));
        while (scanner.hasNextLine()) {
            pipeGmapsOutput = pipeGMaps.runFilters(scanner.nextLine());
            if (!Objects.equals(pipeGmapsOutput, "")) wineries.add(pipeGmapsOutput);

        }

        scanner = new Scanner(new File("Домашно 1/PipeAndFilterData/src/input/osm_wineries_data.csv"));
        while (scanner.hasNextLine()) {
            pipeOSMoutput = pipeOSM.runFilters(scanner.nextLine());
            if (!Objects.equals(pipeOSMoutput, "")) wineries.add(pipeOSMoutput);

        }

        try (FileWriter csvWriter = new FileWriter("Домашно 1/PipeAndFilterData/src/output/wineries.csv", StandardCharsets.UTF_8)) {
            for (String winery : wineries) {
                csvWriter.write(winery);
                csvWriter.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}