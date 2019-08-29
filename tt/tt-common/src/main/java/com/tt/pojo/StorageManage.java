package com.tt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("storage_manage")
public class StorageManage extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer id;//库存id
	private Integer storageId;//仓库id
	private String orderDescKind;//货物类型
	private Integer orderDescNum;//货物数量
	private Date inputTime;//入库时间
	private Date outTime;//出库时间
}
