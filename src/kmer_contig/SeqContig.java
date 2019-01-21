package kmer_contig;

import fuzzyHash.FuzzyHashMap;
import io.FileInput;

import java.io.File;
import java.util.*;

public class SeqContig {

    private Set<String> contigs;
    private Map<String, String> contigsMap;
    private FuzzyHashMap contigsMapFuzzy = new FuzzyHashMap(FuzzyHashMap.PRE_HASHING_METHOD.FIRST_4, FuzzyHashMap.FUZZY_MATCHING_ALGORITHM.LEVENSHTEIN);
    private String[] s = {"A", "C", "D", "E", "F",
            "G", "H", "I", "K", "L",
            "M", "N", "P", "Q", "R",
            "S", "T", "V", "W", "Y"};
    private int k;
    private dbg foo;

    public SeqContig(int k){
        this.k = k;
        dbg foo = new dbg(k, s);
        this.foo = foo;
    }

    public Set<String> getContigs() {
        return contigs;
    }

    public Map<String, String> getContigsMap() {
        return contigsMap;
    }

    public FuzzyHashMap getContigsMapFuzzy() {
        return contigsMapFuzzy;
    }

    public void contigTable(String speFile, int k) {

        List<String> filelist = FileInput.read(speFile);

        for (String tem : filelist) {
            foo.getKmer(tem);
        }
        Set<String> kmers = foo.getKmerSet();
        System.out.println(kmers.size());
        long startTime = System.currentTimeMillis();
        foo.allContigs();
        Map<String, String> contigs = foo.getContigs();
        this.contigsMap = contigs;
        Set<String> tem = new HashSet<>();
        for (Map.Entry<String, String> entry : contigs.entrySet()) {
            contigsMapFuzzy.putFuzzy(entry.getKey(), entry.getValue());
            tem.add(entry.getValue());
        }
        this.contigs = tem;
        System.out.println(tem.size());
        long endTime = System.currentTimeMillis();

//        foo.printContigs();
        System.out.println("Time:" + (endTime - startTime) / 1000);


    }
}
