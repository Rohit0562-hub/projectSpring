package io.herald.projectSpring.Repository;

import io.herald.projectSpring.Model.ImageTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageTable, Integer> {


}
