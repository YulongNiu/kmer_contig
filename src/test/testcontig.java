package test;

import kmer_contig.contig;

public class testcontig {
    public static void main(String[] args) {
        contig foo =  new contig(3,"/home/yangfang/PPFeature/kmer_profile/abb_seqs500/",4);
        foo.printCompress();
    }
}
