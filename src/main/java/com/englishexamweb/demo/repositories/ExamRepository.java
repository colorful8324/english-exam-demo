package com.englishexamweb.demo.repositories;

import com.englishexamweb.demo.entities.*;
import org.springframework.data.jpa.repository.*;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
