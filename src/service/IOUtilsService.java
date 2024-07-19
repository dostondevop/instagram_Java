package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IOUtilsService<T> {
    static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type type;

    public IOUtilsService() {}

    public IOUtilsService(Type type) {
        this.type = type;
    }

    public void write(String path, List<T> arr) {
        String json = arrToJson(arr);
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(path);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    bufferedWriter.close();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<T> read(String path) {
        Reader reader = null;
        BufferedReader bufferedReader = null;
        List<T> list = null;
        try {
            reader = new FileReader(path);
            bufferedReader = new BufferedReader(reader);
            String s = "", temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                s += temp;
            }
            list = gson.fromJson(s, type);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                reader.close();
            } catch (IOException e) {
                e.getCause();
            }
        }
        return list != null ? list : new ArrayList<>();
    }

    public List<T> jsonToArr(String s) {
        Type listType = new TypeToken<List<T>>() {}.getType();
        List<T> list = gson.fromJson(s, listType);
        return list;
    }

    public String arrToJson(List<T> arr) {
        String json = gson.toJson(arr);
        return json;
    }
}
