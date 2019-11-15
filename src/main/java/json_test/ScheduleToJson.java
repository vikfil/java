package json_test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Lesson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleToJson implements Runnable {
    private  String jsonFile;
    private List<Lesson> list;

    public ScheduleToJson(String file, List<Lesson> list) {
        jsonFile = file;
        this.list = list;
    }

    @Override
    public void run() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(jsonFile), list);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessonsFromFile() {
        List<Lesson> list = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(new File(jsonFile), List.class);
        }
        catch (JsonParseException e) { e.printStackTrace(); }
        catch (JsonMappingException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        return list;
    }
}
