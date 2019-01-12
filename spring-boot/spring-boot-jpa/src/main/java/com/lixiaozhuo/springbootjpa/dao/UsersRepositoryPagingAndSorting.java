package com.lixiaozhuo.springbootjpa.dao;

import com.lixiaozhuo.springbootjpa.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 *PagingAndSortingRepository接口
 *
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users,Integer> {

}
