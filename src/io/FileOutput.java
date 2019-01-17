package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    public void writeContigSplit(Set<String> contigs,int splitNum) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }

        String[] tem2 = file.getName().split("\\.");
        String name = tem2[0];


        List<String> contigsList = new ArrayList<>();
        List<Integer> allIndex = new ArrayList<>();
        // trans set to list
        for (String line:contigs){
            contigsList.add(line);
        }
        int length = contigsList.size();
        int tl = length % splitNum == 0 ? length / splitNum : (length
                / splitNum + 1);
        for (int i = 0; i < length ; i++) {
            allIndex.add(i);
        }
        for (int i = 0; i < splitNum; i++) {
            String w_files = file.getParent() +"/" + name + String.valueOf(i)+ ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(w_files));
            int end = (i + 1) * tl;
            final List<Integer> list1 = allIndex.subList(i * tl, end > length ? length : end);
            for (int j:list1){
                writer.write(contigsList.get(j)+"\n");
            }
            writer.close();
        }

    }

    private static String aryToString(List<Integer> ary){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ary.size(); i++){
            sb.append(ary.get(i)).append(",");
        }

        sb.deleteCharAt(sb.length() -1);

        return sb.toString();
    }

    private static String aryToString2(List<Float> ary){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ary.size(); i++){
            sb.append(ary.get(i)).append(",");
        }

        sb.deleteCharAt(sb.length() -1);

        return sb.toString();
    }
    public void writeSpeTable(Map<String,List<Integer>> speTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (Map.Entry<String,List<Integer>> entry: speTable.entrySet()){
            writer.write(entry.getKey()+"\t" + aryToString(entry.getValue()) +"\n");

        }
        writer.close();
    }
    public void writeSpeTableContinue(Map<String,List<Float>> speTable) throws IOException {
        File file = new File(this.fileOutputPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();

        }
        if (!file.isFile()) {
            file.createNewFile();

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileOutputPath));

        for (Map.Entry<String,List<Float>> entry: speTable.entrySet()){
            writer.write(entry.getKey()+"\t" + aryToString2(entry.getValue()) +"\n");

        }
        writer.close();
    }


}