**kmer_contig.jar** quickly constricting contig profile by kmer method.

# Method:
* Compress: Compressing AA sequence to contig.
 
 ## options
 
 | option |  Description                                                                                                                            |
 |:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
 |  -i     |  Species AA file path.                                                                                                         |
 |  -k     |  Setting kmer parameter value k.                                                                                                               |
 |  -m     |  Max add species number.                                                                                 |

## Usage:

```angular2html
java -Xmx9192m -jar kmer_contig.jar Compress -i /home/yangfang/PPFeature/kmer_profile/abb_seqs_class/ -k 6 -m 60
```
 
# Method:
* Index: Constructing contig index.
 
## options
  
  | option |  Description                                                                                                                            |
  |:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
  |  -i     |  Species AA file path.                                                                                                         |
  |  -o     |  Setting store path.                                                                                                               |
  |  -k     |  setting kmer parameter value k.                                                                                                               |
  |  -s     |  Max split file number.                                                                                 |
  |  -m    |  Max add species number.                                                      |
  |  -d     |  Choose profile model (AA,CH,PO,CHP,HY)                                                              |
  |  --hcp  |  HCP (highly conserved protein) method (default).                                                                                        |
  |  --ehcp |  HCP method with extended HCP sequences.                                                                                               |
  |  --srna |  SSU method.                                                                       |
  |  --esrna|  SSU rRNA method with extended SSU rRNA sequences.                                                                                                           |
  
## Usage:

```angular2html
java -Xmx10190m -jar kmer_contig.jar Index -i /home/yangfang/PPFeature/kmer_profile/test_jar/seq/ -k 19 -s 1 -m 4 -o /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_index/k19_ch_split.txt -d CH
```



# Method:
* Profile: Constructing phylogenetic profile by kmer contig.
   
## options
    
| option |  Description                                                                                                                            |
|:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
|  -i     |  Species AA file path.                                                                                                         |
|  -o     |  Setting store path.                                                                                                               |
|  -k     |  setting kmer parameter value k.                                                                                                               |
|  -c     |  The path of contig index.                                                                                 |
|  -d     |  Choose profile model (AA,CH,PO,CHP,HY)                                                              |

## Usage:

```angular2html
java -Xmx10190m -jar kmer_contig.jar Profile -i /home/yangfang/PPFeature/kmer_profile/abb_seqs500/ -k 19 -c /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_index/ -o /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_profile/ -d CH

```

# Method:
* Feature: Extracting profile feature form kmer congtig profile.
   
## options
    
| option |  Description                                                                                                                            |
|:------- |:---------------------------------------------------------------------------------------------------------------------------------------|
|  -i     |  Species AA file path.                                                                                                         |
|  -o     |  Setting store path.                                                                                                               |
|  -k     |  setting kmer parameter value k.                                                                                                               |
|  -p     |  The profile path.                                                                                 |
|  -d     |  Choose profile model (AA,CH,PO,CHP,HY)                                                              |


## Usage:

```angular2html
java -Xmx10190m -jar kmer_contig.jar Feature -i /home/yangfang/PPFeature/kmer_profile/test_jar/seq_all.fasta -k 19 -p /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_profile/k19_ch_split0.txt -o /home/yangfang/PPFeature/kmer_profile/test_hy/k19_ch_feature/combine_contig.txt -d CH
```