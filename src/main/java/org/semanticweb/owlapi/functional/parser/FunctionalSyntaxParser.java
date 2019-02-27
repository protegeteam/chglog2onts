package org.semanticweb.owlapi.functional.parser;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.StringReader;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
public class FunctionalSyntaxParser {

    private FunctionalSyntaxParserConfig config;

    private String defaultPrefix;

    public FunctionalSyntaxParser(FunctionalSyntaxParserConfig config,
                                  String defaultPrefix) {
        this.config = config;
        this.defaultPrefix = defaultPrefix;
    }

    public OWLAxiom parseAxiom(String axiom) {
        var stringReader = new StringReader(axiom);
        var parser = new OWLFunctionalSyntaxParser(new CustomTokenizer(stringReader));
        var ontology = config.getDummyOntology();
        parser.setUp(ontology, new OWLOntologyLoaderConfiguration());
        DefaultPrefixManager prefixManager = new DefaultPrefixManager();
        prefixManager.setDefaultPrefix(defaultPrefix);
        parser.setPrefixes(prefixManager);
        return parser.Axiom();
    }
}
