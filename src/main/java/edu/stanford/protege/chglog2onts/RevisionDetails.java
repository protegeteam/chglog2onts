package edu.stanford.protege.chglog2onts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@AutoValue
public abstract class RevisionDetails {

    @JsonCreator
    public static RevisionDetails get(@JsonProperty("changes") ImmutableList<Change> changes) {
        return new AutoValue_RevisionDetails(changes);
    }

    public abstract ImmutableList<Change> getChanges();
}
