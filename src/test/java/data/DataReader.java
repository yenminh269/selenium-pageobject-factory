package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {
  public static List<HashMap<String, String>> getJsonData(String fileName) throws IOException {
    File file = new File(fileName);
    String json = FileUtils.readFileToString(file);

    ObjectMapper objectMapper = new ObjectMapper();

    List<HashMap<String, String>> data = objectMapper.readValue(json, new TypeReference<List<HashMap<String, String>>>(){
    });

    return data;
  }
}
