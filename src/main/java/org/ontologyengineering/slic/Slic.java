package org.ontologyengineering.slic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.*;
import org.apache.commons.io.*;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Slic {
    public static void main(String [] args) {
        Option query    = OptionBuilder.isRequired()
                                       .withArgName( "query" )
                                       .hasArg()
                                       .withDescription(  "use given query as SPARQL query" )
                                       .create( "query");
        Option data     = OptionBuilder.isRequired()
                                      .withArgName( "data" )
                                      .hasArg()
                                      .withDescription(  "run query over this data file" )
                                      .create( "data");
        Options options = new Options();
        options.addOption(query);
        options.addOption(data);

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse( options, args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "slic", options );
            System.exit(0);
        }

        // Get the query & data
        String queryString = null;
        try {
            readStringFromFile(cmd.getOptionValue("query"));
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        File dataFile = new File(cmd.getOptionValue("data"));

        // Run the query
        ResultSet rs = doRunQuery(dataFile, queryString);
        // Print the ResultSet
        ResultSetFormatter.out(System.out, rs);
    }

    /* package */ static ResultSet doRunQuery(File ontologyFile, String queryString) {
        OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        ontology.read(ontologyFile.toURI().toString());

        Query qs1         = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(qs1, ontology);
        return qe.execSelect();
    }

    /* package */ static String readStringFromFile(String fname) throws IOException {
        File file = new File(fname);
        try {
            return FileUtils.readFileToString(file);
        } catch (IOException e) {
            throw e;
        }
    }
}
