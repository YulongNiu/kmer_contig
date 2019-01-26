package kmer_contig;

import fuzzyHash.FuzzyHashMap;
import io.FileInput;
import io.FileOutput;
import utils.Levenshtein;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateSpeciesTable {

    private String contigPath;
    private String speFilePath;
    private String speContigIndexPath;
    private Map<String, List<Integer>> contigTable;
    private FuzzyHashMap contigTableFuzzy;
    private List<String> speName = new ArrayList<>();
    private String model;
    //    private SeqContig foo;
    public CreateSpeciesTable(String contigPath,
                              String speFilePath,
                              String speContigIndexPath,
                              String model) {
        this.contigPath = contigPath;
        this.speFilePath = speFilePath;
        this.speContigIndexPath = speContigIndexPath;
        this.model = model;
        File file = new File(speContigIndexPath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
//        this.foo = new SeqContig();
    }

    public void readContig(String path) {
        Map<String, List<Integer>> contigTable = FileInput.readContig(path);
        this.contigTable = contigTable;
    }

    public void readContigFuzzy(String path){
        FuzzyHashMap contigTable = FileInput.readContigFuzzy(path);
        this.contigTable = contigTable;
    }

    private void fuzzyMatch(FuzzyHashMap contigs,int f) {
        for (Map.Entry<String, List<Integer>> entry : this.contigTable.entrySet()) {
            if (contigs.containsFuzzyKey(entry.getKey(),f)) {
                entry.getValue().add(1);
            } else {
                entry.getValue().add(0);
            }
        }
    }

    private void exactMatch(Set<String> contigs) {
        for (Map.Entry<String, List<Integer>> entry : this.contigTable.entrySet()) {
            if (contigs.contains(entry.getKey())) {
                entry.getValue().add(1);
            } else {
                entry.getValue().add(0);
            }

        }


    }

    public void createSpeEach(int k, int f) throws IOException {
        File[] files = FileInput.getFiles(contigPath);
        for (int i = 0; i < files.length; i++) {
            System.out.println("Start " + files[i].getAbsolutePath());

            if (f == 1){
                readContigFuzzy(files[i].getAbsolutePath());
            }else {
                readContig(files[i].getAbsolutePath());
            }
            createSpe(k, f);
            String w_path = speContigIndexPath + files[i].getName();
            writeSpeTable(w_path);
        }


    }

    public void createOne(int k) throws IOException {
        File file = new File(contigPath);
        System.out.println("Start " + file.getAbsolutePath());
        readContig(contigPath);
        createSpe(k, 0);
        String w_path = speContigIndexPath + file.getName();
        writeSpeTable(w_path);

    }

    public void createSpe(int k, int f) {

        File[] files = FileInput.getFiles(speFilePath);
        SeqContig foo = new SeqContig(this.model);
        for (int i = 0; i < files.length; i++) {
            System.out.println(String.valueOf(i) + ": " + files[i].getName());
            String name = files[i].getName();

            String subName = name.substring(0, name.length()-6);
            this.speName.add(subName);

            foo.contigTable(files[i].getAbsolutePath(), k);


            if (f == 0) {
                Set<String> contigs = foo.getContigs();
                exactMatch(contigs);

            } else {
                FuzzyHashMap contigs = foo.getContigsMapFuzzy();
                fuzzyMatch(contigs,f);
            }
        }


    }

    public void writeSpeTable(String writePath) throws IOException {
        FileOutput file = new FileOutput(writePath);
        file.writeSpeTable(this.contigTable);
    }

    public void writeSpeName(String speNamePath) throws IOException {
        FileOutput file = new FileOutput(speNamePath);
        file.writeSpeName(this.speName);
    }


}
