package edu.stanford.protege.chglog2onts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Change {

    @JsonCreator
    public static Change get(@JsonProperty("operation") ChangeOperation operation,
                             @Nullable @JsonProperty("axiom") String axiom,
                             @Nullable @JsonProperty("annotation") String annotation) {
        return new AutoValue_Change(operation, Optional.ofNullable(axiom), Optional.ofNullable(annotation));
    }

    public abstract ChangeOperation getOperation();

    public abstract Optional<String> getAxiom();

    public abstract Optional<String> getAnnotation();

}
