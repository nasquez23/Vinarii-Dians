import pipeandfilter.Impl.CoordinatesFilter;
import pipeandfilter.Impl.PhoneNumbersFilter;
import pipeandfilter.Impl.WebFilter;
import pipeandfilter.Impl.WorkingHoursFilter;
import pipeandfilter.Pipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PipeAndFilterProblem {
    public static void main(String[] args) throws FileNotFoundException  {

        String string;
        ClassLoader loader = PipeAndFilterProblem.class.getClassLoader();
        Scanner scanner = new Scanner(new File("input/wineries.csv"));

        Pipe<String> pipeWineries = new Pipe<String>();

        CoordinatesFilter coordinatesFilter = new CoordinatesFilter();
        PhoneNumbersFilter phoneNumbersFilter = new PhoneNumbersFilter();
        WebFilter webFilter = new WebFilter();
        WorkingHoursFilter workingHoursFilter = new WorkingHoursFilter();


        pipeWineries.addFilter(coordinatesFilter);
        pipeWineries.addFilter(phoneNumbersFilter);
        pipeWineries.addFilter(webFilter);
        pipeWineries.addFilter(workingHoursFilter);
        ArrayList<String> wineries = new ArrayList<>();

        while (scanner.hasNextLine()) {
            string = pipeWineries.runFilters(scanner.nextLine());
            if (!Objects.equals(string, ""))
                wineries.add(string);
        }

    }
}