package test;

import io.FileOutput;
import utils.Levenshtein;

import javax.swing.text.html.parser.Parser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class test {
    private static String aryToString(List<Integer> ary){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ary.size(); i++){
            sb.append(ary.get(i)).append(",");
        }

        sb.deleteCharAt(sb.length() -1);

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        Set<String> set1 = new HashSet<>();
        set1.add("A");
        set1.add("B");
        set1.add("C");
        set1.add("D");
        set1.add("E");
        set1.add("F");
        set1.add("G");
        set1.add("H");
        set1.add("J");
        int each = set1.size()/4;
//        System.out.println(set1.size()/4);
        List<String> allSplite = new ArrayList<>();

        for (String line:set1){
            allSplite.add(line);
        }

        //      split all task
        List<Integer> allIndex = new ArrayList<>();
        int splitNum = 2;
        int length = allSplite.size();
        int tl = length % splitNum == 0 ? length / splitNum : (length
                / splitNum + 1);
        for (int i = 0; i < length ; i++) {
            allIndex.add(i);
        }
//        for (int i = 0; i < splitNum; i++) {
//            String w_files = "/home/yangfang/PPFeature/kmer_profile/contig_idx/" +"idx"+ String.valueOf(i)+".txt";
//            BufferedWriter writer = new BufferedWriter(new FileWriter(w_files));
//            int end = (i + 1) * tl;
//            final List<Integer> list1 = allIndex.subList(i * tl, end > length ? length : end);
//            for (int j:list1){
//                writer.write(allSplite.get(j)+"\n");
//            }
//            writer.close();
//        }
        String w_files = "/home/yangfang/PPFeature/kmer_profile/contig_idx/idx.txt";
        String[] tem = w_files.split("/");
        String last = tem[tem.length-1];
        String[] tem2 = last.split("\\.");
        String name = tem2[0];
        System.out.println(name);
        File file = new File(w_files);
        System.out.println(file.getName());
        System.out.println(file.getParentFile());

    }
}
