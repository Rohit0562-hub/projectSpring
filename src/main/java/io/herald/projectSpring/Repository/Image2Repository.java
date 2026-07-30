package io.herald.projectSpring.Repository;

import io.herald.projectSpring.Model.ImageTable2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Image2Repository extends JpaRepository<ImageTable2, Integer> {
}
