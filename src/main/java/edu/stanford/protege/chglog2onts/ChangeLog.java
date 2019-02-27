package edu.stanford.protege.chglog2onts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-02-27
 */
@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ChangeLog {

    private boolean inTrash;

    private int totalRevisions;

    @JsonCreator
    public static ChangeLog get(@JsonProperty("inTrash") boolean inTrash,
                     @JsonProperty("totalRevisions") int totalRevisions,
                                @JsonProperty("revisionDetails") ImmutableList<RevisionDetails> revisionDetails) {

        return new AutoValue_ChangeLog(inTrash, totalRevisions, revisionDetails);
    }

    public abstract boolean isInTrash();


    public abstract int getTotalRevisions();

    public abstract ImmutableList<RevisionDetails> getRevisionDetails();

}
