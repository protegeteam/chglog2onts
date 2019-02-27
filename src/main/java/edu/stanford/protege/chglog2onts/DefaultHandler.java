package edu.stanford.protege.chglog2onts;

import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentTarget;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import java.nio.file.Path;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
public class DefaultHandler implements ChangeLogHandler {

    private final OWLOntology ontology;

    private final Path outputDirectory;

    private int revisionCounter;

    public DefaultHandler(OWLOntology ontology, Path outputDirectory) {
        this.ontology = ontology;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public void startRevision() {
        revisionCounter++;
    }

    @Override
    public void endRevision() {
        try {
            // Save the ontology as it is
            var outputPath = outputDirectory.resolve("revision-" + revisionCounter + ".owl");
            var documentTarget = new FileDocumentTarget(outputPath.toFile());
            var documentFormat = new RDFXMLDocumentFormat();
            ontology.saveOntology(documentFormat, documentTarget);
        } catch(OWLOntologyStorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAxiom(OWLAxiom ax) {
        ontology.getOWLOntologyManager().addAxiom(ontology, ax);
    }

    @Override
    public void removeAxiom(OWLAxiom ax) {
        ontology.getOWLOntologyManager().removeAxiom(ontology, ax);
    }
}
