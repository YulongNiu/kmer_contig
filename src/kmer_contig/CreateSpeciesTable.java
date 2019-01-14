package kmer_contig;

import io.FileInput;
import io.FileOutput;
import utils.Levenshtein;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateSpeciesTable {

    private String contigPath;
    private String speFilePath;
    private String speContigIndexPath;
    private Map<String,List<Integer>> contigTable;
//    private SeqContig foo;
    public CreateSpeciesTable(String contigPath,
                              String speFilePath,
                              String speContigIndexPath){
        this.contigPath = contigPath;
        this.speFilePath = speFilePath;
        this.speContigIndexPath = speContigIndexPath;
        File file = new File(speContigIndexPath);
        if (!file.isDirectory()){
            file.mkdirs();
        }
//        this.foo = new SeqContig();
    }
    public void readContig(String path){
        Map<String, List<Integer>> contigTable = FileInput.readContig(path);
        this.contigTable = contigTable;
    }

    private void fuzzyMatch(Set<String> contigs) {
        for (Map.Entry<String, List<Integer>> entry : this.contigTable.entrySet()) {
            int idx = 0;
            for (String eachContig : contigs) {
                int fuzzyScore = Levenshtein.distance(entry.getKey(), eachContig);

                if (fuzzyScore <= 1) {
                    idx =1;
                    break;
                }
            }
            System.out.println(idx);
            entry.getValue().add(idx);
        }
    }
    private void exactMatch(Set<String> contigs){
        for (Map.Entry<String, List<Integer>> entry : this.contigTable.entrySet()) {
            if (contigs.contains(entry.getKey())){
                entry.getValue().add(1);
            }else {
                entry.getValue().add(0);
            }

        }



    }
    public void createSpeEach(int k) throws IOException {
        File[] files = FileInput.getFiles(contigPath);
        for (int i = 0; i < files.length; i++) {
            System.out.println("Start " + files[i].getAbsolutePath() );
            readContig(files[i].getAbsolutePath());
            createSpe(k);
            String w_path = speContigIndexPath + files[i].getName();
            writeSpeTable(w_path);
        }


    }

    public void createOne(int k) throws IOException {
        File file = new File(contigPath);
        System.out.println("Start " + file.getAbsolutePath());
        readContig(contigPath);
        createSpe(k);
        String w_path = speContigIndexPath + file.getName();
        writeSpeTable(w_path);

    }

    public void createSpe(int k){

        File[] files = FileInput.getFiles(speFilePath);
        SeqContig foo = new SeqContig();
        for (int i = 0; i < files.length; i++) {
            System.out.println(String.valueOf(i) + ": " + files[i].getName());
            foo.contigTable(files[i].getAbsolutePath(),k);
            Set<String> contigs = foo.getContigs();
//            fuzzyMatch(contigs);
            exactMatch(contigs);

        }



    }

    public void writeSpeTable(String writePath) throws IOException {
        FileOutput file = new FileOutput(writePath);
        file.writeSpeTable(this.contigTable);
    }

}
