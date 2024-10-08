package in.g77tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.g77tech.entity.UserDtlsEntity;

public interface UserDtlsRepository extends JpaRepository<UserDtlsEntity, Integer>{

	public UserDtlsEntity findByEmail(String email);
}
