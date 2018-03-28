package net.thegaminghuskymc.huskylib2.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.*;

public class JsonUtils {

    public static final Gson GSON = new Gson();

    @Nullable
    public static JsonObject read(File file) {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            return read(fileInputStream);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(fileInputStream);
        }

        return null;
    }

    @Nullable
    public static JsonObject read(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        JsonReader jsonReader = null;

        try {
            inputStreamReader = new InputStreamReader(inputStream);
            jsonReader = new JsonReader(inputStreamReader);
            return GSON.fromJson(jsonReader, JsonObject.class);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(jsonReader);
            IOUtils.closeQuietly(inputStreamReader);
        }

        return null;
    }

    @Nullable
    public static File write(File file, JsonObject rootObject) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        JsonWriter jsonWriter = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            jsonWriter = new JsonWriter(outputStreamWriter);
            jsonWriter.setIndent("  ");
            GSON.toJson(rootObject, jsonWriter);
            return file;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(jsonWriter);
            IOUtils.closeQuietly(outputStreamWriter);
        }

        return null;
    }

}