package test;

import kmer_contig.GetFeature;

import java.io.IOException;

public class testGetFeature {
    public static void main(String[] args) throws IOException {
        GetFeature foo = new GetFeature("/home/yangfang/PPFeature/kmer_profile/contig_idx/seq/Gor2.fasta",
                "/home/yangfang/PPFeature/kmer_profile/contig_idx/72speIdx/",
                "/home/yangfang/PPFeature/kmer_profile/contig_idx/seq/Gor2_k6_test__.txt",
                6);
        foo.createContig();
        foo.eachContig(0);
//        foo.writeFeature();
    }
}
