package test;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String path = "name.ser";
        SerializedName serializedName = new SerializedName();
        serializedName.faceNames = new HashMap<>();
        serializedName.faceNames.put(111, "222");
        KryoUtil.writeToFile(path, serializedName);
        final SerializedName name = KryoUtil.readFromFile(path, SerializedName.class);
        System.out.println(name.faceNames);
    }
}
