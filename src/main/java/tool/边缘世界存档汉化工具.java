package tool;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从地图存档里提取名称，好汉化
 */
public class 边缘世界存档汉化工具 {
    public static void main(String[] args) {
        // 替换翻译();
        提取英文关键词();
    }

    private static void 替换翻译() {
        try {
            File en = new File("C:\\Program Files (x86)\\Steam\\steamapps\\common\\RimWorld\\Mods\\Custom map The Earth 汉化\\Defs\\en.txt");
            File cn = new File("C:\\Program Files (x86)\\Steam\\steamapps\\common\\RimWorld\\Mods\\Custom map The Earth 汉化\\Defs\\cn.txt");
            List<String> ens = FileUtils.readLines(en, StandardCharsets.UTF_8);
            List<String> cns = FileUtils.readLines(cn, StandardCharsets.UTF_8);
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < ens.size(); i++) {
                map.put(ens.get(i), cns.get(i));
            }

            File input = new File("C:\\Users\\jesse\\AppData\\LocalLow\\Ludeon Studios\\RimWorld by Ludeon Studios\\Saves\\人类革新联盟2.13.rws");
            int count = 0;
            StringBuilder result = new StringBuilder();
            for (String line : FileUtils.readLines(input, StandardCharsets.UTF_8)) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (line.contains(entry.getKey())) {
                        line = line.replace(entry.getKey(), entry.getValue());
                        count++;
                    }
                }
                result.append(line).append(System.lineSeparator());
            }

            System.out.println("替换 " + count + "/" + map.size() + " 处翻译。");
            File output = new File("C:\\Users\\jesse\\AppData\\LocalLow\\Ludeon Studios\\RimWorld by Ludeon Studios\\Saves\\人类革新联盟3.0.rws");
            FileUtils.writeStringToFile(output, result.toString(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void 提取英文关键词() {
        File xmlFile = new File("C:\\Program Files (x86)\\Steam\\steamapps\\workshop\\content\\294100\\2602058532\\Defs\\The_Earth_IV.xml");
        try {
            List<String> list = new ArrayList<>();
            for (String line : FileUtils.readLines(xmlFile, StandardCharsets.UTF_8)) {
                if (line.contains("<name>") && line.contains("</name>")
                        || line.contains("<label>") && line.contains("</label>")
                        || line.contains("<description>") && line.contains("</description>")
                        || line.contains("<summary>") && line.contains("</summary>")
                        || line.contains("<templateName>") && line.contains("</templateName>")
                ) {
                    list.add(line);
                    System.out.println(line);
                }
            }
            File en = new File("C:\\Program Files (x86)\\Steam\\steamapps\\common\\RimWorld\\Mods\\Custom map The Earth 汉化\\Defs\\en.txt");
            FileUtils.writeStringToFile(en, StringUtils.join(list, '\n'), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
