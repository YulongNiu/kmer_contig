package kmer_contig;

import io.FileInput;
import io.FileOutput;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CreateContigIndex {
    private String spePath;
    private String outFilePath;
    private int k;
    private int max;
    private int splitNum;

    public CreateContigIndex(int k,
                             String spePath,
                             String outFilePath,
                             int splitNum,
                             int max){
        this.k = k;
        this.spePath = spePath;
        this.outFilePath = outFilePath;
        this.splitNum = splitNum;
        this.max = max;
    }

    public void create() throws IOException {
        String[] s = {"A", "C", "D", "E", "F",
                "G", "H", "I", "K", "L",
                "M", "N", "P", "Q", "R",
                "S", "T", "V", "W", "Y"};
        dbg foo = new dbg(k, s);
        List<String> allSeq = new ArrayList<>();
        File[] files = FileInput.getFiles(spePath);

        for (int i = 0; i < max; i++) {
            System.out.println(files[i].getName());
            allSeq.addAll(FileInput.read(files[i].getAbsolutePath()));

        }

        for (String tem : allSeq) {
            foo.getKmer(tem);
        }
        Set<String> kmers = foo.getKmerSet();

        System.out.println("Kmers: "+ String.valueOf(kmers.size()));

        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        Set<String> tem = new HashSet<>();
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            tem.add(entry.getValue());
        }
        FileOutput write = new FileOutput(outFilePath);
        write.writeContigSplit(tem,splitNum);
        System.out.println("Contigs: " + String.valueOf(tem.size()));

        long endTime = System.currentTimeMillis();

//        foo.printContigs();
        System.out.println("Time:" + (endTime - startTime) / 1000);


    }
}