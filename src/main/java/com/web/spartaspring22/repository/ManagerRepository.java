package com.web.spartaspring22.repository;

import com.web.spartaspring22.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByTodoId(Long id);
}
