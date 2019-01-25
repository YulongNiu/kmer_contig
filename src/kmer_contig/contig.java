package kmer_contig;

import java.io.*;
import java.util.*;
import io.FileInput;
public class contig {
    private int k;
    private String spePath;
    private int maxIter;

    public contig(int k, String spePath,int maxIter){
        this.k = k;
        this.spePath = spePath;
        this.maxIter = maxIter;
    }

    private   void searchContigs(List<String> allSeq){
        String[] s = {"A", "C", "D", "E", "F",
                "G", "H", "I", "K", "L",
                "M", "N", "P", "Q", "R",
                "S", "T", "V", "W", "Y"};
        dbg foo = new dbg(k, s);
        for (String tem : allSeq) {
            foo.getKmer(tem);
        }
        Set<String> kmers = foo.getKmerSet();
        System.out.print(String.valueOf(kmers.size())+",");
//        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        Set<String> tem = new HashSet<>();
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            tem.add(entry.getValue());
        }
        System.out.print(String.valueOf(tem.size())+",");

        System.out.println(String.valueOf(allSeq.size()));
//        long endTime = System.currentTimeMillis();

//        foo.printContigs();
//        System.out.println("Time:" + (endTime - startTime) / 1000);


    }


    public void printCompress(){
        File[] files = FileInput.getFiles(spePath);
        for (int i = 1; i <= maxIter; i++) {
            System.out.print(String.valueOf(i)+",");
            List<String> allSeq = new ArrayList<>();
//            System.out.println(allSeq.size());
            for (int j = 0; j < i; j++) {
//                System.out.println(files[j].getName());
                allSeq.addAll(FileInput.read(files[j].getAbsolutePath()));
            }
//            System.out.println(allSeq.size());
            searchContigs(allSeq);
        }











    }


}
