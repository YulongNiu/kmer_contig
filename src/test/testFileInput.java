package test;

import io.FileInput;

import java.io.File;

public class testFileInput {
    public static void main(String[] args) {
//        File[] files = FileInput.getFiles("/home/yangfang/PPFeature/kmer_profile/abb_seqs");
//        for (File tem:files){
//            System.out.println(tem.getAbsolutePath());
//        }

        FileInput.readIndex("/home/yangfang/PPFeature/kmer_profile/contig_idx/test.txt");



    }
}
