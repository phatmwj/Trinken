package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Asset;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Integer> {

}
