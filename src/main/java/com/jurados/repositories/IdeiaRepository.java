package com.jurados.repositories;

import com.jurados.entities.Ideia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeiaRepository extends JpaRepository<Ideia, Long> {
}
