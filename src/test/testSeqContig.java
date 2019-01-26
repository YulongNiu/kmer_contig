package test;

import io.FileInput;
import kmer_contig.SeqContig;

import java.io.File;
import java.util.Set;

public class testSeqContig {
    public static void main(String[] args) {
        File[] files = FileInput.getFiles("/home/yangfang/PPFeature/kmer_profile/abb_seqs_class/");
        SeqContig foo = new SeqContig("AA");
        for (int i = 0; i < files.length; i++) {
            System.out.println(String.valueOf(i) + ": " + files[i].getName());
            foo.contigTable(files[i].getAbsolutePath(),6);
            Set<String> contigs = foo.getContigs();

        }

    }

}
