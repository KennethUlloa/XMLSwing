package xmlswing.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VariableProcessor {
    private HashMap<String, String> variables = new HashMap<>();
    private InputStream stream;

    public VariableProcessor(InputStream stream) {
        this.stream = stream;
    }

    public void set(String key, String value) {
        variables.put(key, value);
    }

    public String get(String key) {
        return variables.get(key);
    }

    public InputStream process() {
        if(variables.size() == 0) {
            return stream;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String content = reader.lines().collect(Collectors.joining("\n"));
        for(Map.Entry<String, String> entry : variables.entrySet()) {
            content = content.replace("${"+entry.getKey()+"}",entry.getValue());
        }
        return new ByteArrayInputStream(content.getBytes());
    }
}
