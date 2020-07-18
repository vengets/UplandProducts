package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<String> readFile(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        File fin =new File(
                getClass().getClassLoader().getResource(fileName).getFile());
        FileInputStream fis = new FileInputStream(fin);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
            if(!line.isEmpty())
                result.add(line);
        }

        br.close();
        return result;
    }
}
