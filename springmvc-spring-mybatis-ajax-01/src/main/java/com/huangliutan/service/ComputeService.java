package com.huangliutan.service;

import com.huangliutan.entity.Records;
import com.huangliutan.entity.RequestParam;

import java.util.List;

public interface ComputeService {
    List<Records> compute(RequestParam requestParam);
}
