package org.semanticweb.owlapi.functional.parser;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
public class FunctionalSyntaxParserFactory {

    public static final String DEFAULT_PREFIX = "http://datasets.protege.stanford.edu/chglog/";

    private final String defaultPrefix;

    public FunctionalSyntaxParserFactory(String defaultPrefix) {
        this.defaultPrefix = defaultPrefix;
    }

    public FunctionalSyntaxParser create() {
        return new FunctionalSyntaxParser(FunctionalSyntaxParserConfig.get(),
                                          defaultPrefix);
    }
}
