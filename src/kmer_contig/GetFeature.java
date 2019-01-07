package kmer_contig;

import io.FileInput;
import io.FileOutput;
import io.ParserSeq;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GetFeature {
    private String seqPath;
    private String indexPath;
    private String writeFeaturePath;
    private Map<String,String> contigsMap;
    private Map<String,List<Integer>> feature;
    private int k;
    public GetFeature(String seqPath,
                      String indexPath,
                      String writeFeaturePath,
                      int k){
        this.seqPath = seqPath;
        this.indexPath = indexPath;
        this.writeFeaturePath = writeFeaturePath;
        this.k = k;
    }

    public Set<String> getKmers(String seq) {
        Set<String> allKmers = new HashSet<>();
        int len= seq.length();
        for (int i = 0; i <= len - k; i++){
            String kmer = seq.substring(i,i+k);
            allKmers.add(kmer);
        }
        return allKmers;
    }

    public void createContig(){
        SeqContig foo = new SeqContig();
        foo.contigTable(this.seqPath,k);
        this.contigsMap = foo.getContigsMap();
    }

    public Set<String> matchContig(String seqs){
        Set<String> eachContigs = new HashSet<>();
        Set<String> kmerSet = getKmers(seqs);
        for (String eachKmer:kmerSet){
            if (this.contigsMap.containsKey(eachKmer))
                eachContigs.add(this.contigsMap.get(eachKmer));
        }
        return eachContigs;


    }

    public int[] matchIndexTable(Set<String> contigs){
        int[] arr = new int[72];
        List<int[]> match = new ArrayList<>();
        File[] files = FileInput.getFiles(this.indexPath);
        // get feature
        for (String line:contigs){
            for (int i = 0; i < files.length; i++) {
                Map<String,int[]> eachIndex = FileInput.readIndex(files[i].getAbsolutePath());
                if (eachIndex.containsKey(line))
                    match.add(eachIndex.get(line));
            }

        }
        // combine feature
        if (!match.isEmpty()){
            for (int[] x:match){
                for (int i = 0; i < x.length; i++) {
                    arr[i] = arr[i] + x[i];

                }
            }
        }
        return arr;
    }

    private List<Integer> transArrtoList(int[] arr){
        List<Integer> tem = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            tem.add(arr[i]);

        }
        return tem;
    }


    public void eachContig(){
        ParserSeq seq = new ParserSeq(this.seqPath);
        for (int i = 0; i < seq.size(); i++) {
            String seqName = seq.getDescription(i);
            String seqs = seq.getSequence(i);
            Set<String> contigs = matchContig(seqs);
            int [] match  = matchIndexTable(contigs);
            this.feature.put(seqName,transArrtoList(match));
        }
        
    }
    
    public void writeFeature() throws IOException {
        FileOutput out = new FileOutput(this.writeFeaturePath);
        out.writeSpeTable(this.feature);
        
    }


}
