package First;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class First {

    private static ObjectMapper objectMapper = new ObjectMapper();
public static void main(String args[]){
    try{

        String input = FirstService.readDataFromFile("../mmt//src//main//resources//Input.json");
        System.out.println(input);
        List<Input> inputList = getInput(input);
        for(Input i : inputList){
            System.out.println(i.getCount()+" "+i.getIsParallel()+" "+i.getUrl());
        }
        Date startDate = new Date();
        Calendar startcal = Calendar.getInstance();
        System.out.println(startcal);
        FirstService.calculateTotaltimestamp(inputList);
        Calendar endcal = Calendar.getInstance();
        System.out.println(endcal);
        System.out.println("Main thread ends");
        Date endDate = new Date();

        long diff= endcal.getTimeInMillis()-startcal.getTimeInMillis();
        System.out.println("Time taken"+diff);
    }
    catch(Exception e){
        System.out.println(e);
    }
}

    public static List<Input> getInput(String input){
        System.out.println("Fetch Inout list");
        Input[] inputList = null;
        try {
            inputList = objectMapper.readValue(input, Input[].class);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return Arrays.asList(inputList);
    }
}
