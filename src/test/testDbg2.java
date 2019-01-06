package test;

import kmer_contig.dbg;

public class testDbg2 {
    public static void main(String[] args) {
        String[] s = {"A","C","D","E","F",
                      "G","H","I","K","L",
                      "M","N","P","Q","R",
                      "S","T","V","W","Y"};
        dbg foo = new dbg(30,s);
        String seq = "MQSAGQDPWQFGSLGRNPSPGTLSGPIFPLPYDGSYSQAVYAPTSTWTERRARLAKEPLCRATNPTRSGHGAVERRLQQPQPQQPQQTGPTSMWDGYSTAAPPAATTRYTRGNQGTMANSGSSAFMGQGPFTTSGIAGASPRGTKRGASPSSFVEDEGHDYSEEEDGQEWAEDVYDDGAEYEEDYIDEQGSESDDDDAQDDRLFSRPSERRQTSPGFMRAALLEAQRGRQDMWLLPPAPTSSLHLPSPPRGSPAVELDAFGQPLFGSRSLRRSPTVRSPQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQVLPAVCRAIQDGTATGRDVNRVLGAAKLAKGEEPSLDEPKGM";
        String seq2 = "CCCDDEEFF";
        String seq3 = "SSTTCCVVWY";

        foo.getKmer(seq);
//        foo.getKmer(seq2);
//        foo.getKmer(seq3);

//        foo.printKmer();

        foo.allContigs();
//        foo.printContigs();
    }
}
