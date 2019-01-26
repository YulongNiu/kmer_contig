package test;

import kmer_contig.contig;

public class testcontig {
    public static void main(String[] args) {
        contig foo =  new contig(15,"/home/yangfang/PPFeature/kmer_profile/abb_seqs_class/",100);
        foo.printCompress();
    }
}
