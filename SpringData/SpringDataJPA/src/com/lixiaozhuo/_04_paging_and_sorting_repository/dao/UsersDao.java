package com.lixiaozhuo._04_paging_and_sorting_repository.dao;



import com.lixiaozhuo._04_paging_and_sorting_repository.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * PagingAndSortingRepository接口
 *
 */
public interface UsersDao extends PagingAndSortingRepository<Users, Integer>{
	
}
