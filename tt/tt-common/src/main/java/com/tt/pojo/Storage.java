package com.tt.pojo;

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
@TableName("storage")
public class Storage extends BasePojo{
	@TableId(type = IdType.AUTO)
	private Integer id;//仓库id
	private String name;//仓库姓名
	private String addr;//仓库地址
	private String tel;//仓库电话
}