package com.example.javatoo.challenge;

public final class PairCharCount<V, C> {
    final V character;
    final C occurrences;

    public PairCharCount(V character, C occurrences) {
        this.character = character;
        this.occurrences = occurrences;
    }

    static <V, C> PairCharCount<V, C> of(V character, C occurrences) {
        return new PairCharCount<>(character, occurrences);
    }

    @Override
    public int hashCode() {
        return character.hashCode() ^ character.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }

        PairCharCount obj = (PairCharCount) o;
        return this.character.equals(obj.character)
                && this.occurrences.equals(obj.occurrences);
    }
}
