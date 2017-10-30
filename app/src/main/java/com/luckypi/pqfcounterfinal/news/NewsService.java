package com.luckypi.pqfcounterfinal.news;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dell on 2017/6/6.
 * 通过GSON解析JSON文件
 */

public class NewsService {
    public static List<NewsInfo> getNewsInfo(InputStream is) throws IOException {
        byte[] buffer=new byte[is.available()];
        is.read(buffer);
        String json=new String(buffer,"utf-8");
        Gson gson=new Gson();
        Type listType=new TypeToken<List<NewsInfo>>(){}.getType();
        List<NewsInfo> NewsInfos=gson.fromJson(json,listType);
        return NewsInfos;
    }
}
