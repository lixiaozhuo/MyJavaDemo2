package com.lixiaozhuo._02_repository.dao;


import java.util.List;


import com.lixiaozhuo._02_repository.pojo.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;


/**
 * Repository接口DAO
 */
public interface UsersDao extends Repository<Users, Integer> {
	//方法名称命名规则
	List<Users> findByUsernameIs(String string);
	List<Users> findByUsernameLike(String string);
	List<Users> findByUsernameAndUserAgeGreaterThanEqual(String name, Integer age);
	///////////////////////////////////////////////////////////////////////////////
	//使用@Query注解查询
	@Query(value="from Users where username = ?")
	List<Users> queryUserByNameUseJPQL(String name);
	
	@Query("from Users where username like ?")
	List<Users> queryUserByLikeNameUseJPQL(String name);
	
	@Query("from Users where username = ? and userage >= ?")
	List<Users> queryUserByNameAndAge(String name, Integer age);
	///////////////////////////////////////////////////////////////////////////////
	//使用@Query注解查询SQL
	//nativeQuery:默认的是false.表示不开启sql查询。是否对value中的语句做转义。
	@Query(value="select * from t_users where username = ?",nativeQuery=true)
	List<Users> queryUserByNameUseSQL(String name);

	@Query(value="select * from t_users where username like ?",nativeQuery=true)
	List<Users> queryUserByLikeNameUseSQL(String name);
	
	@Query(value="select * from t_users where username = ? and userage >= ?",nativeQuery=true)
	List<Users> queryUserByNameAndAgeUseSQL(String name, Integer age);
	
	@Query("update Users set userage = ? where userid = ?")
	@Modifying //@Modifying:当前语句是一个更新语句
	void updateUserAgeById(Integer age, Integer id);
	
}
