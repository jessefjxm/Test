package test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.bms.common.RmiVerifyClient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        RmiVerifyClient.初始化登录();
    }

    private static void json() {
        String path = "CustomBuffDataDTO.json";
        CustomBuffDataDTO dto = new CustomBuffDataDTO();
        dto.isItem = true;
        dto.skillId = 1;
        dto.name = "test";
        try {
            FileUtils.writeStringToFile(new File(path), JSON.toJSONString(dto), StandardCharsets.UTF_8);
            String str = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
            CustomBuffDataDTO read = JSON.parseObject(str, CustomBuffDataDTO.class);
            System.out.println(JSON.toJSONString(read));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void kryo() {
        String path = "name.ser";
        SerializedName serializedName = new SerializedName();
        serializedName.faceNames = new HashMap<>();
        serializedName.faceNames.put(111, "222");
        KryoUtil.writeToFile(path, serializedName);
        final SerializedName name = KryoUtil.readFromFile(path, SerializedName.class);
        System.out.println(name.faceNames);
    }
}
