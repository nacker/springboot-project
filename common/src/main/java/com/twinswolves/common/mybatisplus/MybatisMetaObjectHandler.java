package com.twinswolves.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

// 配置自动填充的内容：插入数据或写入数据时，create_time和update_time的字段不需要前端传入，自动填充。
// 当写入数据时，Mybatis-Plus会自动将实体对象的`create_time`字段填充为当前时间，
// 当更新数据时，则会自动将实体对象的`update_time`字段填充为当前时间。
/**
 * MyBatis-Plus 元对象处理器，用于自动填充实体对象中的创建时间和更新时间字段。
 * 该类实现了 MetaObjectHandler 接口，重写了插入和更新时的字段填充方法。
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入数据时的字段填充方法。
     * 当执行插入操作时，MyBatis-Plus 会调用此方法，将实体对象的 createTime 字段自动填充为当前时间。
     *
     * @param metaObject 元对象，包含了实体对象的相关信息，用于操作实体对象的属性。
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 使用严格插入填充方法，将 createTime 字段填充为当前时间
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    /**
     * 更新数据时的字段填充方法。
     * 当执行更新操作时，MyBatis-Plus 会调用此方法，将实体对象的 updateTime 字段自动填充为当前时间。
     *
     * @param metaObject 元对象，包含了实体对象的相关信息，用于操作实体对象的属性。
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 使用严格更新填充方法，将 updateTime 字段填充为当前时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
