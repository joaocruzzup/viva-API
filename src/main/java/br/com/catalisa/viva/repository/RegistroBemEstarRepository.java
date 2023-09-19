package br.com.catalisa.viva.repository;

import br.com.catalisa.viva.model.RegistroBemEstar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroBemEstarRepository extends JpaRepository<RegistroBemEstar, Long> {
}
