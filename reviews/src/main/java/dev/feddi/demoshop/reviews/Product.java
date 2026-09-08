package dev.feddi.demoshop.reviews;

/**
 * Reviews' own view of the shared Product entity — just enough to resolve
 * the "reviews" field this subgraph contributes. The rest of Product (e.g. name)
 * comes from the products subgraph.
 */
public record Product(String id) {
}
