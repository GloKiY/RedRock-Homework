package team.redrock.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Collections;

/**
 * 测试建立链接
 */
public class Demo {
    public static void main(String[] args) throws IOException, ParseException {
        String class_id = "04031701";
        String url = "http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey="+class_id;
        String response = HttpClientUtil.sendPost(url,null,"utf-8");

        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(response, JsonObject.class).getAsJsonArray("returnData");
        System.out.println(jsonArray.toString());


        for (int i=0; i<jsonArray.size(); i++){
            Student student = new Student();
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            student.setName(jsonObject.get("xm").getAsString());
            student.setClass_id(jsonObject.get("bj").getAsString());
            student.setStudent_id(jsonObject.get("xh").getAsString());
            student.setCollege(jsonObject.get("yxm").getAsString());
            System.out.println(student.toString());
            DaoTest.insertStudentDao(student);

        }

    }
}