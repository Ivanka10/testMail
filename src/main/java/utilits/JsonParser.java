package utilits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilits.model.UserModel;

import java.io.File;
import java.io.IOException;

public class JsonParser {

    private ObjectMapper objectMapper;
    private Logger logger = LogManager.getLogger(JsonParser.class);

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    public  UserModel[] getData(File jsonFile) {
        UserModel[] data = new UserModel[0];
        try {
            data = objectMapper.readValue(jsonFile, UserModel[].class);
        } catch (IOException e) {
            logger.error(e.getClass());
            e.printStackTrace();
        }
        return data;
    }
}
