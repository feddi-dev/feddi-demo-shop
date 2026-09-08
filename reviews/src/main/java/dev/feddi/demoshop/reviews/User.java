package dev.feddi.demoshop.reviews;

/**
 * Reviews' own view of the shared User entity — just enough to resolve
 * the "reviews" field this subgraph contributes. The rest of User (e.g. name)
 * comes from the users subgraph.
 */
public record User(String id) {
}
