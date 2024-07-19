package service;

import com.google.gson.reflect.TypeToken;
import models.Comment;
import models.Like;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LikeService implements BaseService<Like> {
    public static final String PATH = "C:\\E\\Wanna_Learn\\Java\\instagram\\src\\Docs\\likes.json";
    Type listType = new TypeToken<List<Like>>() {}.getType();
    IOUtilsService<Like> io = new IOUtilsService<>(listType);

    @Override
    public Like add(Like like) {
        return null;
    }
}
