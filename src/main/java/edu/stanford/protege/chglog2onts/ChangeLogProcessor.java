package edu.stanford.protege.chglog2onts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.change.RemoveAxiomData;
import org.semanticweb.owlapi.functional.parser.FunctionalSyntaxParserFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
public class ChangeLogProcessor {

    private final FunctionalSyntaxParserFactory parserFactory;

    public ChangeLogProcessor(FunctionalSyntaxParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    public void processChangeLog(@Nonnull InputStream inputStream,
                                 @Nonnull ChangeLogHandler handler) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new GuavaModule());
        ChangeLog chgLog = objectMapper.readValue(inputStream, ChangeLog.class);
        System.out.println(chgLog);

        var revisionDetailsList = chgLog.getRevisionDetails();
        for(var revisionDetails : revisionDetailsList) {
            handler.startRevision();
            var changes = revisionDetails.getChanges();
            for(Change change : changes) {
                if(change.getAxiom().isPresent()) {
                    String axiom = change.getAxiom().get();
                    var ax = parserFactory.create().parseAxiom(axiom);
                    if(change.getOperation() == ChangeOperation.ADD) {
                        handler.addAxiom(ax);
                    }
                    else {
                        handler.removeAxiom(ax);
                    }
                }

            }
            handler.endRevision();
        }
    }

    private OWLOntologyChangeData toChangeData(@Nonnull Change change) {
        return change.getAxiom()
                .map(ax -> parserFactory.create().parseAxiom(ax))
                .map(ax -> {
                    if(change.getOperation() == ChangeOperation.ADD) {
                        return new AddAxiomData(ax);
                    }
                    else {
                        return new RemoveAxiomData(ax);
                    }
                })
                .orElse(null);
    }

}
