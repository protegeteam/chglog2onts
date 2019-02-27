package edu.stanford.protege.chglog2onts;

import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.functional.parser.FunctionalSyntaxParserFactory;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
public class ProcessChangeLog_TestCase {

    private ChangeLogProcessor processor;

    @Before
    public void setUp() {
        FunctionalSyntaxParserFactory factory = new FunctionalSyntaxParserFactory(FunctionalSyntaxParserFactory.DEFAULT_PREFIX);
        processor = new ChangeLogProcessor(factory);
    }

    @Test
    public void shouldProcessChangeLog() throws IOException, OWLOntologyCreationException {
        InputStream inputStream = getClass().getResourceAsStream("/example.json");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        var ontology = OWLManager.createOWLOntologyManager().createOntology();
        var outputDirectory = Path.of("/tmp/ontology-snapshots");
        Files.createDirectories(outputDirectory);
        var handler = new DefaultHandler(ontology, outputDirectory);
        processor.processChangeLog(bufferedInputStream, handler);
    }
}
