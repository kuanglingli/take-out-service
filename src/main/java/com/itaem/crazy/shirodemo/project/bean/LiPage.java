package com.itaem.crazy.shirodemo.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LiPage{

    @NotNull(message = "分页参数不可为空")
    private Integer page;

    @NotNull(message = "分页参数不可为空")
    private Integer size;
}
