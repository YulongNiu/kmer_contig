import kmer_contig.CreateSpeciesTable;

import java.io.IOException;

public class Profile {
    public static boolean run(String[] args) throws IOException {
        int argIdx = 0;
        String spePath = null;
        String contigPath = null;
        String outProfile = null;
        int k = 1;


        while (argIdx < args.length && args[argIdx].startsWith("-")) {
            String arg = args[argIdx++];
            if (arg.equals("-i"))
                spePath = args[argIdx++];
            else if (arg.equals("-c"))
                contigPath = args[argIdx++];
            else if (arg.equals("-o"))
                outProfile = args[argIdx++];
            else if (arg.equals("-k"))
                k = Integer.parseInt(args[argIdx++]);
            else {
                System.err.println("Unknown option: " + arg);
            }
        }
        CreateSpeciesTable foo = new CreateSpeciesTable(contigPath,
                spePath,
                outProfile);
        foo.createSpeEach(k);
        return true;
    }
}
