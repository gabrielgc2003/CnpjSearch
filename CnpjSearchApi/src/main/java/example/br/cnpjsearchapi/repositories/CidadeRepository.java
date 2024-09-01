package example.br.cnpjsearchapi.repositories;

import example.br.cnpjsearchapi.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositório para Cidade
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    // Busca cidade por nome
    Optional<Cidade> findByNome(String nome);
}
