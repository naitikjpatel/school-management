package com.school.repository;

import com.school.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // Using EntityGraph (clean and recommended)
    @EntityGraph(attributePaths = {"courses", "courses.subjects"})
    @Query("SELECT u FROM Users u WHERE u.userId = :id")
    Optional<Users> findUserWithCoursesAndSubjects(@Param("id") Long id);

}
