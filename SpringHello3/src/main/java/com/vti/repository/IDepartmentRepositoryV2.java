package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entites.Department;
import org.springframework.stereotype.Repository;


public interface IDepartmentRepositoryV2  extends JpaRepository<Department, Integer>{

}
