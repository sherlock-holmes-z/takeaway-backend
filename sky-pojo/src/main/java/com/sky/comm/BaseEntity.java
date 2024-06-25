package com.sky.comm;

import com.sky.annotation.AutoFill;
import com.sky.enums.FieldFillType;
import com.sky.enums.FieldType;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * @author Chocolate
 * @since 2024/6/23 1:28
 */
@Data
//@Getter
public class BaseEntity {

    @AutoFill(value = FieldFillType.INSERT, type = FieldType.CURRENT_USER)
    public Long createUser;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @AutoFill(value = FieldFillType.INSERT, type = FieldType.LOCAL_DATE_TIME)
    public LocalDateTime createTime;

    @AutoFill(value = FieldFillType.INSERT_UPDATE, type = FieldType.LOCAL_DATE_TIME)
    public LocalDateTime updateTime;

    @AutoFill(value = FieldFillType.INSERT_UPDATE, type = FieldType.CURRENT_USER)
    public Long updateUser;


}
