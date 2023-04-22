package com.tp.trinken.service;

import com.tp.trinken.entity.CartItem;

public interface CartItemService {

	<S extends CartItem> S save(S entity);

}
