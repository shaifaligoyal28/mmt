package First;


import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FirstService {

    public static int calculateTotaltimestamp(List<Input> inputList) throws ExecutionException, InterruptedException {

        List<Future<Integer>> result = new ArrayList<Future<Integer>>();
        for(final Input i:inputList){
            if(i.getIsParallel().equalsIgnoreCase("true")){
                ExecutorService executorService = Executors.newCachedThreadPool();
                int j = Integer.parseInt(i.getCount());

                for(int k=0;k<j;k++){

                    result.add(executorService.submit(new Callable<Integer>() {
                        public Integer call() {
                            try {
                            //    System.out.println(Thread.currentThread().getName());
                                HttpReq.sendGet(i.getUrl());

                            }catch(Exception e){
                                System.out.println(e);
                            }
                            return 1;
                        }

                    }));
                }
                executorService.shutdown();
            }
          /*  for(Future<Integer> res:result){
                try {
                    res.get();
                }catch(Exception e){

                }
            }*/
          //  Future<Integer> res ;

            if(i.getIsParallel().equalsIgnoreCase("false")){
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                int j = Integer.parseInt(i.getCount());
                System.out.println("inside this");
                for(int k=0;k<j;k++){

                    Future<Integer> res =executorService.submit(new Callable<Integer>() {
                        public Integer call() {
                            try {
                                System.out.println("sequential");
                           //     System.out.println(Thread.currentThread().getName());
                                HttpReq.sendGet(i.getUrl());

                            }catch(Exception e){
                                System.out.println(e);
                            }
                            return 1;
                        }

                    });
                //    res.get();
                }
                executorService.shutdown();
            }

        }
        return 1;
    }








    public static String readDataFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        String fileContentStr = FileUtils.readFileToString(file);
        return fileContentStr;
    }
}
