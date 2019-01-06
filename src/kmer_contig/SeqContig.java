package kmer_contig;

import io.FileInput;

import java.io.File;
import java.util.*;

public class SeqContig {

    private Set<String> contigs;

    public Set<String> getContigs() {
        return contigs;
    }

    public void contigTable(String speFile, int k){
        String[] s = {"A", "C", "D", "E", "F",
                "G", "H", "I", "K", "L",
                "M", "N", "P", "Q", "R",
                "S", "T", "V", "W", "Y"};
        dbg foo = new dbg(k, s);

        List<String> filelist = FileInput.read(speFile);

        for (String tem : filelist) {
            foo.getKmer(tem);
        }
        Set<String> kmers = foo.getKmerSet();
        System.out.println(kmers.size());
        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        Set<String> tem = new HashSet<>();
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            tem.add(entry.getValue());
        }
        this.contigs = tem;
        System.out.println(tem.size());
        long endTime = System.currentTimeMillis();

//        foo.printContigs();
        System.out.println("Time:" + (endTime - startTime) / 1000);


    }
}
