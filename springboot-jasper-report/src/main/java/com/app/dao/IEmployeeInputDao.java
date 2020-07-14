package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.EmployeeInput;

@Repository
public interface IEmployeeInputDao extends JpaRepository<EmployeeInput,Integer>{

}
