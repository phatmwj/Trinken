package com.tp.trinken.service;

import com.tp.trinken.entity.Discount;

public interface DiscountService {

	<S extends Discount> S save(S entity);

}
