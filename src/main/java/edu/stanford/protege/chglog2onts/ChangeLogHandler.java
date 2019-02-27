package edu.stanford.protege.chglog2onts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ChangeLogHandler {

    void startRevision();

    void endRevision();

    void addAxiom(OWLAxiom ax);

    void removeAxiom(OWLAxiom ax);
}
