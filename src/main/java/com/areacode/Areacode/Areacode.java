package com.areacode.Areacode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Areacode {

    public Areacode(){

    }
    public static List<Path> findByFileName(String pathString, String fileName)
            throws IOException {
        Path path = Paths.get(pathString);
        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path, Integer.MAX_VALUE, (p, basicFileAttributes) -> p.getFileName().toString().equalsIgnoreCase(fileName))
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;
    }

    public static String getAreaCodeFromCSV(String enteredZipCode) throws Exception {
        List<Path> result = findByFileName(".", "areacodes.csv");
        String file = String.valueOf(result.getFirst());
        BufferedReader reader = null;
        String line;
        try{
            //OUTPUT_LABEL.setText("Output");
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                for(String index : row) {
                    if (index.equals(enteredZipCode)) {
                        System.out.println(line);
                        return line;
                    }

                }

            }
        } finally {
            try {
                assert reader != null;
                reader.close();
            }
            catch(Exception d){
                //d.printStackTrace();
            }
        }
        return "Area Code not found";
    }
}
