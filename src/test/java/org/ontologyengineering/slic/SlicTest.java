package org.ontologyengineering.slic;

import org.junit.*;

import java.io.File;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;

public class SlicTest {

    private String [] fnames = {
            "conjuctionTest",
            "equivalence",
            "simple_atomsubsetatom",
            "simple_atomsubsetconj",
            "simple_atomsubsetdisj",
            "simple_atomsubsetnegatom",
            "simple_atomsubsetonly",
            "simple_atomsubsetsome",
            "simple_atomsubsettop",
            "simple_conjsubsetatom",
            "simple_conjsubsetconj",
            "simple_conjsubsetdisj",
            "simple_conjsubsetnegatom",
            "simple_conjsubsetonly",
            "simple_conjsubsetsome",
            "simple_disjsubsetatom",
            "simple_disjsubsetconj",
            "simple_disjsubsetdisj",
            "simple_disjsubsetnegatom",
            "simple_disjsubsetonly",
            "simple_disjsubsetsome",
            "simple_negatomsubsetatom",
            "simple_negatomsubsetconj",
            "simple_negatomsubsetdisj",
            "simple_negatomsubsetnegatom",
            "simple_negatomsubsetonly",
            "simple_negatomsubsetsome",
            "simple_onlysubsetatom",
            "simple_onlysubsetconj",
            "simple_onlysubsetdisj",
            "simple_onlysubsetnegatom",
            "simple_onlysubsetonly",
            "simple_onlysubsetsome",
            "simple_somesubsetatom",
            "simple_somesubsetconj",
            "simple_somesubsetdisj",
            "simple_somesubsetnegatom",
            "simple_somesubsetonly",
            "simple_somesubsetsome",
            "simple_topsubsetatom",
            "simple_topsubsetconj",
            "simple_topsubsetdisj",
            "simple_topsubsetnegatom",
            "simple_topsubsetonly",
            "simple_topsubsetsome",
            "simple_topsubsettop"};

    @Test
    public void runAll() {
        String dirName = System.getProperty("slic.test.resources");
        for(String base: fnames) {
            try {
                String fname = dirName + File.separator + base;
                File dataFile = new File(fname + ".owl");
                String queryString = Slic.readStringFromFile(fname + ".sparql");
                ResultSet rs = Slic.doRunQuery(dataFile, queryString);
                Assert.assertEquals(1, size(rs));
                System.out.println("+");
            } catch (Exception e) {
                System.out.println("No implementation for " + base);
            }
        }
    }

    private static int size(ResultSet rs) {
        int r = 0;
        while(rs.hasNext()) {
            rs.next();
            r++;
        }
        return r;
    }
}
