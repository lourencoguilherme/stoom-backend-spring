package br.com.gml.repository;

/**
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends
        JpaSpecificationExecutor<T>,
        JpaRepository<T, ID> {
}
