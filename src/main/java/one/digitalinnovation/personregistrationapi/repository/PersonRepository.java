package one.digitalinnovation.personregistrationapi.repository;

import one.digitalinnovation.personregistrationapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
