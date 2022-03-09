package test;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class SerializedName {
    public static final String PATH = "ser/Name.ser";

    public Map<Integer, String> itemNames;
    public Map<Integer, String> skillNames;
    public Map<Integer, String> npcNames;
    public Map<Integer, String> mapNames;
    public Map<Integer, String> mapCityNames;
    public Map<Integer, String> mapSimpleNames;
    public Map<Integer, String> questNames;
    public Map<Integer, String> mobNames;
    public Map<Integer, String> faceNames;
    public Map<Integer, String> hairNames;
}
