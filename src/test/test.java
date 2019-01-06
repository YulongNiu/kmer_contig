package test;

import io.FileOutput;
import utils.Levenshtein;

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
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(0);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        List<Integer> tem = new ArrayList<>();
        Map<String,List<Integer>> contigTable = new HashMap<>();
        contigTable.put("AAAA",new ArrayList<Integer>());
        contigTable.put("BBBB",new ArrayList<Integer>());
        contigTable.put("CCCC",new ArrayList<Integer>());
        Set<String> contigs = new HashSet<>();
        contigs.add("AAAA");
        contigs.add("BBBB");
        contigs.add("WWWW");
        contigs.add("ACCC");
        contigs.add("AAAD");
        contigs.add("VVVV");
        contigs.add("FFFFFFF");


        for (Map.Entry<String, List<Integer>> entry : contigTable.entrySet()) {
            int idx = 0;
            for (String eachContig : contigs) {
                int fuzzyScore = Levenshtein.distance(entry.getKey(), eachContig);
                if (fuzzyScore <= 1) {
                    idx =1;
                    break;
                }
            }
            entry.getValue().add(idx);
        }
        for (Map.Entry<String, List<Integer>> entry : contigTable.entrySet()) {
            System.out.println("key:" + entry.getKey()+ "\n" + "value:" +entry.getValue().toString());
        }
        FileOutput file = new FileOutput("/home/yangfang/PPFeature/kmer_profile/test_java/spetable.txt");
        file.writeSpeTable(contigTable);
    }
}
