package com.wushanghui.chat03.demo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 吴尚慧
 * @since 2022/8/14 17:50
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderModel {
    private Long id;
    private Long user_id;
    private Double price;
}
