package com.devvision.series.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.devvision.series.app.model.Token;

public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {
	@Modifying
    @Query("UPDATE Token t SET t.active=false WHERE t.id=:id")
    public void disableAuthentication(@Param("id") Long id);
	
	@Modifying
	@Query(value = "SELECT * FROM tokens WHERE id_user=:id_user ORDER BY id DESC LIMIT 1", nativeQuery = true)
	public List<Token> findUserToken(@Param("id_user") Long id_user);
}
