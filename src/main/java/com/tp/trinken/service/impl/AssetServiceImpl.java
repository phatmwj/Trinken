package com.tp.trinken.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Asset;
import com.tp.trinken.repository.AssetRepo;
import com.tp.trinken.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {
	@Autowired
	AssetRepo assetRepo;

	@Override
	public <S extends Asset> S save(S entity) {
		// TODO Auto-generated method stub
		return assetRepo.save(entity);
	}
	
	

}
