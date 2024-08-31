package example.br.cnpjsearchapi.repositories;

import example.br.cnpjsearchapi.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    //busca por nome
    Optional<Cidade> findByNome(String nome);
}
