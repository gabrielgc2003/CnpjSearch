package example.br.cnpjsearchapi.repositories;

import example.br.cnpjsearchapi.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repositório para Empresa
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    Optional<Empresa> findByCnpj(String cnpj);
}
