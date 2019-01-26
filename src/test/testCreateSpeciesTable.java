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
        CreateSpeciesTable foo = new CreateSpeciesTable("/home/yangfang/PPFeature/kmer_profile/test_hy/k15_hy_index/",
                "/home/yangfang/PPFeature/kmer_profile/abb_seqs500/",
                "/home/yangfang/PPFeature/kmer_profile/test_hy/k15_hy_profile/_",
                "HY");
        foo.createSpeEach(15,0);
    }
}
