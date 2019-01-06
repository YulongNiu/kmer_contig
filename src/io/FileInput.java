package io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String,List<Integer>> read_contig(String filePath){
        Map<String, List<Integer>> map = new HashMap<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;
            int i =0;

            while ((str = in.readLine()) != null) {

                map.put(str.trim(),new ArrayList<Integer>());

//                System.out.println(Arrays.toString(arr));
                i +=1;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

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
