package com.epam.cdp.springbootlab.good;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="http://localhost:9999/goods", name="good")
public interface GoodResourceClient extends GoodResource {
}
