package org.ontologyengineering.slic;

import java.io.File;
import java.util.Vector;
import java.util.Collection;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;

import org.junit.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class SlicTest {

    private String    file;
    static private String [] fnames = {
        //            "conjuctionTest",
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

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    public SlicTest (String fname) {
        this.file = fname;
    }

    @Parameters
    public static Collection<String[]> data() {
        Vector<String[]> v = new Vector<String[]>();
        for(String f: fnames) {
            v.add(new String[]{f});
        }
        return v;
    }


    @Test
    public void runAll() {
        String dirName = System.getProperty("slic.test.resources");
        try {
            String fname = dirName + File.separator + file;
            File dataFile = new File(fname + ".owl");
            String queryString = Slic.readStringFromFile(fname + ".sparql");
            ResultSet rs = Slic.doRunQuery(dataFile, queryString);
            Assert.assertEquals("Failure with " + file, 1, size(rs));
        } catch (Exception e) {
            Assert.assertTrue("Exception with " + file, false);
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
