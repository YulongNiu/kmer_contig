package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInput {
    public static List<String> read(String file) {
        List seq = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            StringBuffer buffer = new StringBuffer();
            String line = in.readLine();


            if (line.charAt(0) != '>')
                throw new IOException("First line of " + file + " should start with '>'");

            for (line = in.readLine().trim(); line != null; line = in.readLine()) {
                if (line.length() > 0 && line.charAt(0) == '>') {
                    seq.add(buffer.toString());
                    buffer = new StringBuffer();
                } else
                    buffer.append(line.trim());
            }
            if (buffer.length() != 0)
                seq.add(buffer.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Error when reading " + file);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error when reading " + file);
            e.printStackTrace();
        }
        return seq;

    }
    public static File[] getFiles(String path) {
        File file = new File(path);
        File[] files = new File[0];
        if (file.isDirectory()) {
            files = file.listFiles();

        }
        return files;
    }
}
