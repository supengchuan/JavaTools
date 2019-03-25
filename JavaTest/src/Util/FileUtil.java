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

    public static void writeBytesFile(File file, byte[] content) {
        FileOutputStream os = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            os.write(content);
            os.flush();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void appendStringFile(File file, String content) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file.getName(), true);
            writer.write(content);
            writer.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeStringFile(File file, String content) {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
