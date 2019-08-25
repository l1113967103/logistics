package com.tt.rep.service;

import com.tt.pojo.OrderDesc;
import com.tt.pojo.Storage;

public interface InbillsService {

	int createInbills(OrderDesc orderDesc,String inputPlace,Storage storage);
}
