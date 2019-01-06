package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileOutput {
    private String fileOutputPath;

    public FileOutput(String fileOutputPath) {
        this.fileOutputPath = fileOutputPath;
    }

    public void writeContig(Set<String> contigs) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for(String line:contigs){
            writer.write(line + "\n");

        }
        writer.close();

    }
    private static String aryToString(List<Integer> ary){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ary.size(); i++){
            sb.append(ary.get(i)).append(",");
        }

        sb.deleteCharAt(sb.length() -1);

        return sb.toString();
    }

    public void writeSpeTable(Map<String,List<Integer>> speTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (Map.Entry<String,List<Integer>> entry: speTable.entrySet()){
            writer.write(entry.getKey()+"\t" + aryToString(entry.getValue()) +"\n");

        }
        writer.close();
    }


}