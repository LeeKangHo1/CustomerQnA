package kr.co.greenart.web.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyOrder {
	private String column;
	private String order;
	
	public String getSortingMethod() {
		// (ORDER BY) views(column) + 띄어쓰기 + DESC(order)
		return column + " " + order;
	}
}
