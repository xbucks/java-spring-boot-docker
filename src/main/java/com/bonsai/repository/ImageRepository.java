package com.bonsai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bonsai.model.Image;

@Repository("ImageRepository")
public interface ImageRepository extends JpaRepository<Image, String> {

//	Page<Image> findAllByOrderByCreatedAtDesc(Pageable pageable);
//
//	Page<Image> findAllByOrderByViewsDesc(Pageable pageable);

	Optional<Image> findById(@Param("id") String id);
}
