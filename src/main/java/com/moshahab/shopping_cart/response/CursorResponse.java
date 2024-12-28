package com.moshahab.shopping_cart.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursorResponse<T> {
    private List<T> content;
    private Long nextCursor;
}
