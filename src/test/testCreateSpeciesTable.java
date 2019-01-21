package test;

import kmer_contig.CreateContigIndex;
import kmer_contig.CreateSpeciesTable;

import java.io.IOException;

public class testCreateSpeciesTable {
    public static void main(String[] args) throws IOException {
//        CreateSpeciesTable foo = new CreateSpeciesTable("/home/yangfang/PPFeature/kmer_profile/test_java/out.txt",
//                "/home/yangfang/PPFeature/kmer_profile/test");
//        foo.readContig();
//        foo.createSpe(6);
//        foo.writeSpeTable("/home/yangfang/PPFeature/kmer_profile/test_java/spetable.txt");
        CreateSpeciesTable foo = new CreateSpeciesTable("/home/yangfang/PPFeature/kmer_profile/contig_idx/idx_k30/",
                "/home/yangfang/PPFeature/kmer_profile/abb_seqs_class/",
                "/home/yangfang/PPFeature/kmer_profile/contig_idx/72speIdx_k30/");
//        foo.createSpeEach(30,0);
    }
}
