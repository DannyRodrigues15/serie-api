package com.devvision.series.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.devvision.series.app.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	@Modifying
	@Query(value = "INSERT INTO user_has_groups (id_user, id_group) values (:id_user, :id_group)", 
		nativeQuery = true)
	public void insertPermission(@Param("id_user") Long id_user, @Param("id_group") Long id_group);
	
	@Modifying
	@Query(value = "DELETE FROM user_has_groups where id_user=:id_user AND id_group=:id_group", 
		nativeQuery = true)
	public void deletePermission(@Param("id_user") Long id_user, @Param("id_group") Long id_group);
	
	@Modifying
    @Query("UPDATE User u SET u.password=:password WHERE u.id=:id")
    public void changePasswordUser(@Param("id") Long id, @Param("password") String password);
	
	@Query(value="SELECT u FROM User u WHERE u.person.email =:email") //INNER JOIN c.person WHERE p.email=:email
	public List<User> findUserByEmail(@Param("email") String email);
	
	@Query(value="SELECT u FROM User u WHERE u.id =:id_user") //INNER JOIN c.person WHERE p.email=:email
	public List<User> findUserPassword(@Param("id_user") Long id_user);
}
