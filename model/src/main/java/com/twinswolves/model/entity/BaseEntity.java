package com.twinswolves.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity 类是一个基础实体类，实现了 Serializable 接口，可用于序列化操作。
 * 该类定义了一些通用的字段，如主键、创建时间、更新时间和逻辑删除标记，
 * 其他实体类可以继承该类来复用这些通用字段。
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 实体的主键字段，用于唯一标识一条记录。
     * 使用 @Schema 注解描述字段信息，方便 Swagger 生成 API 文档。
     * @TableId 注解指定该字段为主键，并且使用数据库自增策略。
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 记录的创建时间字段，用于记录该实体创建的时间。
     * 使用 @Schema 注解描述字段信息，@TableField 注解指定字段名和插入时自动填充。
     * @JsonIgnore 注解标记该字段在序列化时会被忽略。
     */
    @Schema(description = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonIgnore // 标记序列化被忽略的字段
    private Date createTime;

    /**
     * 记录的更新时间字段，用于记录该实体最后一次更新的时间。
     * 使用 @Schema 注解描述字段信息，@TableField 注解指定字段名和更新时自动填充。
     * @JsonIgnore 注解标记该字段在序列化时会被忽略。
     */
    @Schema(description = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @JsonIgnore // 标记序列化被忽略的字段
    private Date updateTime;

    /**
     * 逻辑删除标记字段，用于标记该记录是否被删除。
     * 使用 @Schema 注解描述字段信息，@JsonIgnore 注解标记该字段在序列化时会被忽略。
     * @TableLogic 注解标记该字段为逻辑删除字段，使用 MyBatis Plus 的删除方法时，
     * 会将该字段更新为 1，而不是实际删除记录。
     */
    @Schema(description = "逻辑删除")
    @JsonIgnore
    @TableLogic // 标记逻辑删除字段，使用 MyBatis Plus 的删除方法时，逻辑删除会自动生效。会将该字段更新为 1，而不是实际删除记录。
    @TableField("is_deleted")
    private Byte isDeleted;

}