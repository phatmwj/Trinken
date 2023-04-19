package com.tp.trinken.service;

import com.tp.trinken.entity.Asset;

public interface AssetService {
	<S extends Asset> S save(S entity);
}
