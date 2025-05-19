package com.school.repository;

import com.school.dtos.UserResultDto;
import com.school.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // Using EntityGraph (clean and recommended)
    @EntityGraph(attributePaths = {"courses", "courses.subjects"})
    @Query("SELECT u FROM Users u WHERE u.userId = :id")
    Optional<Users> findUserWithCoursesAndSubjects(@Param("id") Long id);

    Optional<Users> findByUserIdAndEmail(Long userId, String email);

    @Query("SELECT new com.school.dtos.UserResultDto(s.subjectName, r.status) " +
            "FROM Users u " +
            "JOIN u.results r " +
            "JOIN r.exam e " +
            "JOIN e.subjects s " +
            "WHERE u.userId = :userId")
    List<UserResultDto> findUserResults(@Param("userId") Long userId);

}
