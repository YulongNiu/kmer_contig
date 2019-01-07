package kmer_contig;

import io.FileInput;
import io.FileOutput;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CreateContigIndex {
    public static void main(String[] args) throws IOException {


        String[] s = {"A", "C", "D", "E", "F",
                "G", "H", "I", "K", "L",
                "M", "N", "P", "Q", "R",
                "S", "T", "V", "W", "Y"};
        dbg foo = new dbg(6, s);
        List<String> allSeq = new ArrayList<>();
        File[] files = FileInput.getFiles("/home/yangfang/PPFeature/kmer_profile/contig_idx/abb_idx_seq/");

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
            allSeq.addAll(FileInput.read(files[i].getAbsolutePath()));

        }

        for (String tem : allSeq) {
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
        FileOutput write = new FileOutput("/home/yangfang/PPFeature/kmer_profile/contig_idx/idx_k6.txt");
        write.writeContigSplit(tem,10);
        System.out.println(tem.size());

        long endTime = System.currentTimeMillis();

//        foo.printContigs();
        System.out.println("Time:" + (endTime - startTime) / 1000);
    }
}