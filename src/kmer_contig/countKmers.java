package kmer_contig;

import java.io.*;
import java.util.Hashtable;

public class countKmers
{
    public static int k=3;									// scalar
    public static Hashtable<String, Integer> count = new Hashtable<String, Integer>();	// hash

    public static void countSeqKmers(String seq)
    {
        if(seq.length()>0)
        {
            int len=seq.length();
            for(int i=0;i<=len-k;i++)
            {
                String kmer=seq.substring(i,i+k);
                char[] revKmerArray = new char[kmer.length()];
                for (int j=0 ;j<k;j++)
                {
                    switch(kmer.charAt(j))
                    {
                        case 'A':
                        case 'a': revKmerArray[k-1-j] = 'T'; break;
                        case 'C':
                        case 'c': revKmerArray[k-1-j] = 'G'; break;
                        case 'G':
                        case 'g': revKmerArray[k-1-j] = 'C'; break;
                        case 'T':
                        case 't': revKmerArray[k-1-j] = 'A'; break;
                        default:  break;
                    }
                }

                String revKmer=new String(revKmerArray);
                if(kmer.compareTo(revKmer)>0) {  kmer=revKmer; }

                if(count.containsKey(kmer))
                {
                    int value=count.get(kmer);
                    value++;
                    count.put(kmer,value);
                }
                else
                {
                    count.put(kmer,1);
                }
            }
        }
    }

    public static void printKmers()
    {
        for (String kmer : count.keySet()) {
            System.out.println(kmer + " " + count.get(kmer));
        }
    }

    public static void main(String args[]) throws IOException
    {
        // initialize variables
        String seq="atcgaactc";				// scalar

        // parse command line arguments

        // read STDIN

                //process previous sequence
        countSeqKmers(seq);


        // process last sequence
        countSeqKmers(seq);

        // prink kmer hash
        printKmers();

    }
}
