package Util;

import java.io.*;

public class FileUtil {

    public static String readStringFile(File file) {
        StringBuffer sb = new StringBuffer();
        String tmpString = null;
        try {
            if (!file.exists())
                throw new FileNotFoundException();
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            while ((tmpString = br.readLine()) != null) {
                sb.append(tmpString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
