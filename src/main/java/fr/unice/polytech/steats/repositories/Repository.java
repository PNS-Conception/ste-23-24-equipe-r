package fr.unice.polytech.steats.repositories;

import java.util.Optional;

public interface Repository<T, ID> {

    Optional<T> findById(ID id);

    Iterable<T> findAll();
    // Returns the number of entities available.
    long count();

    // Deletes all entities managed by the repository.
    void deleteAll();

    // Deletes the entity with the given id.
    void deleteById(ID id);

    // Returns whether an entity with the given id exists.
    boolean existsById(ID id);

    // Saves a given entity through its id.
    <S extends T> void save(S entity, ID id);
}
