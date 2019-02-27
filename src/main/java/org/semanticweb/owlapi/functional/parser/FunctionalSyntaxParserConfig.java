package org.semanticweb.owlapi.functional.parser;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
public class FunctionalSyntaxParserConfig {

    private final OWLOntologyManager ontologyManager;

    private final OWLOntology dummyOntology;

    private FunctionalSyntaxParserConfig(OWLOntologyManager ontologyManager,
                                         OWLOntology dummyOntology) {
        this.ontologyManager = ontologyManager;
        this.dummyOntology = dummyOntology;
    }

    @Nonnull
    public static FunctionalSyntaxParserConfig get() {
        try {
            OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
            OWLOntology dummyOntology = ontologyManager.createOntology();
            return new FunctionalSyntaxParserConfig(ontologyManager, dummyOntology);
        } catch(OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public OWLOntology getDummyOntology() {
        return dummyOntology;
    }
}
