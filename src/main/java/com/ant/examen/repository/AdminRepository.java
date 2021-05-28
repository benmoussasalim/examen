package com.ant.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
