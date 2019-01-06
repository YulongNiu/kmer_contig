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
    private Map<String,List<Integer>> contigTable;
    public CreateSpeciesTable(String contigPath, String speFilePath){
        this.contigPath = contigPath;
        this.speFilePath = speFilePath;
    }
    public void readContig(){
        Map<String, List<Integer>> contigTable = FileInput.read_contig(this.contigPath);
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


    public void createSpe(int k){
        SeqContig foo = new SeqContig();
        File[] files = FileInput.getFiles(speFilePath);

        for (int i = 0; i < files.length; i++) {

            foo.contigTable(files[i].getAbsolutePath(),k);
            Set<String> contigs = foo.getContigs();
            fuzzyMatch(contigs);
        }



    }

    public void writeSpeTable(String writePath) throws IOException {
        FileOutput file = new FileOutput(writePath);
        file.writeSpeTable(this.contigTable);
    }

}
