package com.ash.common.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: 封装分页后的数据格式
 */
@Data
public class PagedResult {

	/**
	 * 当前页数
	 */
	@ApiModelProperty("当前页码")
	private int currentPage;
	/**
	 * 总页数
	 */
	@ApiModelProperty("总页数")
	private int totalPage;
	/**
	 * 总记录数
	 */
	@ApiModelProperty("总记录数")
	private long records;
	/**
	 * 当前页的数据
	 */
	@ApiModelProperty("数据内容")
	private List<?> dataContent;
	@ApiModelProperty("是否具有下一页")
	private boolean hasNext;
}
